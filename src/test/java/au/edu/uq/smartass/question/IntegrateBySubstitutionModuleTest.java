package au.edu.uq.smartass.question;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
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

    @Test
    public void IntegrateBySubstitutionModuleConstructor() throws Exception {
        constructionPass(1,1,1,0,1);
        constructionPass(100,100,100,99,100);

        constructionFail(0,1,1,1,1);
        constructionFail(1,1,1,1,1);
        constructionFail(100,100,100,100,100);
        constructionFail(100,100,100,99,101);
    }

    private void constructionPass(final int a, final int m, final int b, final int n, final int S) {
        try {
            new IntegrateBySubstitutionModule(a,m,b,n,S);
            assert(true);
        } catch (Exception ex) {
            fail();
        }
    }

    private void constructionFail(final int a, final int m, final int b, final int n, final int S) {
        try {
            new IntegrateBySubstitutionModule(a,m,b,n,S);
            fail();
        } catch (AssertionError er) {
            assert(true);
        }
    }

    @Test
    public void testQuestionLaTeX_sample() throws Exception {
        IntegrateBySubstitutionModule integral = new IntegrateBySubstitutionModule(1, 2, 1, 1, 1);

        String expectedQ =
                "Determine $\\displaystyle\\int\\dfrac{2x+1}{x^{2}+x}\\,\\mathrm{d}x$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "\\text{Let }u&=x^{2}+x.\\\\\n" +
                "\\text{Then }\\dfrac{\\mathrm{d}u}{\\mathrm{d}x}&=2x+1.\\\\\n" +
                "\\text{So }\\mathrm{d}u&=2x+1\\,\\mathrm{d}x.\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\displaystyle\\int\\dfrac{2x+1}{x^{2}+x}\\,\\mathrm{d}x\n" +
                "&=\\displaystyle\\int\\dfrac{1}{u}\\,\\mathrm{d}u\\\\\n" +
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

    @Test
    public void testQuestionLaTeX_small() throws Exception {
        IntegrateBySubstitutionModule integral = new IntegrateBySubstitutionModule(1, 1, 1, 0, 1);

        String expectedQ =
                "Determine $\\displaystyle\\int\\dfrac{1}{x+1}\\,\\mathrm{d}x$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "\\text{Let }u&=x+1.\\\\\n" +
                "\\text{Then }\\dfrac{\\mathrm{d}u}{\\mathrm{d}x}&=1.\\\\\n" +
                "\\text{So }\\mathrm{d}u&=1\\,\\mathrm{d}x.\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\displaystyle\\int\\dfrac{1}{x+1}\\,\\mathrm{d}x\n" +
                "&=\\displaystyle\\int\\dfrac{1}{u}\\,\\mathrm{d}u\\\\\n" +
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

    @Test
    public void testQuestionLaTeX_big() throws Exception {
        IntegrateBySubstitutionModule integral =
                new IntegrateBySubstitutionModule(10, 10, 10, 9, 10);

        String expectedQ =
                "Determine $\\displaystyle\\int\\dfrac{1000x^{9}+900x^{8}}{10x^{10}+10x^{9}}\\,\\mathrm{d}x$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "\\text{Let }u&=10x^{10}+10x^{9}.\\\\\n" +
                "\\text{Then }\\dfrac{\\mathrm{d}u}{\\mathrm{d}x}&=100x^{9}+90x^{8}.\\\\\n" +
                "\\text{So }\\mathrm{d}u&=100x^{9}+90x^{8}\\,\\mathrm{d}x.\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\displaystyle\\int\\dfrac{1000x^{9}+900x^{8}}{10x^{10}+10x^{9}}\\,\\mathrm{d}x\n" +
                "&=\\displaystyle\\int\\dfrac{10}{u}\\,\\mathrm{d}u\\\\\n" +
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