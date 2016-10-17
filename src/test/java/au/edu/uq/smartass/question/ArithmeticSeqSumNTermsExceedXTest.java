package au.edu.uq.smartass.question;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 */
public class ArithmeticSeqSumNTermsExceedXTest {

    private ArithmeticSeqSumNTermsExceedX seq = new ArithmeticSeqSumNTermsExceedX(2, 4, 1000);

    @Test
    public void arithmeticSeqSumNTermsExceedXDefault() throws Exception {
        try {
            Constructor<ArithmeticSeqSumNTermsExceedX> constructor
                    = ArithmeticSeqSumNTermsExceedX.class.getConstructor();
            assert(true);
        } catch (NoSuchMethodException ex){
            fail();
        }
    }

    @Test
    public void getSectionQuestion() throws Exception {
        String expected =
                "Let $2,6,10$ be the first three terms of a finite arithmetic sequence.\n" +
                "How many terms are needed for the sum to first exceed $1000$?";
        System.out.println(expected);
        assertEquals(expected, seq.getSection("Question"));
    }

    @Test
    public void getSectionSolution() throws Exception {
        String expected =
				"Let's solve this as an equation then round appropriately.\n" +
				"\\begin{align*}\n" +
				"S_n&=\\dfrac{n}{2}(2a+(n-1)d),\\text{ where }d=6-2=4\\text{ and }a=2.\\\\\n" +
				"\\text{Therefore }1000&=\\dfrac{n}{2}(2\\cdot2+(n-1)\\cdot4)\\\\\n" +
				"1000&=\\dfrac{n}{2}(4+4n-4)\\\\\n" +
				"2000&=n(4n+0)\\\\\n" +
				"0&=4n^2+0n-2000\\\\\n" +
				"n&\\approx22.36\n" +
				"\\end{align*}\n" +
				"So $23$ terms are required.";
        System.out.println(expected);
        assertEquals(expected, seq.getSection("Solution"));
    }

    @Test
    public void getSectionAnswer() throws Exception {
        String expected = String.format("$%d$", 23);
        System.out.println(expected);
        assertEquals(expected, seq.getSection("Answer"));
    }

}