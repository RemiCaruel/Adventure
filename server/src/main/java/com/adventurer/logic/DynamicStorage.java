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

    DynamicStorage() {
        this.trafficBaseHourCreationLimit = (int)(this.trafficBaseHour * 0.9f);
        this.trafficBaseHourReductionLimit = (int)(this.trafficBaseHour * 1.9f);
        this.currentSize = this.trafficBaseHour;
    }

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
