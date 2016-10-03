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
     * Test to compare the expected "Question" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionQuestion() {
        String expected =
                "Let $2,6,10$ be the first three terms of a finite sequence. " +
                "Determine the sum of the first $40$ terms in the sequence.";
        //System.out.println(expected);
        assertEquals(expected, a.getSection("question"));
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionSolution() {
        String expected =
				"\\begin{align*}" +
				"S_n&=\\dfrac{n}{2}(2a+(n-1)d)\\text{, where }d=6-2=4\\text{ and }a=2.\\\\" +
				"\\text{Therefore }S_{40}&=\\dfrac{40}{2}(2\\cdot 2 +(40-1)\\cdot 4)\\\\" +
				"&=20(4+39\\cdot4)\\\\" +
				"&=3200" +
				"\\end{align*}";
        //System.out.println(expected);
        assertEquals(expected, a.getSection("solution"));
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionAnswer() {
        String expected = "$3200$";
        //System.out.println(expected);
        assertEquals(expected, a.getSection("answer"));
    }
}
