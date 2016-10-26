package au.edu.uq.smartass.question;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 */
public class InfiniteGeometricSequenceSumModuleTest {

    /**
     * Check for 'public' default constructor.
     *
     * @throws Exception
     */
    @Test
    public void InfiniteGeometricSequenceSumModuleDefault() throws Exception {
        try {
            Constructor<InfiniteGeometricSequenceSumModule> constructor
                    = InfiniteGeometricSequenceSumModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testGetSection() throws Exception {
        InfiniteGeometricSequenceSumModule seq = new InfiniteGeometricSequenceSumModule(4);

        String expectedQ =
                "Let $2,\\dfrac{1}{2},\\dfrac{1}{8}$ be the first three terms of an infinite geometric sequence. \n" +
                "What is the sum of the series?";
        String actualQ = seq.getSection("question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "S_{\\infty}&=\\dfrac{a}{1-r}\\text{, where }\n" +
                "r=\\dfrac{1}{2}\\div2=\\dfrac{1}{4}\\text{ and }a=2.\\\\\n" +
                "S_{\\infty}&=\\dfrac{2}{1-\\tfrac{1}{4}}\\\\\n" +
                "&=\\dfrac{2}{\\tfrac{3}{4}}\\\\\n" +
                "&=\\dfrac{8}{3}\n" +
                "\\end{align*}";
        System.out.println(expectedS);
        String actualS = seq.getSection("solution");
        assertEquals(expectedS, actualS);

        String expectedA = "$\\dfrac{8}{3}$";
        String actualA = seq.getSection("answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }

}