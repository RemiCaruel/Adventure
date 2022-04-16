package com.adventurer.logic;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.SourceType;

public class Map {

    @Id
    @GeneratedValue
    Long id;
    int sizeX, sizeY;
    Element[] elements;
    int lastElementPos;

    Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.lastElementPos = 0;

        this.elements = new Element[sizeX * sizeY];
    }

    public String getState() {
        String state = "C - " + sizeX + " - " + sizeY + "\n";
        for (Element element : elements) {
            if (element ==  null) continue;
            state += element.getState() + "\n";
        }
        return state;
    }

    boolean isTileWalkable(int[] position) {
        System.out.println(position[0] + ", " + position[1]);
        boolean isWalkable = true;
        if (position[0] > sizeX || position[1] > sizeY || position[0] < 0 || position[1] < 0) return false;
        for (Element element: elements) {
            if (element != null) {
                if (position == element.position) {
                    isWalkable = isWalkable && element.isWalkable;
                }
            }
        }
        return isWalkable;
    }

    Treasure searchForTreasure(int[] position) {
        for (Element element: elements) {
            if (element != null) {
                if (element.getClass().getName().equals("com.adventurer.logic.Treasure") && element.position == position ) {
                    return (Treasure)element;
                }
            }
        }
        return null;
    }

    void AddElementAt(String identifiant, int[] position, boolean isWalkable, int layer) {
        if (position[0] > sizeX && position[1] > sizeY) return;

        this.elements[lastElementPos] = new Element(identifiant, position, isWalkable, layer);
        lastElementPos += 1;
    }

    void AddTreasureAt(int[] position, int treasureAmount) {
        if (position[0] > sizeX && position[1] > sizeY) return;
        if (treasureAmount < 0) return;

        this.elements[lastElementPos] = new Treasure(position, treasureAmount);
        lastElementPos += 1;
    }

    void AddPlayerAt(int[] position, String name, String orientation) {
        if (position[0] > sizeX && position[1] > sizeY) return;

        this.elements[lastElementPos] = new Player(name, position, orientation);
        lastElementPos += 1;
    }

    void MovePlayer(String name, String move) {
        for (Element element: elements) {
            if (element != null) {
                if (element.getClass().getName().equals("com.adventurer.logic.Player")) {
                    System.out.println("This is a player!");
                    if (((Player)element).getName().equals(name)) {
                        System.out.println("This is my Player : " + name);
                        ((Player)element).makeMove(move, this);
                    };
                }
            }
        }
    }
}
