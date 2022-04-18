package com.adventurer.logic;

public class Player extends Element {
    
    private String name;
    private int orientation;
    private int treasureAmount;
    private final String orientations = "NESO";

    /**
     * Player constructor
     * @param name name of the player
     * @param position position of the player on the map
     * @param orientation orientation of the player on the map
     */
    Player(String name, int[] position, String orientation) {
        super("A", position, false, 3);
        this.name = name;
        this.orientation = this.orientations.indexOf(orientation);
        this.treasureAmount = 0;
    }

    /**
     * Move the player needs to do
     * @param Movement string matching the movement to do
     * @param map the current environment (Map) of the player
     */
    void makeMove(String Movement, Map map) {
        switch (Movement) {
            case "A": // forward
                int[] move = this.getMoveDirection();
                if (map.isTileWalkable(this.aimedPosition(this.position, move))) {
                    // if the player can move to the tile
                    this.position = this.aimedPosition(this.position, move);
                    Treasure treasureFound = map.searchForTreasure(this.position);
                    if (treasureFound != null) {
                        // if a treasure is at this location on the map
                        this.treasureAmount += treasureFound.takeTreasure();
                    }
                }
                return;

            case "G": //left
                this.orientation -= 1;
                break;

            case "D": //right
                this.orientation += 1;
                break;
        }
        
        //keeping the orientation between 0 and 3
        if (this.orientation == -1) {
            this.orientation = 3;
        } else if (this.orientation == 4) {
            this.orientation = 0;
        }
    }

    /**
     * compute the sum of the two position
     * @param myPosition position 1 (x1, y1)
     * @param move       position 2 (x2, y2)
     * @return the sum of elements (x1 + x2, y1 + y2)
     */
    private int[] aimedPosition(int[] myPosition, int[] move) {
        return new int[]{myPosition[0] + move[0], myPosition[1] + move[1]};
    }

    /**
     * get the moving direction
     * @return int[] for the move to do
     */
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

    /**
     * @return the state of the player
    */
    @Override
    String getState() {
        return "A - "+ this.name
            +  " - " + this.position[0]
            +  " - " + this.position[1]
            +  " - " + this.getOrientation()
            +  " - " + this.treasureAmount;
    }

    /**
     * @return the player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the string associated player orientation
     */
    public String getOrientation() {
        return Character.toString(orientations.charAt(this.orientation));
    }
}
