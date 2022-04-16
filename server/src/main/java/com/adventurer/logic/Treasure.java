package com.adventurer.logic;

public class Treasure extends Element{

    private int treasureAmount;

    Treasure(int[] position, int treasureAmount) {
        super("T", position, true, 1);
        this.treasureAmount = treasureAmount;
    }

    int takeTreasure() {
        if (this.treasureAmount == 0) {
            return 0;
        }

        this.treasureAmount -= 1;
        return 1;
    }

    @Override
    String getState() {
        return "T - " + this.position[0] + " - " + this.position[1] + " - " + treasureAmount;
    }
    
}
