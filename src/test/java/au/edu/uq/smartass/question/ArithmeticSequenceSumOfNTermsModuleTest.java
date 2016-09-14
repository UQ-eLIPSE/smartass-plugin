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
        ArithmeticSequenceSumOfNTermsModule a = new ArithmeticSequenceSumOfNTermsModule(2, 8, 40);
        assertEquals(14, a.numC);
        assertEquals(6, a.diff);
        assertEquals(4760, a.result);
    }

    @Test
    public void testGetSectionQuestion() {
        ArithmeticSequenceSumOfNTermsModule a = new ArithmeticSequenceSumOfNTermsModule(2, 6, 40);
        String expected = "Let $2,6,10$ be an arithmetic sequence. Determine the sum of the first $40$ terms in the sequence.\\\\";
        String actual = a.getSection("question");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSectionSolution() {
        ArithmeticSequenceSumOfNTermsModule a = new ArithmeticSequenceSumOfNTermsModule(2, 6, 40);
        String expected = "$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=6-2=4$ and $a=2$.\\\\Therefore $S_{40}=\\dfrac{40}{2}(2\\cdot 2 +(40-1)\\cdot 4)$\\\\$=20(4+39 \\cdot4)$\\\\$=3200$";
        String actual = a.getSection("solution");
        assertEquals(expected, actual);
    }

    @Test
    public void testRandomIntsQuestion() {
        ArithmeticSequenceSumOfNTermsModule a = new ArithmeticSequenceSumOfNTermsModule();
        int numA =  a.numA, numB = a.numB, numC = a.numC, term = a.term;
        ArithmeticSequenceSumOfNTermsModule b = new ArithmeticSequenceSumOfNTermsModule(numA, numB, term);
        String expected = "Let $" + numA + "," + numB + "," + numC + "$ be an arithmetic sequence. Determine the sum of the first $" + term + "$ terms in the sequence.\\\\";
        String actual = b.getSection("question");
        assertEquals(expected, actual);
    }

    @Test
    public void testRandomIntsSolution() {
        ArithmeticSequenceSumOfNTermsModule a = new ArithmeticSequenceSumOfNTermsModule();
        int numA =  a.numA, numB = a.numB, numC = a.numC, term = a.term, diff = a.diff, result = a.result;
        ArithmeticSequenceSumOfNTermsModule b = new ArithmeticSequenceSumOfNTermsModule(numA, numB, term);
        String expected = "$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=" + numB + "-" + numA + "=" + diff + "$ and $a=" + numA + "$.\\\\Therefore $S_{" + term + "}=\\dfrac{" + term + "}{2}(2\\cdot " + numA + " +(" + term + "-1)\\cdot " + diff + ")$\\\\$=" + (term / 2) + "(" + (2 * numA) + "+" + (term - 1) + " \\cdot" + diff + ")$\\\\$=" + result + "$";
        String actual = b.getSection("solution");
        assertEquals(expected, actual);
    }
}
