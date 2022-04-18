package com.adventurer.logic;

import java.util.Arrays;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Map {

    @Id
    @GeneratedValue
    Long id;
    int sizeX, sizeY;
    Element[] elements;
    int lastElementPos;

    /**
     * Map constructor
     * @param sizeX Horizontal dimension
     * @param sizeY Vertical dimension
     */
    Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.lastElementPos = 0;

        this.elements = new Element[sizeX * sizeY];
    }

    /**
     * format the state of the map 
     * @return state of the map as a String
     */
    public String getState() {
        String state = "C - " + sizeX + " - " + sizeY + "\n";
        for (Element element : elements) {
            if (element ==  null) continue;
            if (element.getState() == "") continue;
            state += element.getState() + "\n";
        }
        return state;
    }

    /**
     * returns if a tile is walkable
     *      In case multiple tiles are at the same position, takes the most constraint result
     * @param position position aimed to move to
     * @return boolean indicating if the move is possible
     */
    boolean isTileWalkable(int[] position) {
        boolean isWalkable = true;
        if (position[0] > sizeX || position[1] > sizeY || position[0] < 0 || position[1] < 0) return false;
        for (Element element: elements) {
            if (element != null) {
                if (Arrays.equals(position, element.position)) {
                    isWalkable = isWalkable && element.isWalkable;
                }
            }
        }
        return isWalkable;
    }

    /**
     * returns if there is a treasure at the position
     * @param position position to search for
     * @return the treasure at this position
     */
    Treasure searchForTreasure(int[] position) {
        for (Element element: elements) {
            if (element != null) {
                if (element.getClass().getName().equals("com.adventurer.logic.Treasure") && Arrays.equals(position, element.position) ) {
                    return (Treasure)element;
                }
            }
        }
        return null;
    }

    /**
     * Add a generic element to the map
     * @param identifiant the element identificator
     * @param position    the element position on the map
     * @param isWalkable  wether or not an adventurer can walk on the tile
     * @param layer       the tile layer for printing purposes
     */
    void AddElementAt(String identifiant, int[] position, boolean isWalkable, int layer) {
        if (position[0] > sizeX || position[1] > sizeY) return;
        
        this.elements[lastElementPos] = new Element(identifiant, position, isWalkable, layer);
        lastElementPos += 1;
    }

    /**
     * Add a treasure to the map
     * @param position the treasure position on the map
     * @param treasureAmount the amount of treasures it has
     */
    void AddTreasureAt(int[] position, int treasureAmount) {
        if (position[0] > sizeX || position[1] > sizeY) return;
        if (treasureAmount < 0) return;
        
        this.elements[lastElementPos] = new Treasure(position, treasureAmount);
        lastElementPos += 1;
    }

    /**
     * Add a player to the map
     * @param position the position of the player on the map
     * @param name the name of the player
     * @param orientation the current orientation of the player
     */
    void AddPlayerAt(int[] position, String name, String orientation) {
        if (position[0] > sizeX || position[1] > sizeY) return;
        if (!this.isTileWalkable(position)) return;
        
        this.elements[lastElementPos] = new Player(name, position, orientation);
        lastElementPos += 1;
    }

    /**
     * Get a player to make it move
     * @param name name of the player
     * @param move move the player should do
     */
    void MovePlayer(String name, String move) {
        for (Element element: elements) {
            if (element != null) {
                if (element.getClass().getName().equals("com.adventurer.logic.Player")) {
                    if (((Player)element).getName().equals(name)) {
                        ((Player)element).makeMove(move, this);
                    };
                }
            }
        }
    }
}
