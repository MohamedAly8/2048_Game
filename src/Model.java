/**
 * Author: Mohamed Aly
 * Revised: April 15, 2021
 *
 * Description: The Model which contains methods that controls the board
 */

import java.util.Random;
import java.util.ArrayList;
public class Model {


    private BoardT boardT;

    /**
     * @brief A constructor for the Model class
     * @param boardT
     */
    public Model(BoardT boardT){
        this.boardT = boardT;
    }

    /**
     * @brief Gets the boardT associated with the Model Object
     * @return Returns the boardT associated with the Model Object
     */
    public BoardT getBoardT() {
        return this.boardT;
    }

    /**
     * @brief Adds a TileT to a Random available cell in the board
     * @details The Tile is added to a random cell by generating a random
     * row and column within the bounds of the board, then there is a check
     * to whether that certain spot is empty, if not the row/col are updated
     * by random again till an empty cell is found. If there are no empty cells,
     * then the method does not do anything.
     */
    public void addRandomTile(){
        Random rand = new Random();

        int row = rand.nextInt(this.boardT.getSize());
        int col = rand.nextInt(this.boardT.getSize());

        if (availableCells().isEmpty()){
            return;
        }
        while (this.boardT.getBoard()[row][col].getVal() != 0){
            row = rand.nextInt(this.boardT.getSize());
            col = rand.nextInt(this.boardT.getSize());
        }
        this.boardT.getBoard()[row][col].setVal(GetRandomVal());
    }

    /**
     * @brief Gets either 2 or 4 at random
     * @details The number 2 or 4 are chosen by random, but there
     * is a 75% chance that a 2 is chosen and a 25% chance of a 4
     * being chosen
     * @return Returns either 2 or 4 by random probabilities
     */
    public static int GetRandomVal(){
        Random rand = new Random();

        int[] possible_starts = new int[] {2,2,2,4};
        return possible_starts[rand.nextInt(4)];
    }


    /**
     * @brief Adds 2 starting Tiles to the board with values 2 or 4
     * @details It is assumed that if this method is called that the board is empty. The cells were
     * the Tiles are added are chosen at random.
     */
    public void addStartTile(){

            TileT tile1 = RandomAvailableCell();
            TileT tile2 = RandomAvailableCell();

        this.boardT.set_Board_val_at(tile1.getPosition()[0],tile1.getPosition()[1] , GetRandomVal());
        this.boardT.set_Board_val_at(tile2.getPosition()[0],tile2.getPosition()[1] , GetRandomVal());

    }


    // find random available cell

    /**
     * @brief Finds an available cell
     * @return Returns an available cell on the board with value of 0
     */
    public TileT RandomAvailableCell(){
        Random rand = new Random();
        return availableCells().get(rand.nextInt(availableCells().size()));
    }

    // any cells available?

    /**
     * @brief If there is an available cell, true is returned
     * @return Returns if there is an available cell in the board or not
     */
    public boolean isCellAvailable(){
        for (TileT[] row: this.boardT.getBoard()){
            for (TileT tile: row){
                if (tile.getVal() == 0){
                    return true;
                }
            }
        }
        return false;
    }

    // available moves. returns array list of all tiles that are available

    /**
     * @brief Gets an Arraylist of objects TileT that have a value of 0 on the board
     * @details By available cell, it means that the TileT on the board has a value of 0,
     * but there is still a TileT there.
     * @return Returns a list of all the available cells
     */
    public ArrayList<TileT> availableCells(){
        ArrayList<TileT> available_tiles = new ArrayList<TileT>();
        for (TileT[] row: this.boardT.getBoard()){
            for (TileT tile: row){
                if (tile.getVal() == 0){
                    available_tiles.add(tile);
                }
            }
        }
        return available_tiles;
    }


    /**
     * @brief Shifts all TileT in the board up
     * @details All the Tiles shift up to fill in vacancies on the board. Vacancies
     * means that the value of the cell is 0
     * @return Returns a boolean of whether at least one TileT shifted during the execution of the method
     */
    public boolean shiftup(){
        boolean moved = false;

        for (int j = 0; j < this.boardT.getSize(); j++){
            for (int iterate = 1; iterate < this.boardT.getSize(); iterate++){
                for (int i = this.boardT.getSize()-1; i > 0; i--){
                    if (this.boardT.get_Board_val_at(i,j) != 0 && this.boardT.getBoard()[i-1][j].getVal() == 0){
                        this.boardT.set_Board_val_at(i-1,j,this.boardT.get_Board_val_at(i,j));
                        this.boardT.set_Board_val_at(i,j,0);
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * @brief Combines 2 adjacent cells if they hold the same value
     * @details The method checks all adjacent values on the board in a vertical manner, if any adjacent values
     * are found that are equal, then the two values are combined into one cell where the value of that cell is doubled.
     * The cell above is where the doubled value becomes, while the below cell value goes to 0.
     * @return Returns true if at least 2 adjacent cells were combined during the execution of this method
     */
    public boolean mergeup(){
        boolean merged = false;

        for(int col = 0; col < this.boardT.getSize(); col++) {
            for(int row = 0; row < this.boardT.getSize() - 1; row++) {
                    if(this.boardT.get_Board_val_at(row, col) == this.boardT.get_Board_val_at(row + 1, col) && this.boardT.get_Board_val_at(row, col) != 0) {
                        this.boardT.set_Board_val_at(row + 1, col, this.boardT.get_Board_val_at(row, col) * 2);
                        this.boardT.set_Board_val_at(row, col, 0);
                        int new_score = this.boardT.getScore() + this.boardT.get_Board_val_at(row + 1, col);
                        this.boardT.setScore(new_score);
                        merged = true;
                        this.shiftup();
                }
            }
        }
        return merged;
    }


    /**
     * @brief Shifts all TileT in the board down
     * @details All the Tiles shift down to fill in vacancies on the board. Vacancies
     * means that the value of the cell is 0
     * @return Returns a boolean of whether at least one TileT shifted during the execution of the method
     */
    public boolean shiftdown(){

        boolean moved = false;
        for (int j = 0; j < this.boardT.getSize(); j++){
            for (int iterate = 1; iterate < this.boardT.getSize(); iterate++){
                for (int i = 0; i < this.boardT.getSize()-1; i++){
                    if (this.boardT.get_Board_val_at(i,j) != 0 && this.boardT.getBoard()[i+1][j].getVal() == 0){
                        this.boardT.set_Board_val_at(i+1,j,this.boardT.get_Board_val_at(i,j));
                        this.boardT.set_Board_val_at(i,j,0);
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * @brief Combines 2 adjacent cells if they hold the same value
     * @details The method checks all adjacent values on the board in a vertical manner, if any adjacent values
     * are found that are equal, then the two values are combined into one cell where the value of that cell is doubled.
     * The cell below is where the doubled value becomes, while the above cell value goes to 0.
     * @return Returns true if at least 2 adjacent cells were combined during the execution of this method
     */
    public boolean mergedown(){
        boolean merged = false;

        for(int col = 0; col < this.boardT.getSize(); col++) {
            for(int row = this.boardT.getSize()-1; row > 1; row--) {
                if(this.boardT.get_Board_val_at(row, col) == this.boardT.get_Board_val_at(row - 1, col) && this.boardT.get_Board_val_at(row, col) != 0) {
                    this.boardT.set_Board_val_at(row - 1, col, this.boardT.get_Board_val_at(row, col) * 2);
                    this.boardT.set_Board_val_at(row, col, 0);
                    int new_score = this.boardT.getScore() + this.boardT.get_Board_val_at(row - 1, col);
                    this.boardT.setScore(new_score);
                    merged = true;
                    this.shiftdown();
                }
            }
        }
        return merged;
    }

    /**
     * @brief Shifts all TileT in the board left
     * @details All the Tiles shift left to fill in vacancies on the board. Vacancies
     * means that the value of the cell is 0
     * @return Returns a boolean of whether at least one TileT shifted during the execution of the method
     */
    public boolean shiftleft(){

        boolean moved = false;
        for (int i = 0; i < this.boardT.getSize(); i++){
            for (int iterate = 0; iterate < this.boardT.getSize(); iterate++){
                for (int j = 1; j < this.boardT.getSize(); j++){
                    if (this.boardT.getBoard()[i][j-1].getVal() == 0){
                        this.boardT.set_Board_val_at(i,j-1,this.boardT.get_Board_val_at(i,j));
                        this.boardT.set_Board_val_at(i,j,0);
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * @brief Combines 2 adjacent cells if they hold the same value
     * @details The method checks all adjacent values on the board in a horizontal manner, if any adjacent values
     * are found that are equal, then the two values are combined into one cell where the value of that cell is doubled.
     * The cell to the left is where the doubled value becomes, while the cell to the right value goes to 0.
     * @return Returns true if at least 2 adjacent cells were combined during the execution of this method
     */
    public boolean mergeleft(){
        boolean merged = false;

        for(int col = 1; col < this.boardT.getSize(); col++) {
            for(int row = 0; row < this.boardT.getSize(); row++) {
                if(this.boardT.get_Board_val_at(row, col) == this.boardT.get_Board_val_at(row , col-1) && this.boardT.get_Board_val_at(row, col) != 0) {
                    this.boardT.set_Board_val_at(row, col-1, this.boardT.get_Board_val_at(row, col) * 2);
                    this.boardT.set_Board_val_at(row, col, 0);
                    int new_score = this.boardT.getScore() + this.boardT.get_Board_val_at(row, col-1);
                    this.boardT.setScore(new_score);
                    merged = true;
                    this.shiftleft();
                }
            }
        }
        return merged;
    }


    /**
     * @brief Shifts all TileT in the board right
     * @details All the Tiles shift right to fill in vacancies on the board. Vacancies
     * means that the value of the cell is 0
     * @return Returns a boolean of whether at least one TileT shifted during the execution of the method
     */
    public boolean shiftright(){
        boolean moved = false;

        for (int i = 0; i < this.boardT.getSize(); i++){
            for (int iterate = 0; iterate < this.boardT.getSize(); iterate++){
                for (int j = this.boardT.getSize()-2; j >= 0; j--){
                    if (this.boardT.getBoard()[i][j+1].getVal() == 0){
                        this.boardT.set_Board_val_at(i,j+1,this.boardT.get_Board_val_at(i,j));
                        this.boardT.set_Board_val_at(i,j,0);
                        moved = true;
                    }
                }
            }
        }
        return moved;
    }

    /**
     * @brief Combines 2 adjacent cells if they hold the same value
     * @details The method checks all adjacent values on the board in a horizontal manner, if any adjacent values
     * are found that are equal, then the two values are combined into one cell where the value of that cell is doubled.
     * The cell to the right is where the doubled value becomes, while the cell to the left value goes to 0.
     * @return Returns true if at least 2 adjacent cells were combined during the execution of this method
     */
    public boolean mergeright(){
        boolean merged = false;

        for(int col = this.boardT.getSize()-2; col >= 0; col--) {
            for(int row = 0; row < this.boardT.getSize(); row++) {
                if(this.boardT.get_Board_val_at(row, col) == this.boardT.get_Board_val_at(row , col+1) && this.boardT.get_Board_val_at(row, col) != 0) {
                    this.boardT.set_Board_val_at(row, col+1, this.boardT.get_Board_val_at(row, col) * 2);
                    this.boardT.set_Board_val_at(row, col, 0);
                    int new_score = this.boardT.getScore() + this.boardT.get_Board_val_at(row, col+1);
                    this.boardT.setScore(new_score);
                    merged = true;
                    this.shiftright();
                }
            }
        }
        return merged;
    }


    /**
     * @brief The method returns true if at least 2 adjacent TileT objects on
     * the board hold the same value
     * @details The two TileT objects cannot have the same value of 0, but a value
     * of greater than 0. This is checked within the method.
     * @return Returns true if there are two adjacent cells with the same value
     */
    public boolean isMergePossible(){
        for (int i = 0; i < this.boardT.getSize(); i++){
            for (int j = 0; j < this.boardT.getSize()-1; j++){
                if (this.boardT.get_Board_val_at(i,j) == this.boardT.get_Board_val_at(i, j+1 )){
                    if (this.boardT.get_Board_val_at(i,j) != 0)
                    return true;
                }
            }
        }
        for (int i = 0; i < this.boardT.getSize()-1; i++){
            for (int j = 0; j < this.boardT.getSize(); j++){
                if (this.boardT.get_Board_val_at(i,j) == this.boardT.get_Board_val_at(i+1, j)){
                    if (this.boardT.get_Board_val_at(i,j) != 0)
                        return true;
                }
            }
        }
        return false;

    }

    /**
     * @brief Determines whether the game is won yet
     * @details The game is won if one of the TileT in the Board has a value
     * of 2048. The method checks each TileT if the value is 2048 and returns
     * true if at least one holds this value.
     * @return Returns whether the game is won or not
     */
    public  boolean isGameWon(){
        for (TileT[] row: this.boardT.getBoard()){
            for (TileT tile: row){
                if (tile.getVal() == 2048){
                    return  true;
                }
            }
        }
        return false;
    }
}
