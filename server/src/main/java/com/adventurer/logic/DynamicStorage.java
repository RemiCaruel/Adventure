package com.adventurer.logic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
class DynamicStorage {

    @Value( "${DynamicStorage.TrafficBaseHour}" )
    private int trafficBaseHour;
    private int trafficBaseHourCreationLimit;
    private int trafficBaseHourReductionLimit;
    private Adventure[] Storage;
    private Adventure[] TempStorage;
    private int currentSize;

    /**
     * Construction
     * Initialize some parameters according to DynamicStorage.TrafficBaseHour parameter
     */
    DynamicStorage() {
        this.trafficBaseHourCreationLimit = (int)(this.trafficBaseHour * 0.9f);
        this.trafficBaseHourReductionLimit = (int)(this.trafficBaseHour * 1.9f);
        this.currentSize = this.trafficBaseHour;
    }

    /**
     * Increase the size of the stored data according to the TrafficBaseHour parameter
     */
    void increaseSize() {
        this.TempStorage = new Adventure[this.currentSize];
        this.TempStorage = this.Storage;
        this.currentSize += this.trafficBaseHour;
        this.Storage = new Adventure[this.currentSize];
        for (int i = 0; i < this.TempStorage.length; i++) {
            this.Storage[i] = this.TempStorage[i];
        }
        this.TempStorage = null;
    }

    /**
     * Decrease the size of the stored data according to the TrafficBaseHour parameter
     */
    void decreaseSize() {
        this.TempStorage = new Adventure[this.currentSize];
        this.TempStorage = this.Storage;
        this.currentSize -= this.trafficBaseHour;
        this.Storage = new Adventure[this.currentSize];
        for (int i = 0; i < this.Storage.length; i++) {
            this.Storage[i] = this.TempStorage[i];
        }
        this.TempStorage = null;
    }

    /**
     * Add a new adventure to the storage
     *      In case the storage has empty space less than 90% of TrafficBaseHour parameter ==> increase its size
     * @param adventure adventure to add
     */
    void add(Adventure adventure) {
        int numberEmpty = currentSize;
        for (Adventure adv: this.Storage) {
            if (adv == null) {
                adv = adventure;
            } else {
                numberEmpty -= 1;
            }
        }

        if (numberEmpty < this.trafficBaseHourCreationLimit) {
            this.increaseSize();
        }
    }

    /**
     * remove an adventure from the storage
     *      In case the storage has empty space more than 190% of TrafficBaseHour parameter ==> decrease its size
     * @param adventure adventure to add
     */
    void remove(Long id) {
        int numberEmpty = 0;
        for (Adventure adv: this.Storage) {
            if (adv == null) {
                numberEmpty += 1;
            } else if (adv.getId() == id) {
                adv = null;
            }
        }

        if (numberEmpty > this.trafficBaseHourReductionLimit) {
            this.decreaseSize();
        }
    }

    /**
     * return the adventure based on its id
     * @param id adventure's id
     * @return the matching adventure
     */
    Adventure getAdventure(Long id) {
        for (Adventure adventure: this.Storage) {
            if (adventure == null) continue;
            if (adventure.getId() == id) {
                return adventure;
            }
        }
        return null;
    }
}
