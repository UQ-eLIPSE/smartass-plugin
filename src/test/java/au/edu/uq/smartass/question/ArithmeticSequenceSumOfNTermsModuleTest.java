package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by uqamoon1 on 13/09/2016.
 */
public class ArithmeticSequenceSumOfNTermsModuleTest {

    ArithmeticSequenceSumOfNTermsModule a = new ArithmeticSequenceSumOfNTermsModule(2, 4, 40);
    ArithmeticSequenceSumOfNTermsModule b = new ArithmeticSequenceSumOfNTermsModule();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


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

    @Test
    public void testGeneratedProperties() {
        assertEquals(10, a.numC);
        assertEquals(4, a.diff);
        assertEquals(3200, a.result);
    }

    @Test
    public void testGetSectionQuestion() {
        String expected = "Let $2,6,10$ be an arithmetic sequence. Determine the sum of the first $40$ terms in the sequence.\\\\";
        String actual = a.getSection("question");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSectionSolution() {
        String expected = "$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=6-2=4$ and $a=2$.\\\\Therefore $S_{40}=\\dfrac{40}{2}(2\\cdot 2 +(40-1)\\cdot 4)$\\\\$=20(4+39 \\cdot4)$\\\\$=3200$";
        String actual = a.getSection("solution");
        assertEquals(expected, actual);
    }

    @Test
    public void testRandomIntsQuestion() {
        int numA =  b.numA, numB = b.numB, numC = b.numC, term = b.term, diff = b.diff;
        ArithmeticSequenceSumOfNTermsModule c = new ArithmeticSequenceSumOfNTermsModule(numA, diff, term);
        String expected = "Let $" + numA + "," + numB + "," + numC + "$ be an arithmetic sequence. Determine the sum of the first $" + term + "$ terms in the sequence.\\\\";
        String actual = c.getSection("question");
        assertEquals(expected, actual);
    }

    @Test
    public void testRandomIntsSolution() {
        int numA =  b.numA, numB = b.numB, term = b.term, diff = b.diff, result = b.result;
        ArithmeticSequenceSumOfNTermsModule c = new ArithmeticSequenceSumOfNTermsModule(numA, diff, term);
        String expected = "$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=" + numB + "-" + numA + "=" + diff + "$ and $a=" + numA + "$.\\\\Therefore $S_{" + term + "}=\\dfrac{" + term + "}{2}(2\\cdot " + numA + " +(" + term + "-1)\\cdot " + diff + ")$\\\\$=" + (term / 2) + "(" + (2 * numA) + "+" + (term - 1) + " \\cdot" + diff + ")$\\\\$=" + result + "$";
        String actual = c.getSection("solution");
        assertEquals(expected, actual);
    }
}
