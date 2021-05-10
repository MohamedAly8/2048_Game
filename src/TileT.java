/**
 * Author: Mohamed
 * Revised: April 15, 2021
 * Description: A class to make the Tiles that are going to be on the Board
 */


public class TileT {

    private int val;
    private int[] position;

    /**
     * @brief A constructor method for TileT
     * @details The constructor method is for each value on the game board. Coordinates are needed
     * to know the position of the Tile, as well as the Tile value.
     * @param value An integer which holds the value of a tile on the board
     * @param cords The coordinates of the Tile on the game board
     */
    public TileT(int value, int[] cords ){
        this.val = value;
        this.position = cords;
    }

    /**
     * @brief The function returns the value of the Tile
     * @return Returns the value of the tile
     */
    public int getVal() {
        return val;
    }

    /**
     * @brief The function changes the value of the Tile to the parameter new_val
     * @param new_val The new value of a Tile
     */
    public void setVal(int new_val) {
        this.val = new_val;
    }

    /**
     * @brief The function gets the coordinates of a TileT in an Array
     * @return Returns an array for the position of the TileT object
     */
    public int[] getPosition(){
        return this.position;
    }

}

