package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Tests for GeometricSequenceTermsModule.
 */
public class GeometricSequenceTermsToNModuleTest {

    /**
     * Create a global instance of GeometricSequenceTermsToNModule using known values,
     * provides a known output to test against.
     */
    private GeometricSequenceTermsToNModule a = new GeometricSequenceTermsToNModule(2, 3, 15);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test to ensure a constructor exists and is accessible.
     * @throws Exception
     */
    @Test
    public void testGeometricSequenceTermsToNModule() throws Exception {
        try {
            Constructor<GeometricSequenceTermsToNModule> constructor = GeometricSequenceTermsToNModule.class.getConstructor();
        } catch (NoSuchMethodException ex) {
           fail();
        }
    }

    /**
     * Test to compare the expected "Question" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionQuestion() {
        String expected =
                "Let $2,6,18$ be the first three terms of a geometric sequence. " +
                "Determine the $15$th term in the sequence.";
        System.out.println(expected);
        assertEquals(expected, a.getSection("question"));
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionSolution() {
        String expected =
				"\\begin{align*}" +
				"a_n&=ar^{n-1}\\text{, where }r=6\\div2=3\\text{ and }a=2.\\\\" +
				"\\text{So }a_{15}&=2\\cdot3^{14}\\\\" +
				"&=9565938" +
				"\\end{align*}";
        System.out.println(expected);
        assertEquals(expected, a.getSection("solution"));
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against a know string using known values.
     */
    @Test
    public void testGetSectionAnswer() {
        String expected = "$9565938$";
        System.out.println(expected);
        assertEquals(expected, a.getSection("answer"));
    }
}
