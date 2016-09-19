package au.edu.uq.smartass.question;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for ArithmeticSequenceSumOfNTermsModule.
 */
public class ArithmeticSequenceSumOfNTermsModuleTest {

    /**
     * Create a global instance of ArithmeticSequenceSumOfNTermsModule using known values,
     * provides a known output to test against.
     */
    ArithmeticSequenceSumOfNTermsModule a = new ArithmeticSequenceSumOfNTermsModule(2, 4, 40);

    /**
     * Create a global instance of ArithmeticSequenceSumOfNTermsModule using random values.
     */
    ArithmeticSequenceSumOfNTermsModule b = new ArithmeticSequenceSumOfNTermsModule();

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
    public void testArithmeticSequenceSumOfNTermsModule() throws Exception {
        try {
            Constructor<ArithmeticSequenceSumOfNTermsModule> constructor
                    = ArithmeticSequenceSumOfNTermsModule.class.getConstructor();
            assert(true);
        } catch (NoSuchMethodException ex){
            fail();
        }
    }

    /**
     * Test the generated properties against known values.
     */
    @Test
    public void testGeneratedProperties() {
        assertEquals(10, a.numC);
        assertEquals(4, a.diff);
        assertEquals(3200, a.result);
    }

    /**
     * Test to compare the expected "Question" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionQuestion() {
        String expected = "Let $2,6,10$ be an arithmetic sequence. Determine the sum of the first $40$ terms in the sequence.\\\\";
        String actual = a.getSection("question");
        assertEquals(expected, actual);
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionSolution() {
        String expected = "$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=6-2=4$ and $a=2$.\\\\Therefore $S_{40}=\\dfrac{40}{2}(2\\cdot 2 +(40-1)\\cdot 4)$\\\\$=20(4+39 \\cdot4)$\\\\$=3200$";
        String actual = a.getSection("solution");
        assertEquals(expected, actual);
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionAnswer() {
        String expected = "$=3200$";
        String actual = a.getSection("answer");
        assertEquals(expected, actual);
    }

    /**
     * Test to compare the expected "Question" LaTeX output against an expected string using random values.
     */
    @Test
    public void testRandomIntsQuestion() {
        int numA =  b.numA,
            diff = b.diff,
            term = b.term;

        ArithmeticSequenceSumOfNTermsModule c = new ArithmeticSequenceSumOfNTermsModule(numA, diff, term);
        assertEquals(b.getSection("question"), c.getSection("question"));

        try {
            writeTexFile("question_output.tex", c.getSection("question"));
        } catch (Exception ex) {
            TestCase.fail();
        }
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against an expected string using random values.
     */
    @Test
    public void testRandomIntsSolution() {
        int numA =  b.numA,
            diff = b.diff,
            term = b.term;

        ArithmeticSequenceSumOfNTermsModule c = new ArithmeticSequenceSumOfNTermsModule(numA, diff, term);
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
        int numA = b.numA,
            diff = b.diff,
            term = b.term;

        ArithmeticSequenceSumOfNTermsModule c = new ArithmeticSequenceSumOfNTermsModule(numA, diff, term);
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
