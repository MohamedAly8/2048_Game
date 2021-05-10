/**
 * Author: Mohamed Aly
 * Revised: April 15, 2021
 * Description: Testing the Model Class for the 2048 Game
 */

import static org.junit.Assert.*;
import  org.junit.*;
import java.util.ArrayList;


public class TestModel {

    private BoardT b1;
    private BoardT b2;
    private BoardT b3;
    private Model m1;
    private Model m2;
    private Model m3;

    @Before
    public void setUp(){

        b1 = new BoardT();
        m1 = new Model(b1);
        b2 = new BoardT();
        m2 = new Model(b2);
        b3 = new BoardT();
        m3 = new Model(b3);


//        m1.getBoardT().set_Board_val_at(1,1,2);
//        m1.getBoardT().set_Board_val_at(2,0,2);
//
//        m2.getBoardT().set_Board_val_at(1,1,2);
//        m2.getBoardT().set_Board_val_at(2,2,2);
    }

    @After
    public void tearDown(){
        m1 = null;
        m2 = null;
        m3 = null;

        b1 = null;
        b2 = null;
        b3 = null;
    }

    @Test
    public void testgetBoard(){
        assertEquals(m1.getBoardT(), b1);
    }

    @Test
    public void testaddRandomTile(){
        int tiles_before = m1.availableCells().size();
        m1.addRandomTile();

        assertTrue(m1.availableCells().size()+1 == tiles_before);
    }

    @Test
    public void testaddStartTile(){
        m3.addStartTile();
        int num_unavail_cells = 0;
        for (int i = 0; i < m3.getBoardT().getSize(); i++){
            for (int j = 0; j < m3.getBoardT().getSize(); j++){
                if (m3.getBoardT().get_Board_val_at(i,j) != 0){
                    num_unavail_cells++;
                }
            }
        }
        System.out.println(m3.getBoardT().Print_Board());
        assertTrue(num_unavail_cells ==  2);
    }

    @Test
    public void testRandomAvailableCell(){
        m2.getBoardT().set_Board_val_at(1,1,2);
        m2.getBoardT().set_Board_val_at(2,2,2);

        assertTrue(m2.RandomAvailableCell().getVal() == 0);

    }

    @Test
    public void testisCellAvailable(){
        m1.getBoardT().set_Board_val_at(1,1,2);
        m1.getBoardT().set_Board_val_at(2,0,2);
        assertTrue(m1.isCellAvailable() == true);
    }

    @Test
    public void testavailableCells(){
        m1.getBoardT().set_Board_val_at(1,1,2);
        m1.getBoardT().set_Board_val_at(2,0,2);

        ArrayList<TileT> expected = new ArrayList<TileT>();

        for (TileT[] row: b1.getBoard()){
            for (TileT tile: row){
                if (tile.getVal() == 0){
                    expected.add(tile);
                }
            }
        }
        assertArrayEquals(expected.toArray(), m1.availableCells().toArray());
    }

    @Test
    public void testshiftup(){
        m1.getBoardT().set_Board_val_at(1,1,2);
        m1.getBoardT().set_Board_val_at(2,0,2);
        m1.shiftup();

        assertTrue(m1.getBoardT().get_Board_val_at(0,0) == 2 && m1.getBoardT().get_Board_val_at(0,1) == 2);
    }

    @Test
    public void testshiftleft(){
        m1.getBoardT().set_Board_val_at(1,1,2);
        m1.getBoardT().set_Board_val_at(2,0,2);
        m1.shiftleft();

        assertTrue(m1.getBoardT().get_Board_val_at(1,0) == 2 && m1.getBoardT().get_Board_val_at(2,0) == 2);
    }

    @Test
    public void testshiftdown(){
        m2.getBoardT().set_Board_val_at(1,1,2);
        m2.getBoardT().set_Board_val_at(2,2,2);
        m2.shiftdown();

        assertTrue(m1.getBoardT().get_Board_val_at(3,1) == 2 && m1.getBoardT().get_Board_val_at(3,2) == 2);
    }

    @Test
    public void testshiftright(){
        m2.getBoardT().set_Board_val_at(1,1,2);
        m2.getBoardT().set_Board_val_at(2,2,2);
        m2.shiftright();

        assertTrue(m1.getBoardT().get_Board_val_at(1,3) == 2 && m1.getBoardT().get_Board_val_at(2,3) == 2);
    }

    @Test
    public void testmergeup(){
        m1.getBoardT().set_Board_val_at(1,1,2);
        m1.getBoardT().set_Board_val_at(0,1,2);
        m1.shiftleft();
        m1.mergeup();

        assertTrue(m1.getBoardT().get_Board_val_at(0,0) == 4 && m1.getBoardT().get_Board_val_at(0,1) == 0);
    }

    @Test
    public void testmergeleft(){
        m1.getBoardT().set_Board_val_at(1,1,2);
        m1.getBoardT().set_Board_val_at(2,0,2);
        m1.shiftup();
        m1.mergeleft();

        assertTrue(m1.getBoardT().get_Board_val_at(0,0) == 4 && m1.getBoardT().get_Board_val_at(2,0) == 0);
    }

    @Test
    public void testmergedown(){
        m2.getBoardT().set_Board_val_at(1,1,2);
        m2.getBoardT().set_Board_val_at(2,1,2);
        m2.shiftright();
        m2.mergedown();

        assertTrue(m2.getBoardT().get_Board_val_at(3,3) == 4 && m2.getBoardT().get_Board_val_at(3,2) == 0);
    }

    @Test
    public void testmergeright(){
        m2.getBoardT().set_Board_val_at(1,1,2);
        m2.getBoardT().set_Board_val_at(2,2,2);
        m2.shiftup();
        m2.mergeright();

        assertTrue(m2.getBoardT().get_Board_val_at(0,3) == 4 && m2.getBoardT().get_Board_val_at(0,2) == 0);
    }

    @Test
    public void testisMergePossible_false(){
        m2.getBoardT().set_Board_val_at(1,1,2);
        m2.getBoardT().set_Board_val_at(2,2,2);

        assertTrue(!m2.isMergePossible());
    }

    @Test
    public void testisMergePossible_true(){
        m2.getBoardT().set_Board_val_at(2,1,2);
        m2.getBoardT().set_Board_val_at(2,2,2);

        assertTrue(m2.isMergePossible());
    }

    @Test
    public void testisGameWon_true(){
        m2.getBoardT().set_Board_val_at(3,2,2048);

        assertTrue(m2.isGameWon());
    }

    @Test
    public void testisGameWon_false(){
        m2.getBoardT().set_Board_val_at(2,1,2);
        m2.getBoardT().set_Board_val_at(2,2,2);

        assertTrue(!m2.isGameWon());
    }
}
