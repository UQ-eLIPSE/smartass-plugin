package au.edu.uq.smartass.question;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * Tests for <code>IntegrateBySubstitutionModule</code>
 */
public class IntegrateBySubstitutionModuleTest {

    /**
     * Check for 'public' default constructor.
     *
     * @throws Exception
     */
    @Test
    public void IntegrateBySubstitutionModuleDefault() throws Exception {
        try {
            Constructor<IntegrateBySubstitutionModule> constructor
                    = IntegrateBySubstitutionModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    /**
     * Test various values for the main constructor.
     * @throws Exception
     */
    @Test
    public void IntegrateBySubstitutionModuleConstructor() throws Exception {
        constructionPass(1,1,1,0,1);        // Test with minimum values
        constructionPass(10,10,10,9,10);    // Test with maximum values

        constructionFail(0,1,1,1,1);        // Cannot have a coefficient of 0 (a)
        constructionFail(1,1,1,1,1);        // Power of second term must be less than first (n)
        constructionFail(10,10,10,10,10);   // Power of second term must be less than first (n)
        constructionFail(10,10,10,9,11);    // Multiple must be in range 0 < S <= 10 (S)
    }

    /**
     * Utility function to assert construction successful with valid parameters.
     */
    private void constructionPass(final int a, final int m, final int b, final int n, final int S) {
        try {
            new IntegrateBySubstitutionModule(a,m,b,n,S);
            assert(true);
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Utility function to assert construction fails with invalid parameters.
     */
    private void constructionFail(final int a, final int m, final int b, final int n, final int S) {
        try {
            new IntegrateBySubstitutionModule(a,m,b,n,S);
            fail();
        } catch (AssertionError er) {
            assert(true);
        }
    }

    /**
     * Test LaTeX generated question, solution and answer strings
     * when initialised with:
     *      a = 1, m = 2
     *      b = 1, n = 1
     *      S = 1
     *
     * @throws Exception
     */
    @Test
    public void testQuestionLaTeX_sample() throws Exception {
        IntegrateBySubstitutionModule integral = new IntegrateBySubstitutionModule(1, 2, 1, 1, 1);

        String expectedQ =
                "Determine $\\displaystyle\\int\\dfrac{2x+1}{x^{2}+x}\\,dx$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "\\text{Let }u&=x^{2}+x.\\\\\n" +
                "\\text{Then }\\dfrac{du}{dx}&=2x+1.\\\\\n" +
                "\\text{So }du&=2x+1\\,dx.\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\displaystyle\\int\\dfrac{2x+1}{x^{2}+x}\\,dx\n" +
                "&=\\displaystyle\\int\\dfrac{1}{u}\\,du\\\\\n" +
                "&=\\ln|u|+C\\\\\n" +
                "&=\\ln|x^{2}+x|+C\n" +
                "\\end{align*}";
        String actualS = integral.getSection("Solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA =
                "$\\ln|x^{2}+x|+C$";
        String actualA = integral.getSection("Answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);

    }

    /**
     * Test LaTeX generated question, solution and answer strings
     * when initialised with:
     *      a = 1, m = 2
     *      b = 1, n = 0
     *      S = 1
     * Correct formatting of power^0 term.
     *
     * @throws Exception
     */
    @Test
    public void testQuestionLaTeX_small() throws Exception {
        IntegrateBySubstitutionModule integral = new IntegrateBySubstitutionModule(1, 1, 1, 0, 1);

        String expectedQ =
                "Determine $\\displaystyle\\int\\dfrac{1}{x+1}\\,dx$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "\\text{Let }u&=x+1.\\\\\n" +
                "\\text{Then }\\dfrac{du}{dx}&=1.\\\\\n" +
                "\\text{So }du&=1\\,dx.\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\displaystyle\\int\\dfrac{1}{x+1}\\,dx\n" +
                "&=\\displaystyle\\int\\dfrac{1}{u}\\,du\\\\\n" +
                "&=\\ln|u|+C\\\\\n" +
                "&=\\ln|x+1|+C\n" +
                "\\end{align*}";
        String actualS = integral.getSection("Solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA =
                "$\\ln|x+1|+C$";
        String actualA = integral.getSection("Answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }

    /**
     * Test LaTeX generated question, solution and answer strings
     * when initialised with:
     *      a = 10, m = 10
     *      b = 10, n = 9
     *      S = 10
     * Check for correct formatting for numbers with multiple digits.
     * (This is a frequent problem with LaTeX formula.
     *
     * @throws Exception
     */
    @Test
    public void testQuestionLaTeX_big() throws Exception {
        IntegrateBySubstitutionModule integral =
                new IntegrateBySubstitutionModule(10, 10, 10, 9, 10);

        String expectedQ =
                "Determine $\\displaystyle\\int\\dfrac{1000x^{9}+900x^{8}}{10x^{10}+10x^{9}}\\,dx$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "\\text{Let }u&=10x^{10}+10x^{9}.\\\\\n" +
                "\\text{Then }\\dfrac{du}{dx}&=100x^{9}+90x^{8}.\\\\\n" +
                "\\text{So }du&=100x^{9}+90x^{8}\\,dx.\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\displaystyle\\int\\dfrac{1000x^{9}+900x^{8}}{10x^{10}+10x^{9}}\\,dx\n" +
                "&=\\displaystyle\\int\\dfrac{10}{u}\\,du\\\\\n" +
                "&=10\\ln|u|+C\\\\\n" +
                "&=10\\ln|10x^{10}+10x^{9}|+C\n" +
                "\\end{align*}";
        String actualS = integral.getSection("Solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA =
                "$10\\ln|10x^{10}+10x^{9}|+C$";
        String actualA = integral.getSection("Answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }
}