package au.edu.uq.smartass.question;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 */
public class IntegrateByPartsModuleTest {

    /**
     * Check for 'public' default constructor.
     *
     * @throws Exception
     */
    @Test
    public void IntegrateByPartsModuleDefault() throws Exception {
        try {
            Constructor<IntegrateByPartsModule> constructor
                    = IntegrateByPartsModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void IntegrateByPartsModuleConstructor() throws Exception {
        constructionPass(1);
        constructionPass(2);
        constructionPass(5);
        constructionPass(9);
        constructionPass(10);

        constructionFail(-1);
        constructionFail( 0);
        constructionFail(11);
        constructionFail(12);
    }

    private void constructionPass(int a) {
        try {
            new IntegrateByPartsModule(a);
            assert(true);
        } catch (Exception ex) {
            fail();
        }
    }

    private void constructionFail(int a) {
        try {
            new IntegrateByPartsModule(a);
            fail();
        } catch (AssertionError er) {
            assert(true);
        }
    }

    @Test
    public void testQuestionLaTeX_1() throws Exception {
        IntegrateByPartsModule integral = new IntegrateByPartsModule(1);

        String expectedQ =
                "Determine $\\int{x}\\ln{x}\\,dx$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "Let's use integration by parts as the two functions are not\n" +
                "related to each other in terms of derivatives.\n" +
                "\\begin{align*}\n" +
                "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" +
                "&\\text{Let }v'=x\\text{, then }v=\\dfrac{1}{1+1}x^{1+1}.\\\\\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" +
                "\\text{So }\\int{x}\\ln{x}\\,dx&=\\dfrac{1}{1+1}x^{1+1}\\cdot\\ln{x}\n" +
                "-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{1+1}x^{1+1}\\,dx\\\\\n" +
                "&=\\dfrac{1}{2}\\cdot{x}^{2}\\ln{x}\n" +
                "-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{2}x^{2}\\,dx\\\\\n" +
                "&=\\dfrac{1}{2}\\cdot{x}^{2}\\ln{x}\n" +
                "-\\dfrac{1}{2}\\int{x}\\,dx\\\\\n" +
                "&=\\dfrac{1}{2}\\cdot{x}^{2}\\ln{x}\n" +
                "-\\dfrac{1}{2}\\cdot\\dfrac{1}{1+1}\\cdot{x}^{1+1}+C\\\\\n" +
                "&=\\dfrac{1}{2}\\cdot{x}^{2}\\ln{x}\n" +
                "-\\left(\\dfrac{1}{2}\\right)^2x^{2}+C\\\\\n" +
                "&=\\dfrac{1}{2}\\cdot{x}^{2}\\ln{x}-\\dfrac{1}{4}\\cdot{x}^{2}+C\n" +
                "\\end{align*}";
        String actualS = integral.getSection("Solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA =
                "$\\dfrac{1}{2}\\cdot{x}^{2}\\ln{x}-\\dfrac{1}{4}\\cdot{x}^{2}+C$";
        String actualA = integral.getSection("Answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }

    @Test
    public void testQuestionLaTeX_5() throws Exception {
        IntegrateByPartsModule integral = new IntegrateByPartsModule(5);

        String expectedQ =
                "Determine $\\int{x}^{5}\\ln{x}\\,dx$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "Let's use integration by parts as the two functions are not\n" +
                        "related to each other in terms of derivatives.\n" +
                        "\\begin{align*}\n" +
                        "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" +
                        "&\\text{Let }v'=x^{5}\\text{, then }v=\\dfrac{1}{5+1}x^{5+1}.\\\\\n" +
                        "\\end{align*}\n" +
                        "\\begin{align*}\n" +
                        "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" +
                        "\\text{So }\\int{x}^{5}\\ln{x}\\,dx&=\\dfrac{1}{5+1}x^{5+1}\\cdot\\ln{x}\n" +
                        "-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{5+1}x^{5+1}\\,dx\\\\\n" +
                        "&=\\dfrac{1}{6}\\cdot{x}^{6}\\ln{x}\n" +
                        "-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{6}x^{6}\\,dx\\\\\n" +
                        "&=\\dfrac{1}{6}\\cdot{x}^{6}\\ln{x}\n" +
                        "-\\dfrac{1}{6}\\int{x}^{5}\\,dx\\\\\n" +
                        "&=\\dfrac{1}{6}\\cdot{x}^{6}\\ln{x}\n" +
                        "-\\dfrac{1}{6}\\cdot\\dfrac{1}{5+1}\\cdot{x}^{5+1}+C\\\\\n" +
                        "&=\\dfrac{1}{6}\\cdot{x}^{6}\\ln{x}\n" +
                        "-\\left(\\dfrac{1}{6}\\right)^2x^{6}+C\\\\\n" +
                        "&=\\dfrac{1}{6}\\cdot{x}^{6}\\ln{x}-\\dfrac{1}{36}\\cdot{x}^{6}+C\n" +
                        "\\end{align*}";
        String actualS = integral.getSection("Solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA =
                "$\\dfrac{1}{6}\\cdot{x}^{6}\\ln{x}-\\dfrac{1}{36}\\cdot{x}^{6}+C$";
        String actualA = integral.getSection("Answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }

    @Test
    public void testQuestionLaTeX_10() throws Exception {
        IntegrateByPartsModule integral = new IntegrateByPartsModule(10);

        String expectedQ =
                "Determine $\\int{x}^{10}\\ln{x}\\,dx$.";
        String actualQ = integral.getSection("Question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "Let's use integration by parts as the two functions are not\n" +
                "related to each other in terms of derivatives.\n" +
                "\\begin{align*}\n" +
                "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" +
                "&\\text{Let }v'=x^{10}\\text{, then }v=\\dfrac{1}{10+1}x^{10+1}.\\\\\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" +
                "\\text{So }\\int{x}^{10}\\ln{x}\\,dx&=\\dfrac{1}{10+1}x^{10+1}\\cdot\\ln{x}\n" +
                "-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{10+1}x^{10+1}\\,dx\\\\\n" +
                "&=\\dfrac{1}{11}\\cdot{x}^{11}\\ln{x}\n" +
                "-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{11}x^{11}\\,dx\\\\\n" +
                "&=\\dfrac{1}{11}\\cdot{x}^{11}\\ln{x}\n" +
                "-\\dfrac{1}{11}\\int{x}^{10}\\,dx\\\\\n" +
                "&=\\dfrac{1}{11}\\cdot{x}^{11}\\ln{x}\n" +
                "-\\dfrac{1}{11}\\cdot\\dfrac{1}{10+1}\\cdot{x}^{10+1}+C\\\\\n" +
                "&=\\dfrac{1}{11}\\cdot{x}^{11}\\ln{x}\n" +
                "-\\left(\\dfrac{1}{11}\\right)^2x^{11}+C\\\\\n" +
                "&=\\dfrac{1}{11}\\cdot{x}^{11}\\ln{x}-\\dfrac{1}{121}\\cdot{x}^{11}+C\n" +
                "\\end{align*}";
        String actualS = integral.getSection("Solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA =
                "$\\dfrac{1}{11}\\cdot{x}^{11}\\ln{x}-\\dfrac{1}{121}\\cdot{x}^{11}+C$";
        String actualA = integral.getSection("Answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }
}