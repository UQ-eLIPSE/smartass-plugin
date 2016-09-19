package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by uqamoon1 on 13/09/2016.
 * Tests for ArithmeticSequenceNthTermModule.
 */
public class ArithmeticSequenceNthTermModuleTest {

    /**
     * Create a global instance of ArithmeticSequenceNTermsModule using known values,
     * provides a known output to test against.
     */
    ArithmeticSequenceNthTermModule a = new ArithmeticSequenceNthTermModule(2, 4, 40);

    /**
     * Create a global instance of ArithmeticSequenceSumOfNTermsModule using random values.
     */
    ArithmeticSequenceNthTermModule b = new ArithmeticSequenceNthTermModule();  // Create an instance of ArithmeticSequenceNthModule to get random values.

    int numA = b.numA,
        term = b.term,
        diff = b.diff;

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
    public void testArithmeticSequenceNthTermModule() throws Exception {
        try {
           Constructor<ArithmeticSequenceNthTermModule> constructor
                   = ArithmeticSequenceNthTermModule.class.getConstructor();
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
        assertEquals(6, a.numB);
        assertEquals(10, a.numC);
        assertEquals(158, a.result);
    }

    /**
     * Test to compare the expected "Question" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionQuestion() {
        String expected = "Let $2,6,10$ be an arithmetic sequence. Determine the $40$th term in the sequence.\\\\";
        String actual = a.getSection("question");
        assertEquals(expected, actual);
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionSolution() {
        String expected = "$a_n=a+(n-1)d$, where $d=6-2=4$ and $a=2$.\\\\Therefore $a_{40}=2+(40-1)\\cdot 4$\\\\$=2+39 \\cdot4$\\\\$=158$";
        String actual = a.getSection("solution");
        assertEquals(expected, actual);
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionAnswer() {
        String expected = "$=158$";
        String actual = a.getSection("answer");
        assertEquals(expected, actual);
    }

    /**
     * Test to compare the expected "Question" LaTeX output against an expected string using random values.
     */
    @Test
    public void testRandomIntsQuestion() {
        ArithmeticSequenceNthTermModule c = new ArithmeticSequenceNthTermModule(numA, diff, term);
        assertEquals(b.getSection("question"), c.getSection("question"));

        try {
            writeTexFile("question_output.tex", c.getSection("question"));
        } catch (Exception ex) {

        }
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against an expected string using random values.
     */
    @Test
    public void testRandomIntsSolution() {
        ArithmeticSequenceNthTermModule c = new ArithmeticSequenceNthTermModule(numA, diff, term);
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
    public void testRandomIntAnswer() {
        ArithmeticSequenceNthTermModule c = new ArithmeticSequenceNthTermModule(numA, diff, term);
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
