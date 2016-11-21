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
    public void testGetSection() throws Exception {
        VolumeOfRevolutionModule vr = new VolumeOfRevolutionModule(5, 5, 10);
        
        String actual = vr.getSection("question");
        String expected = "Find the volume of the solid obtained by rotating $f(x)=\\sqrt{x^{5}\\ln{x}}$ about the $x$-axis over $[5, 10]$.\n\n";

        assertEquals(actual, expected);

        actual = vr.getSection("answer");
        expected = "&\\approx 1106560.628\n\n";
        
        assertEquals(expected, actual);

        actual = vr.getSection("solution");
        expected = "\\begin{align*}\n" + 
                "\\text{Volume }&=\\pi\\int_a^b(f(x))^2\\,dx\\\\\n" + 
                "&=\\pi\\int_5^{10}\\left(\\sqrt{x^{5}\\ln{x}}\\,\\right)^2\\,dx\\\\\n" + 
                "&=\\pi\\int_5^{10}{x^{5}\\ln{x}}\\,dx\\\\\n" + 
                "\\end{align*}\n" + 
                "Let's use integration by parts as the two functions are not \n" + 
                "related to each other in terms of derivatives.\n" + 
                "\\begin{align*}\n" + 
                "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" + 
                "&\\text{Let }v'=x^{5}\\text{, then }v=\\dfrac{1}{5+1}x^{5+1}=\\dfrac{1}{6}\\cdot{x^6}.\\\\\n" + 
                "\\end{align*}\n" + 
                "\\begin{align*}\n" + 
                "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" + 
                "\\text{So }\\pi\\int_5^{10}{x}^{5}\\ln{x}\\,dx&=\\left[\\dfrac{\\pi}{6}\\cdot{x^6}\\ln{x}\\right]_5^{10}-\\pi\\int_5^{10}\\dfrac{1}{x}\\cdot\\dfrac{1}{6}\\cdot{x^6}\\,dx\\\\\n" + 
                "&=\\left[\\dfrac{\\pi}{6}\\cdot{x}^{6}\\ln{x}\\right]_5^{10}-\\dfrac{\\pi}{6}\\int_5^{10}{x}^{5}\\,dx\\\\\n" + 
                "&=\\left[\\dfrac{\\pi}{6}\\cdot{x}^{6}\\ln{x}-\\dfrac{\\pi}{6}\\cdot\\dfrac{1}{6}\\cdot{x}^{6}\\right]_5^{10}\\\\\n" + 
                "&=\\left[\\dfrac{\\pi}{6}\\cdot{x}^{6}\\ln{x}-\\dfrac{\\pi}{36}\\cdot{x}^{6}\\right]_5^{10}\\\\\n" + 
                "&=\\dfrac{\\pi}{6}\\cdot{10}^{6}\\ln{10}-\\dfrac{\\pi}{6}\\cdot{5}^{6}\\ln{5}-\\dfrac{\\pi}{36}\\cdot{10}^{6}+\\dfrac{\\pi}{36}\\cdot{5}^{6}\\\\\n" + 
                "&=\\dfrac{\\pi}{6}\\cdot{5}^{6}\\left(2^6\\ln{10}-\\ln{5}-\\dfrac{2^6}{6}+\\dfrac{1}{6}\\right)\\\\\n" + 
                "&\\approx 1106560.628\n" + 
                "\\end{align*}\n";

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
    }
}
