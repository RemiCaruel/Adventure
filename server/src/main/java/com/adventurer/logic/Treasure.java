package com.adventurer.logic;

public class Treasure extends Element{

    private int treasureAmount;

    /**
     * Treasure element constructor
     * @param position position of the Treasure
     * @param treasureAmount amount of treasure available to pick
     */
    Treasure(int[] position, int treasureAmount) {
        super("T", position, true, 1);
        this.treasureAmount = treasureAmount;
    }

    /**
     * picking a treasure
     * @return 1 if there are any left, 0 otherwise
     */
    int takeTreasure() {
        if (this.treasureAmount == 0) {
            return 0;
        }

        this.treasureAmount -= 1;
        return 1;
    }

    /**
     * return the state of the treasure if there are any left
     */
    @Override
    String getState() {
        return "T - " + this.position[0] + " - " + this.position[1] + " - " + treasureAmount;
    }
    
}
