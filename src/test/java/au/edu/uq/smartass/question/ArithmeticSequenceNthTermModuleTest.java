package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Constructor;
import static org.junit.Assert.*;

/**
 * Created by uqamoon1 on 13/09/2016.
 * Tests for ArithmeticSequenceNthTermModule.
 */
public class ArithmeticSequenceNthTermModuleTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }


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

    @Test
    public void testGeneratedProperties() {
        ArithmeticSequenceNthTermModule a = new ArithmeticSequenceNthTermModule(2, 6, 40);
        assertEquals(10, a.numC);
        assertEquals(4, a.diff);
        assertEquals(158, a.result);
    }

    @Test
    public void testGetSectionQuestion() {
        ArithmeticSequenceNthTermModule a = new ArithmeticSequenceNthTermModule(2, 6, 40);
        String expected = "Let $2,6,10$ be an arithmetic sequence. Determine the $40$th term in the sequence.\\\\";
        String actual = a.getSection("question");
        assertEquals(expected, actual);
    }

    @Test
    public void testGetSectionSolution() {
        ArithmeticSequenceNthTermModule a = new ArithmeticSequenceNthTermModule(2, 6, 40);
        String expected = "$a_n=a+(n-1)d$, where $d=6-2=4$ and $a=2$.\\\\Therefore $a_{40}=2+(40-1)\\cdot 4$\\\\$=2+39 \\cdot4$\\\\$=158$";
        String actual = a.getSection("solution");
        assertEquals(expected, actual);
    }

    @Test
    public void testRandomIntsQuestion() {
        ArithmeticSequenceNthTermModule a = new ArithmeticSequenceNthTermModule();  // Create an instance of ArithmeticSequenceNthModule to get random values.
        int numA = a.numA, numB = a.numB, numC = a.numC, term = a.term, diff = a.diff, result = a.result;
        String ord = a.getOrdinal(term);
        ArithmeticSequenceNthTermModule b = new ArithmeticSequenceNthTermModule(numA, numB, term);
        String expected = "Let $" + numA + "," + numB + "," + numC + "$ be an arithmetic sequence. Determine the $" + term + "$" + ord + " term in the sequence.\\\\";
        String actual = b.getSection("question");
        assertEquals(expected, actual);
    }

    @Test
    public void testRandomIntsSolution() {
        ArithmeticSequenceNthTermModule a = new ArithmeticSequenceNthTermModule();  // Create an instance of ArithmeticSequenceNthModule to get random values.
        int numA = a.numA, numB = a.numB, numC = a.numC, term = a.term, diff = a.diff, result = a.result;
        ArithmeticSequenceNthTermModule b = new ArithmeticSequenceNthTermModule(numA, numB, term);
        String expected = "$a_n=a+(n-1)d$, where $d=" + numB + "-" + numA + "=" + diff + "$ and $a=" + numA + "$.\\\\Therefore $a_{" + term + "}=" + numA + "+(" + term + "-1)\\cdot " + diff + "$\\\\$=" + numA + "+"  + (term -1) + " \\cdot" + diff + "$\\\\$=" + result + "$";
        String actual = b.getSection("solution");
        assertEquals(expected, actual);
    }

}
