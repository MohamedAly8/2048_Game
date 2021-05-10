/**
 * Author: Mohamed Aly
 * Revised: April 15, 2021
 *
 * Description: The visuals of the game and run through is in this class
 */

import java.util.Scanner;

public class Views {

    /**
     * @brief A method that initialized the board and plays through the game
     *
     * @details The method uses the Java Scanner to take in input from the player
     * and transforms it to moves on the game board. If the input in WASD, then the
     * game follows and shifts and merged the cells appropriately as per the rules of
     * 2048. The score also updates with each merge done on the board. If the user inputs
     * "N" or "n", then a new game is initialized with a new BoardT. If the user inputs an
     * invalid input, a prompt shows to the user that the input is invalid and the user is
     * to give a valid input. If there are no possible moves left, the game is declared to
     * be over and the user is shown their score and a "Game Over" screen.
     */
    public static void playGame() {
        System.out.println(" ___   ___    __   ___ ");
        System.out.println("(__ \\ / _ \\  /  | ( _ )");
        System.out.println(" / _/( (_) )(_  _)/ _ \\");
        System.out.println("(____)\\___/   (_) \\___/");

        System.out.println("Use keys WASD to play");
        System.out.println("Press n to restart the game");
        System.out.println("@xxxx[{::::::::::::::>");



        BoardT board = new BoardT();
        Model model = new Model(board);


        model.addStartTile();

        System.out.println(model.getBoardT().Print_Board());

        Scanner entry = new Scanner(System.in);
        String move_board = entry.nextLine();
        move_board = move_board.toLowerCase();


        while (!model.getBoardT().get_GameOver()) {
            boolean merged = false;
            boolean shifted = false;

            System.out.println("Use WASD keys to make a move");
            if (move_board.equals("w")) {
                shifted = model.shiftup();
                merged = model.mergeup();
                System.out.println("Nice Move!");

            } else if (move_board.equals("a")) {
                shifted = model.shiftleft();
                merged = model.mergeleft();
            } else if (move_board.equals("s")) {
                shifted = model.shiftdown();
                merged = model.mergedown();
            } else if (move_board.equals("d")) {
                shifted = model.shiftright();
                merged = model.mergeright();

            } else if (move_board.equals("n")) {

                board = new BoardT();
                model = new Model(board);
                model.addStartTile();
            }
            else {
                System.out.println("Press N to start a new game");
                System.out.println("_____________");
                System.out.println("INVALID INPUT");
                System.out.println("_____________");

            }

            if (model.isGameWon()) {
                board.Print_Board();
                System.out.println("YOU REACHED 2048");
                System.out.println("Your score is" + model.getBoardT().getScore());
            } else {
                if (shifted || merged){
                    model.addRandomTile();
                }
                System.out.println(board.Print_Board());
            }

            if (!model.isMergePossible() && !model.isCellAvailable()) {
                System.out.println("GAME OVER");
                System.out.println("Your score is:" + model.getBoardT().getScore());
                model.getBoardT().set_GameOver(true);
                break;
            }
            move_board = entry.nextLine();
            move_board = move_board.toLowerCase();
        }
    }
}
