/**
 * Author: Mohamed Aly
 * Revised: April 14, 2021
 * Description: Testing TileT for the game board
 */

import static org.junit.Assert.*;
import  org.junit.*;

public class TestTileT {

    private TileT t1;
    private TileT t2;

    @Before
    public void setUp(){
        t1 = new TileT(2,new int[] {2,3});
        t2 = new TileT(4, new int[] {0,0});
    }

    @After
    public void tearDown(){
        t1 = null;
        t2 = null;
    }

    @Test
    public void testgetVal1(){
        assertTrue(t1.getVal() == 2);
    }

    @Test
    public void testgetVal2(){
        assertTrue(t2.getVal() == 4);
    }

    @Test
    public void testsetVal1(){
        t1.setVal(4);
        assertTrue(t1.getVal() == 4);
    }

    @Test
    public void testsetVal2(){
        t2.setVal(2);
        assertTrue(t2.getVal() == 2);
    }

    @Test
    public void testgetPosition1(){
        int[] pos = t1.getPosition();

        assertArrayEquals(new int[] {2,3}, pos);
    }

    @Test
    public void testgetPosition2(){
        int[] pos = t2.getPosition();

        assertArrayEquals(new int[] {0,0}, pos);
    }
}
