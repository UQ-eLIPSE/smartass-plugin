package au.edu.uq.smartass.question;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * @todo
 *      . Additional full TeX tests for InfiniteGeometricSequenceSumModule(a, r)
 *          for 2 <= \a\ <= 10
 *          for 2 <= \r\ <= 10
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
    public void InfiniteGeometricSequenceSumModuleConstructor() throws Exception {
        constructionPass(2, 2);
        constructionPass(-2, -2);
        constructionPass(10, 10);
        constructionPass(-10, -10);

        constructionFail(1, 2);
        constructionFail(2, 1);
        constructionFail(-1, 2);
        constructionFail(2, -1);
        constructionFail(11, 2);
        constructionFail(2, 11);
        constructionFail(-11, 2);
        constructionFail(2, -11);
    }

    private void constructionPass(int a, int r) {
        try {
            new InfiniteGeometricSequenceSumModule(a, r);
            assert(true);
        } catch (Exception ex) {
            fail();
        }
    }

    private void constructionFail(int a, int r) {
        try {
            new InfiniteGeometricSequenceSumModule(a, r);
            fail();
        } catch (AssertionError er) {
            assert(true);
        }
    }

    @Test
    public void testQuestionLaTeX_2_4() throws Exception {
        InfiniteGeometricSequenceSumModule seq = new InfiniteGeometricSequenceSumModule(2, 4);

        String expectedQ =
                "Let $2,\\dfrac{1}{2},\\dfrac{1}{8}$ be the first three terms of an infinite geometric sequence. \n" +
                "What is the sum of the corresponding series?";
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

    @Test
    public void testQuestionLaTeX_8n_7n() throws Exception {
        InfiniteGeometricSequenceSumModule seq = new InfiniteGeometricSequenceSumModule(-8, -7);

        String expectedQ =
                "Let $-8,\\dfrac{8}{7},-\\dfrac{8}{49}$ be the first three terms " +
                "of an infinite geometric sequence. \n" +
                "What is the sum of the corresponding series?";
        String actualQ = seq.getSection("question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "S_{\\infty}&=\\dfrac{a}{1-r}\\text{, where }\n" +
                "r=\\dfrac{8}{7}\\div-8=-\\dfrac{1}{7}\\text{ and }a=-8.\\\\\n" +
                "S_{\\infty}&=-\\dfrac{8}{1+\\tfrac{1}{7}}\\\\\n" +
                "&=-\\dfrac{8}{\\tfrac{8}{7}}\\\\\n" +
                "&=-7\n" +
                "\\end{align*}";
        System.out.println(expectedS);
        String actualS = seq.getSection("solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA = "$-7$";
        String actualA = seq.getSection("answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }

    @Test
    public void testQuestionLaTeX_7n_3() throws Exception {
        InfiniteGeometricSequenceSumModule seq = new InfiniteGeometricSequenceSumModule(-7, 3);

        String expectedQ =
                "Let $-7,-\\dfrac{7}{3},-\\dfrac{7}{9}$ be the first three terms " +
                "of an infinite geometric sequence. \n" +
                "What is the sum of the corresponding series?";
        String actualQ = seq.getSection("question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "S_{\\infty}&=\\dfrac{a}{1-r}\\text{, where }\n" +
                "r=-\\dfrac{7}{3}\\div-7=\\dfrac{1}{3}\\text{ and }a=-7.\\\\\n" +
                "S_{\\infty}&=-\\dfrac{7}{1-\\tfrac{1}{3}}\\\\\n" +
                "&=-\\dfrac{7}{\\tfrac{2}{3}}\\\\\n" +
                "&=-\\dfrac{21}{2}\n" +
                "\\end{align*}";
        //System.out.println(expectedS);
        String actualS = seq.getSection("solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA = "$-\\dfrac{21}{2}$";
        String actualA = seq.getSection("answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }

    @Test
    public void testQuestionLaTeX_8n_4() throws Exception {
        InfiniteGeometricSequenceSumModule seq = new InfiniteGeometricSequenceSumModule(-8, 4);

        String expectedQ =
                "Let $-8,-2,-\\dfrac{1}{2}$ be the first three terms " +
                "of an infinite geometric sequence. \n" +
                "What is the sum of the corresponding series?";
        String actualQ = seq.getSection("question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "S_{\\infty}&=\\dfrac{a}{1-r}\\text{, where }\n" +
                "r=-2\\div-8=\\dfrac{1}{4}\\text{ and }a=-8.\\\\\n" +
                "S_{\\infty}&=-\\dfrac{8}{1-\\tfrac{1}{4}}\\\\\n" +
                "&=-\\dfrac{8}{\\tfrac{3}{4}}\\\\\n" +
                "&=-\\dfrac{32}{3}\n" +
                "\\end{align*}";
        //System.out.println(expectedS);
        String actualS = seq.getSection("solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA = "$-\\dfrac{32}{3}$";
        String actualA = seq.getSection("answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }

    @Test
    public void testQuestionLaTeX_9_9() throws Exception {
        InfiniteGeometricSequenceSumModule seq = new InfiniteGeometricSequenceSumModule(9, 9);

        String expectedQ =
                "Let $9,1,\\dfrac{1}{9}$ be the first three terms " +
                "of an infinite geometric sequence. \n" +
                "What is the sum of the corresponding series?";
        String actualQ = seq.getSection("question");
        System.out.println(actualQ);
        assertEquals(expectedQ, actualQ);

        String expectedS =
                "\\begin{align*}\n" +
                "S_{\\infty}&=\\dfrac{a}{1-r}\\text{, where }\n" +
                "r=1\\div9=\\dfrac{1}{9}\\text{ and }a=9.\\\\\n" +
                "S_{\\infty}&=\\dfrac{9}{1-\\tfrac{1}{9}}\\\\\n" +
                "&=\\dfrac{9}{\\tfrac{8}{9}}\\\\\n" +
                "&=\\dfrac{81}{8}\n" +
                "\\end{align*}";
        //System.out.println(expectedS);
        String actualS = seq.getSection("solution");
        System.out.println(actualS);
        assertEquals(expectedS, actualS);

        String expectedA =
                "$\\dfrac{81}{8}$";
        String actualA = seq.getSection("answer");
        System.out.println(actualA);
        assertEquals(expectedA, actualA);
    }
}