package com.adventurer.logic;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

class Element {
    
    @Id
    @GeneratedValue
    int id;
    int[] position;
    boolean isWalkable;
    int layer;
    String ElementSign;

    public Element(String ElementSign, int[] position, boolean isWalkable, int layer) {
        this.position = position;
        this.isWalkable = isWalkable;
        this.layer = layer;
        this.ElementSign = ElementSign;
    }

    String getState() {
        return ElementSign + " - " + this.position[0] + " - " + this.position[1];
    }
}
