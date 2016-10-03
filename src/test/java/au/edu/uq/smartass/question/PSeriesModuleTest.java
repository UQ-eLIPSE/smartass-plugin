package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class PSeriesModuleTest {

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
        PSeriesModule p = new PSeriesModule(1);

        String expected = "Does $\\sum\\limits_{n=1}^{\\infty} \\frac{1}{n^{\\frac{1}{4}}}$ converge? Explain.";
        System.out.println(expected);
        String actual = p.getSection("question");
        assertEquals(expected, actual);


        String expectedSolution = "No, it doesn't, by the $p$-series test. Since $p<1$, the series will diverge.";
        System.out.println(expectedSolution);
        String actualSolution = p.getSection("solution");
        assertEquals(expectedSolution, actualSolution);
    }
}
