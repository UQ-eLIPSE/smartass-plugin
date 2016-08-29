package au.edu.uq.smartass.auxiliary;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

public class SmartGraphTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Tests a basic input
     */
    @Test
    public void testGraphOutput() {

        String expected = "{ \\sffamily \\setlength{\\unitlength}{0.08mm} \\begin{picture}(2000,380) \n" +
                " \\put(0,50){ \\thicklines\\line(1,0){1800}} \\put(20,50){\\thicklines\\vector(-1,0){20}}  \n" +
                " \\put(1800,50){\\thicklines\\vector(1,0){20}} \n" +
                "\\multiput(50,40)(170,0){11}{\\line(0,1){20}} \n" +
                "\\put(50,10){\\makebox(0,0){-2}}\n" +
                "\\put(220,10){\\makebox(0,0){-1}}\n" +
                "\\put(390,10){\\makebox(0,0){0}}\n" +
                "\\put(560,10){\\makebox(0,0){1}}\n" +
                "\\put(730,10){\\makebox(0,0){2}}\n" +
                "\\put(900,10){\\makebox(0,0){3}}\n" +
                "\\put(1070,10){\\makebox(0,0){4}}\n" +
                "\\put(1240,10){\\makebox(0,0){5}}\n" +
                "\\put(1410,10){\\makebox(0,0){6}}\n" +
                "\\put(1580,10){\\makebox(0,0){7}}\n" +
                "\\put(1750,10){\\makebox(0,0){8}}\n" +
                "\\put(390,50){\\circle*{35}}\n" +
                "\\put(1240,50){\\circle{35}}\n" +
                "\\qbezier(390,50)(815,300)(1240,50)\n" +
                "\\end{picture}}";

        String actual = SmartGraph.drawInterval("0", true, "5", false);
        assertEquals(expected, actual);
    }

    /**
     * Tests for large output
     */
    @Test
    public void testLargeOutput() {
        String expected = "{ \\sffamily \\setlength{\\unitlength}{0.08mm} \\begin{picture}(2000,380) \n" +
                " \\put(0,50){ \\thicklines\\line(1,0){1800}} \\put(20,50){\\thicklines\\vector(-1,0){20}}  \n" +
                " \\put(1800,50){\\thicklines\\vector(1,0){20}} \n" +
                "\\multiput(50,40)(170,0){11}{\\line(0,1){20}} \n" +
                "\\put(50,10){\\makebox(0,0){-5000}}\n" +
                "\\put(220,10){\\makebox(0,0){-4000}}\n" +
                "\\put(390,10){\\makebox(0,0){-3000}}\n" +
                "\\put(560,10){\\makebox(0,0){-2000}}\n" +
                "\\put(730,10){\\makebox(0,0){-1000}}\n" +
                "\\put(900,10){\\makebox(0,0){0}}\n" +
                "\\put(1070,10){\\makebox(0,0){1000}}\n" +
                "\\put(1240,10){\\makebox(0,0){2000}}\n" +
                "\\put(1410,10){\\makebox(0,0){3000}}\n" +
                "\\put(1580,10){\\makebox(0,0){4000}}\n" +
                "\\put(1750,10){\\makebox(0,0){5000}}\n" +
                "\\put(730,50){\\circle{35}}\n" +
                "\\put(1070,50){\\circle{35}}\n" +
                "\\qbezier(730,50)(900,300)(1070,50)\n" +
                "\\end{picture}}";

        String actual = SmartGraph.drawInterval("-1000", false, "1000", false);
        assertEquals(expected, actual);
    }

    /**
     * Tests if both bounds are infinity
     */
    @Test
    public void testInfiniteBounds() {
        String expected = "\\\\Interval includes all numbers.\\\\";
        String actual = SmartGraph.drawInterval("-9999999999999999999", false, "9999999999999999999999", false);
        assertEquals(expected, actual);
    }
}
