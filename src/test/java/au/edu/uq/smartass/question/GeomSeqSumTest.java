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
    public void testGeomSeqSumModuleDefault() throws Exception {
        try {
            Constructor<GeomSeqSumModule> constructor
                    = GeomSeqSumModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testGetSection() throws Exception {
        GeomSeqSumModule geom = new GeomSeqSumModule(2, 3, 20);

        String expectedQuestion =
                "Let $2, 6, 18$ be the first three terms of a geometric sequence. " +
                "What is the sum of the first 20 terms?";
        System.out.println(expectedQuestion);
        assertEquals(expectedQuestion, geom.getSection("question"));

        String expectedSolution =
			"\\begin{align*}\n" +
			"S_n&=\\dfrac{a(r^n-1)}{r-1}\\text{, where }r=6\\div2=3\\text{ and }a=2.\\\\\n" +
			"S_{20}&=\\dfrac{2(3^{20-1})}{20-1}\\\\\n" +
			"&=\\dfrac{2\\cdot3^{19}}{19}\\\\\n" +
			"&\\approx122343312.32\n" +
			"\\end{align*}";
        System.out.println(expectedSolution);
        assertEquals(expectedSolution, geom.getSection("solution"));

        String expectedAnswer = String.format("$%.2f$", 122343312.32);
        System.out.println(expectedAnswer);
        assertEquals(expectedAnswer, geom.getSection("answer"));
    }
}
