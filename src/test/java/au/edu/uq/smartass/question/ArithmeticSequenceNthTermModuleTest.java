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
    private ArithmeticSequenceNthTermModule seq = new ArithmeticSequenceNthTermModule(2, 4, 40);


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
     * Test to compare the expected "Question" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionQuestion() {
        String expected =
                "Let $2,6,10$ be the first three terms of a finite arithmetic sequence. " +
                "Determine the $40$th term in the sequence.";
        System.out.println(expected);
        assertEquals(expected, seq.getSection("question"));
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionSolution() {
        String expected =
                "\\begin{align*}\n" +
                "a_n&=a+(n-1)d,\\text{ where }d=6-2=4\\text{ and }a=2.\\\\\n" +
                "\\text{Therefore }a_{40}&=2+(40-1)\\cdot4\\\\\n" +
                "&=2+39\\cdot4\\\\\n" +
                "&=158\n" +
                "\\end{align*}";
        System.out.println(expected);
        assertEquals(expected, seq.getSection("solution"));
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionAnswer() {
        String expected = "$158$";
        System.out.println(expected);
        assertEquals(expected, seq.getSection("answer"));
    }
}
