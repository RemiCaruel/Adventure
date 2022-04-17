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

    /**
     * Generic Element
     * @param ElementSign Element designation can be "T", "M", "A"
     * @param position    Element position on the board
     * @param isWalkable  boolean indicating if a player can move on its tile or not
     * @param layer       printing layer
     */
    public Element(String ElementSign, int[] position, boolean isWalkable, int layer) {
        this.position = position;
        this.isWalkable = isWalkable;
        this.layer = layer;
        this.ElementSign = ElementSign;
    }

    /**
     * Generic state representation
     * @return state of the element as a String eg : M - 3 - 3
     */
    String getState() {
        return ElementSign + " - " + this.position[0] + " - " + this.position[1];
    }
}
