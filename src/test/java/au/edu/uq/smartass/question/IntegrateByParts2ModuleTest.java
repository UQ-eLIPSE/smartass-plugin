package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class IntegrateByParts2ModuleTest {

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
    public void integrateByParts2ModuleDefault() throws Exception {
        try {
            Constructor<IntegrateByParts2Module> constructor
                    = IntegrateByParts2Module.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    private void constructionPass(int a) {
        try {
            new IntegrateByParts2Module(a);
            assert(true);
        } catch (Exception ex) {
            fail();
        }
    }

    private void constructionFail(int a) {
        try {
            new IntegrateByParts2Module(a);
            fail();
        } catch (AssertionError e) {
            assert(true);
        }
    }

    @Test
    public void testConstructor() throws Exception {
        constructionFail(0);
        constructionPass(2);
        constructionPass(5);
        constructionPass(10);
        constructionFail(11);
    }

    @Test
    public void testQuestionLaTeX_10() throws Exception {
        int a = 10;

        IntegrateByParts2Module q = new IntegrateByParts2Module(a);

        String expectedQuestion = "Determine $\\int " + a + " \\ln x \\,dx$. \\\\";
        String actualQuestion = q.getSection("question");
        assertEquals(expectedQuestion, actualQuestion);

        String expectedAnswer = "$=" + a + "x  \\ln x - " + a + "x +C$\\\\";
        String actualAnswer = q.getSection("answer");
        assertEquals(expectedAnswer, actualAnswer);

        String expectedSolution = "Let's use integration by parts." +
            "Let $u= \\ln x$, then $u'=\\dfrac1x$.\\\\ Let $v'=" + a + "$, then $v=" + a + "x$.\\\\" +
            "$\\int uv' \\,dx = uv - \\int u'v \\,dx$" +
            "So $\\int " + a + "\\ln x \\,dx = " + a + "x \\cdot \\ln x - \\int \\dfrac1x \\cdot " + a + "x\\,dx$\\\\" +
            "$=" + a + "x \\ln x - \\int " + a + " \\,dx$\\\\" +
            "$=" + a + "x  \\ln x - " + a + "x +C$\\\\";

        String actualSolution = q.getSection("solution");
        assertEquals(expectedSolution, actualSolution);
    }

    @Test
    public void testQuestionLaTeX_4() throws Exception {
        int a = 4;

        IntegrateByParts2Module q = new IntegrateByParts2Module(a);

        String expectedQuestion = "Determine $\\int " + a + " \\ln x \\,dx$. \\\\";
        String actualQuestion = q.getSection("question");
        assertEquals(expectedQuestion, actualQuestion);

        String expectedAnswer = "$=" + a + "x  \\ln x - " + a + "x +C$\\\\";
        String actualAnswer = q.getSection("answer");
        assertEquals(expectedAnswer, actualAnswer);

        String expectedSolution = "Let's use integration by parts." +
            "Let $u= \\ln x$, then $u'=\\dfrac1x$.\\\\ Let $v'=" + a + "$, then $v=" + a + "x$.\\\\" +
            "$\\int uv' \\,dx = uv - \\int u'v \\,dx$" +
            "So $\\int " + a + "\\ln x \\,dx = " + a + "x \\cdot \\ln x - \\int \\dfrac1x \\cdot " + a + "x\\,dx$\\\\" +
            "$=" + a + "x \\ln x - \\int " + a + " \\,dx$\\\\" +
            "$=" + a + "x  \\ln x - " + a + "x +C$\\\\";

        String actualSolution = q.getSection("solution");
        assertEquals(expectedSolution, actualSolution);
    }

    @Test
    public void testQuestionLaTeX_1() throws Exception {
        int num = 1;

        IntegrateByParts2Module q = new IntegrateByParts2Module(num);

        // We don't display a number if the number is 1
        String a = "";

        String expectedQuestion = "Determine $\\int " + a + " \\ln x \\,dx$. \\\\";
        String actualQuestion = q.getSection("question");
        assertEquals(expectedQuestion, actualQuestion);

        String expectedAnswer = "$=" + a + "x  \\ln x - " + a + "x +C$\\\\";
        String actualAnswer = q.getSection("answer");
        assertEquals(expectedAnswer, actualAnswer);

        String expectedSolution = "Let's use integration by parts." +
            "Let $u= \\ln x$, then $u'=\\dfrac1x$.\\\\ Let $v'=" + a + "$, then $v=" + a + "x$.\\\\" +
            "$\\int uv' \\,dx = uv - \\int u'v \\,dx$" +
            "So $\\int " + a + "\\ln x \\,dx = " + a + "x \\cdot \\ln x - \\int \\dfrac1x \\cdot " + a + "x\\,dx$\\\\" +
            "$=" + a + "x \\ln x - \\int " + a + " \\,dx$\\\\" +
            "$=" + a + "x  \\ln x - " + a + "x +C$\\\\";

        String actualSolution = q.getSection("solution");
        assertEquals(expectedSolution, actualSolution);
    }
}
