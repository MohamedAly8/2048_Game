/**
 * Author: Mohamed Aly
 * Revised: April 15, 2021
 *
 * Description: A class for the Board which the game 2048 will be displayed on
 */

public class BoardT {

    final static int rows = 4;
    final static int cols = 4;
    private static TileT[][] board;
    private static int score;
    private static boolean game_over;

    /**
     * @brief The constructor for an object of type BoardT
     * @details This method initialized a board by creating a 2D Array of TileT where
     * each TileT object has a value of 0. The constructor also initializes the score
     * of the game to 0
     */
    public BoardT() {
        this.board = new TileT[rows][cols];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                this.board[i][j] =  new TileT(0, new int[] {i,j});
            }
        }
        this.score = 0;
        this.game_over = false;
    }

    /**
     * @brief Gets the dimension of the Board
     * @return Returns an integer which is both the length and width of the board
     */
    public int getSize(){
        return this.board.length;
    }

    /**
     * @brief This method gets the Board Object
     * @return Returns the board object as type 2D TileT Array
     */
    public TileT[][] getBoard(){
        return this.board;
    }

    /**
     * @brief This method gets the current score of an object BoardT
     * @return Returns the current score of an object BoardT
     */
    public int getScore(){
        return this.score;
    }

    /**
     * @brief This method changes and updates the score of BoardT
     * @param new_score The new score that is set to be the score for the Board Object
     */
    public void setScore(int new_score){
        this.score = new_score;
    }


    /**
     * @brief Gets the value of a Tile at a certain row and column in the Board
     * @param row The row at which the value of the tile is being retrieved
     * @param col The coloumn at which the value of the tile is being retrieved
     * @return Returns the value of a Tile at a given row and column
     */
    public int get_Board_val_at(int row, int col){
        return this.board[row][col].getVal();
    }

    /**
     * @brief Changes the value of a Tile at a certain row and coloumn in the Board
     * @param row The row at which the value of the tile is being changed
     * @param col The coloumn at which the value of the tile is being changed
     * @param new_val The new value of the Tile at a given row and column
     */
    public void set_Board_val_at(int row, int col, int new_val){
        this.board[row][col].setVal(new_val);
    }

    /**
     * @brief Gets the boolean game_over associated with the BoardT object
     * @return Returns a boolean whether the game is over or not
     */
    public boolean get_GameOver(){
        return this.game_over;
    }
    /**
     * @brief Gets the boolean game_over associated with the BoardT object
     * @param is_game_over The new parameter boolean that is being set to game_over
     */
    public void set_GameOver(boolean is_game_over){
        this.game_over = is_game_over;
    }

    /**
     * @brief A method that visualizes what the Board object looks like along with all the TileT entries in it.
     * @details The method prints the board in a clear manner along with the current score of the Board. This is done
     * using the get_Board_val_at method which is called for each coordinate in the board to retrieve the value for the
     * coordinates.
     * @return Returns a string which visualizes the board
     */
    public  String Print_Board() {
        String output = "Score: " + this.getScore() + "\n";
        for(int i = 0; i < 4; i++) {
            output += "|";
            for(int j = 0; j < 4; j++) {
                output += "\t" + this.get_Board_val_at(i, j) + "\t" + "|";
            }
            output += "\n";
        }
        return output;
    }
}




