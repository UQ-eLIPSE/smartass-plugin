package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class VolumeOfRevolutionModuleTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    /**
     * Check for 'public' default constructor.
     * 
     * @throws Exception
     */
    @Test
    public void testVolumeOfRevolutionModule() throws Exception {

        try {
            Constructor<VolumeOfRevolutionModule> constructor
                    = VolumeOfRevolutionModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testGetSection_5_5_10() throws Exception {
        VolumeOfRevolutionModule vr = new VolumeOfRevolutionModule(5, 5, 10);

        String actual = vr.getSection("question");
        String expected =
                "Find the volume of the solid obtained by rotating $f(x)=\\sqrt{x^{5}\\ln{x}}$ " +
                "about the $x$-axis over $[5, 10]$.";
        System.out.println(actual);
        assertEquals(actual, expected);

        actual = vr.getSection("answer");
        expected = "$\\approx 1106560.628$\n";
        
        String actual = vr.getSection("question");
        String expected =
                "Find the volume of the solid obtained by rotating $f(x)=\\sqrt{x^{10}\\ln{x}}$ " +
                "about the $x$-axis over $[3, 10]$.";
        System.out.println(actual);
        assertEquals(expected, actual);

        actual = vr.getSection("answer");
        expected = "$\\approx63165267928.761$";
        System.out.println(actual);
        assertEquals(expected, actual);

        actual = vr.getSection("solution");
        expected =
                "\\begin{align*}\n" +
                "\\text{Volume }&=\\pi\\int_a^b(f(x))^2\\,dx\\\\\n" + 
                "&=\\pi\\int_{3}^{10}\\left(\\sqrt{x^{10}\\ln{x}}\\,\\right)^2\\,dx\\\\\n" +
                "&=\\pi\\int_{3}^{10}{x^{10}\\ln{x}}\\,dx\\\\\n" +
                "\\end{align*}\n" + 
                "Let's use integration by parts as the two functions are not \n" + 
                "related to each other in terms of derivatives.\n" + 
                "\\begin{align*}\n" + 
                "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" + 
                "&\\text{Let }v'=x^{10}\\text{, then }v=\\dfrac{1}{10+1}x^{10+1}=\\dfrac{1}{11}\\cdot{x}^{11}.\\\\\n" +
                "\\end{align*}\n" + 
                "\\begin{align*}\n" + 
                "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" + 
                "\\text{So }\\pi\\int_{3}^{10}{x}^{10}\\ln{x}\\,dx" +
                "&=\\left[\\dfrac{\\pi}{11}\\cdot{x}^{11}\\ln{x}\\right]_{3}^{10}" +
                "-\\pi\\int_{3}^{10}\\dfrac{1}{x}\\cdot\\dfrac{1}{11}\\cdot{x}^{11}\\,dx\\\\\n" +
                "&=\\left[\\dfrac{\\pi}{11}\\cdot{x}^{11}\\ln{x}\\right]_{3}^{10}" +
                "-\\dfrac{\\pi}{11}\\int_{3}^{10}{x}^{10}\\,dx\\\\\n" +
                "&=\\left[\\dfrac{\\pi}{11}\\cdot{x}^{11}\\ln{x}" +
                "-\\dfrac{\\pi}{11}\\cdot\\dfrac{1}{11}\\cdot{x}^{11}\\right]_{3}^{10}\\\\\n" +
                "&=\\left[\\dfrac{\\pi}{11}\\cdot{x}^{11}\\ln{x}" +
                "-\\dfrac{\\pi}{121}\\cdot{x}^{11}\\right]_{3}^{10}\\\\\n" +
                "&=\\dfrac{\\pi}{11}\\cdot{10}^{11}\\ln{10}-\\dfrac{\\pi}{11}\\cdot{3}^{11}\\ln{3}" +
                "-\\dfrac{\\pi}{121}\\cdot{10}^{11}+\\dfrac{\\pi}{121}\\cdot{3}^{11}\\\\\n" +
                "&\\approx63165267928.761\n" +
                "\\end{align*}";
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testCalculation() throws Exception {
        VolumeOfRevolutionModule vr = new VolumeOfRevolutionModule(5, 5, 10);
        
        // Calculated from Wolfram Alpha
        double expected = 1106560.6281504822;
        double actual = vr.getAnswer();

        // Specify delta of 1 for rounding errors and value of PI
        assertEquals(expected, actual, 1);

        vr = new VolumeOfRevolutionModule(3, 2, 4);

        expected = 222.897;
        actual = vr.getAnswer();

        assertEquals(expected, actual, 1);
    }
}
