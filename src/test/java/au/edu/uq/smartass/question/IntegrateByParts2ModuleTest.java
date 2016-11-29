package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 * Tests for the IntegrateByParts2Module
 */
public class IntegrateByParts2ModuleTest {

    private static final String QUESTION_TEMPLATE =
            "Determine $\\displaystyle\\int%TERM_001_NOT=1%\\ln{x}\\,dx$.\n";

    private static final String SOLUTION_TEMPLATE =
            "Let's use integration by parts as the two functions are not\n" +
            "related to each other in terms of derivatives.\n" +
            "\\begin{align*}\n" +
            "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" +
            "&\\text{Let }v'=%TERM_001%\\text{, then }v=%TERM_001_NOT=1%x.\\\\\n" +
            "\\end{align*}\n" +
            "\\begin{align*}\n" +
            "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" +
            "\\text{So }\\int{%TERM_001_NOT=1%}\\ln{x}\\,dx\n" +
            "&=%TERM_001_NOT=1%x\\ln{x}-\\int\\dfrac{1}{x}\\cdot{%TERM_001_NOT=1%x}\\,dx\\\\\n" +
            "&=%TERM_001_NOT=1%x\\ln{x}-\\int%TERM_001%\\,dx\\\\\n" +
            "&=%TERM_001_NOT=1%x\\ln{x}-%TERM_001_NOT=1%x+C\n" +
            "\\end{align*}\n";

    private static final String ANSWER_TEMPLATE =
            "$%TERM_001_NOT=1%x\\ln{x}-%TERM_001_NOT=1%x+C$\n";

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
        constructionPass(1);
        constructionPass(2);
        constructionPass(5);
        constructionPass(10);
        constructionFail(11);
    }

    /**
     *
     */
    @Test
    public void testQuestionLaTeX_1_10() throws Exception {
        for (int i = 1; i <= 10; ++i) {
            testQuestionLaTeXHelper(i);
        }
    }

    private void testQuestionLaTeXHelper(final int a) throws Exception {
        String expected;
        String actual;

        IntegrateByParts2Module mod = new IntegrateByParts2Module(a);

        String aterm = Integer.toString(a);
        String term001 = (1 == a) ? "" : aterm;

        expected = QUESTION_TEMPLATE.replace("%TERM_001_NOT=1%", term001);
        actual =  mod.getSection("question");
        System.out.println(actual);
        assertEquals(expected, actual);

        expected = ANSWER_TEMPLATE.replace("%TERM_001_NOT=1%", term001);
        actual =  mod.getSection("answer");
        System.out.println(actual);
        assertEquals(expected, actual);

        expected = SOLUTION_TEMPLATE.replace("%TERM_001_NOT=1%", term001);
        expected = expected.replace("%TERM_001%", aterm);
        actual =  mod.getSection("solution");
        System.out.println(actual);
        assertEquals(expected, actual);
    }
}
