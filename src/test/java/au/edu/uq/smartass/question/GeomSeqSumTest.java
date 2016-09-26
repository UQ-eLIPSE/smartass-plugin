package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class GeomSeqSumTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Check for 'public' default constructor.
     * 
     * @throws Exception
     */
    @Test
    public void testPSeriesModule() throws Exception {
        try {
            Constructor<PSeriesModule> constructor
                    = PSeriesModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testGetSection() throws Exception {
        GeomSeqSumModule geom = new GeomSeqSumModule(2, 3, 20);

        String expectedQuestion = "Let $2, 6, 18$ be a geometric sequence. What is the sum of the first 20 terms?\\\\";
        assertEquals(geom.getSection("question"), expectedQuestion);

        String expectedSolution = "$S_n=\\dfrac{a(r^n-1)}{r-1}$, where $r=6\\div2=3$ and $a=2$.\\\\$S_{20}=\\dfrac{2(3^{20-1})}{20-1}$\\\\$=\\dfrac{2\\cdot3^{19}}{19}$\\\\$\\approx 122343312.32$\\\\";
        assertEquals(geom.getSection("solution"), expectedSolution);


        String expectedAnswer = "$\\approx 122343312.32$";
        assertEquals(geom.getSection("answer"), expectedAnswer);
    }

    @Test
    public void testGetSectionFail() throws Exception {
        ScalarProductModule dot = new ScalarProductModule();
        try {
            dot.getSection("NonExistantSectionName");
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
}
