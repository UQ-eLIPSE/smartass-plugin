package au.edu.uq.smartass.question;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 */
public class VolumeSolidModuleTest {

    /**
     * Check for 'public' default constructor.
     *
     * @throws Exception
     */
    @Test
    public void VolumeSolidModuleDefault() throws Exception {
        try {
            Constructor<VolumeSolidModule> constructor
                    = VolumeSolidModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void VolumeSolidModuleConstructor() throws Exception {
        constructionPass(1, 2);
        constructionPass(5, 6);
        constructionPass(1, 10);
        constructionPass(5, 10);

        constructionFail(0, 1);
        constructionFail(1, 1);
        constructionFail(-1, 2);
        constructionFail(2, -1);
        constructionFail(6, 7);
        constructionFail(5, 11);
    }

    private void constructionPass(int a, int b) {
        try {
            new VolumeSolidModule(a, b);
            assert(true);
        } catch (Exception ex) {
            fail();
        }
    }

    private void constructionFail(int a, int b) {
        try {
            new VolumeSolidModule(a, b);
            fail();
        } catch (AssertionError er) {
            assert(true);
        }
    }

    @Test
    public void testQuestionLaTeX_5_10() throws Exception {
        VolumeSolidModule vol = new VolumeSolidModule(5, 10);

        String expectedQ =
                "Find the volume of the solid obtained by rotating\n" +
                "$f(x)=\\sqrt{x-5}$ about the $x$-axis over $[5,10]$.";
        String actualQ = vol.getSection("Question");
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "\\text{Volume }&=\\pi\\int_a^b(f(x))^2\\,dx\\\\\n" +
                "&=\\pi\\int_5^{10}\\left({\\sqrt{x-5}}\\,\\right)^2\\,dx\\\\\n" +
                "&=\\pi\\int_5^{10}(x-5)\\,dx\\\\\n" +
                "&=\\pi\\left[{\\dfrac{x^2}{2}-5x}\\right]_5^{10}\\\\\n" +
                "&=\\left(\\dfrac{(10)^2}{2}-5\\cdot(10)\\right)\\pi\n" +
                "-\\left(\\dfrac{(5)^2}{2}-5\\cdot(5)\\right)\\pi\\\\\n" +
                "&=\\left(\\dfrac{100}{2}-50\\right)\\pi\n" +
                "-\\left(\\dfrac{25}{2}-25\\right)\\pi\\\\\n" +
                "&=12.5\\pi\\text{ units}^3\n" +
                "\\end{align*}";
        String actualS = vol.getSection("Solution");
        assertEquals(expectedS, actualS);

        String expectedA =
                "$12.5\\pi\\text{ units}^3$";
        String actualA = vol.getSection("Answer");
        assertEquals(expectedA, actualA);

    }

}
