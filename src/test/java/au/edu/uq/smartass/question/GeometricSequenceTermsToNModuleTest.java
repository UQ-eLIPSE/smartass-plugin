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

    /**
     * Create a global instance of GeometricSequenceTermsToNModule using random values.
     */
    private GeometricSequenceTermsToNModule b = new GeometricSequenceTermsToNModule();

    int numA = b.numA,
        ratio = b.ratio,
        term = b.term;

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
     * Test the generated properties against known values.
     */
    @Test
    public void testGeneratedProperties() {
        assertEquals(2, a.numA);
        assertEquals(6, a.numB);
        assertEquals(18, a.numC);
        assertEquals(3, a.ratio);
        assertEquals(15, a.term);
        assertEquals(9565938, a.result);
    }

    /**
     * Test to compare the expected "Question" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionQuestion() {
        String expected = "Let $2,6,18$ be a geometric sequence. Determine the $15$th term in the sequence.\\\\";
        String actual = a.getSection("question");
        assertEquals(actual, expected);
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionSolution() {
        String expected = "$a_n=ar^{n-1}$, where $r=6\\div2=3$ and $a=2$.\\\\so $a_{15}=2\\cdot 3^{14}$\\\\$=9565938$";
        String actual = a.getSection("solution");
        assertEquals(expected, actual);
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against a know string using known values.
     */
    @Test
    public void testGetSectionAnswer() {
        String expected = "$=9565938$";
        assertEquals(expected, a.getSection("answer"));
    }

    /**
     * Test to compare the expected "Question" LaTeX output against an expected string using random values.
     */
    @Test
    public void testRandomIntsQuestion() {
        GeometricSequenceTermsToNModule c = new GeometricSequenceTermsToNModule(numA, ratio, term);
        assertEquals(b.getSection("question"), c.getSection("question"));

        try {
            writeTexFile("question_output.tex", c.getSection("question"));
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against an expected string using random values.
     */
    @Test
    public void testRandomIntsSolution() {
        GeometricSequenceTermsToNModule c = new GeometricSequenceTermsToNModule(numA, ratio, term);
        assertEquals(b.getSection("solution"), c.getSection("solution"));
        try {
            writeTexFile("solution_output.tex", c.getSection("solution"));
        } catch (Exception ex) {

        }
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against an expected string using random values.
     */
    @Test
    public void testRandomIntsAnswer() {
        GeometricSequenceTermsToNModule c = new GeometricSequenceTermsToNModule(numA, ratio, term);
        assertEquals(b.getSection("answer"), c.getSection("answer"));
        try {
            writeTexFile("answer_output.tex", c.getSection("answer"));
        } catch (Exception ex) {

        }
    }

    private void writeTexFile(String filename, String str) throws Exception {
        Files.write(Paths.get("./" + filename), str.getBytes());
    }

}
