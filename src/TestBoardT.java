/**
 * Author: Mohamed Aly
 * Revised: April 15, 2021
 * Description: Testing BoardT methods
 */

import static org.junit.Assert.*;
import  org.junit.*;


public class TestBoardT {

    private BoardT b1;

    @Before
    public void setUp(){
        b1 = new BoardT();
    }

    @After
    public void tearDown(){
        b1 = null;
    }

    @Test
    public void testgetSize(){
        assertTrue(b1.getSize() == 4);
    }

    @Test
    public void testgetBoard(){

        for (TileT[] row: b1.getBoard()){
            for (TileT tile: row){
                assertTrue(tile.getVal() == 0);
            }
        }
    }

    @Test
    public void testgetScore(){
        assertTrue(b1.getScore() == 0);
    }

    @Test
    public void testsetScore(){
        b1.setScore(8);
        assertTrue(b1.getScore() == 8);
    }

    @Test
    public void testget_Board_val_at(){
        for (int i = 0; i < b1.getSize(); i++){
            for (int j = 0; j < b1.getSize(); j++){
                assertTrue(b1.get_Board_val_at(i,j) == 0);
            }
        }
    }

    @Test
    public void testset_Board_val_at(){
        b1.set_Board_val_at(0,3,4);
        assertTrue(b1.get_Board_val_at(0,3) == 4);
    }

    @Test
    public void testset_GameOver(){
        b1.set_GameOver(true);
        assertTrue(b1.get_GameOver() == true);
    }
}
