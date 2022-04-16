package com.adventurer.logic;

public class Player extends Element {
    
    private String name;
    private int orientation;
    private int treasureAmount;
    private final String orientations = "NESO";

    Player(String name, int[] position, String orientation) {
        super("A", position, false, 3);
        this.name = name;
        this.orientation = this.orientations.indexOf(orientation);
        this.treasureAmount = 0;
    }

    void makeMove(String Movement, Map map) {
        System.out.println(Movement);
        switch (Movement) {
            case "A":
                System.out.println("Straight!");
                int[] move = this.getMoveDirection();
                if (map.isTileWalkable(this.aimedPosition(this.position, move))) {
                    this.position = this.aimedPosition(this.position, move);
                    Treasure treasureFound = map.searchForTreasure(this.position);
                    if (treasureFound != null) {
                        this.treasureAmount += treasureFound.takeTreasure();
                    }
                    System.out.println("Moved!");
                }
                return;
            case "G":
                System.out.println("Left!");
                this.orientation -= 1;
                break;
            case "D":
                System.out.println("Right");
                this.orientation += 1;
                break;
        }
        if (this.orientation == -1) {
            this.orientation = 3;
        } else if (this.orientation == 4) {
            this.orientation = 0;
        }
    }

    private int[] aimedPosition(int[] myPosition, int[] move) {
        return new int[]{myPosition[0] + move[0], myPosition[1] + move[1]};
    }

    private int[] getMoveDirection() {
        switch (this.orientation) {
            case 0: //N
                return new int[]{  0, -1};
            case 1: //E
                return new int[]{  1,  0};
            case 2: //S
                return new int[]{  0,  1};
            case 3: //O
                return new int[]{ -1,  0};
            default:
                return new int[]{  0,  0};
        }
    }

    @Override
    String getState() {
        return "A - "+ this.name
            +  " - " + this.position[0]
            +  " - " + this.position[1]
            +  " - " + this.getOrientation()
            +  " - " + this.treasureAmount;
    }

    public String getName() {
        return this.name;
    }

    public String getOrientation() {
        return Character.toString(orientations.charAt(this.orientation));
    }
}
