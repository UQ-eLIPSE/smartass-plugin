package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static java.lang.System.*;
import static org.junit.Assert.*;

/**
 *
 */
public class Eigen2by2DistinctRealModuleTest {


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * A 'public' default constructor is required.
     */
    @Test
    public void testEigen2by2DistinctRealModule() throws Exception {
        try {
            Constructor<Eigen2by2DistinctRealModule> constructor
                    = Eigen2by2DistinctRealModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void Eigen2by2DistinctRealModuleIndex() throws Exception {
        try {
            new Eigen2by2DistinctRealModule(Eigen2by2DistinctRealModule.getDataSize() - 1);
            assertTrue(true);
        } catch (Exception ex) {
            fail();
        }
        try {
            new Eigen2by2DistinctRealModule(Eigen2by2DistinctRealModule.getDataSize());
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }
        try {
            for (int i = 0; i < Eigen2by2DistinctRealModule.getDataSize(); ++i) {
                new Eigen2by2DistinctRealModule(i);
            }
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    /**
     * Valid Question TeX.
     */
    @Test
    public void testQuestion() throws Exception {
        Eigen2by2DistinctRealModule eigen = new Eigen2by2DistinctRealModule(0);
        String expected =
                "Find the eigenvalues of \\(\\begin{bmatrix}-7&-9\\\\4&8\\end{bmatrix}\\).";
        String actual = eigen.getSection("question");
        out.println(actual);
        assertEquals(expected, actual);
    }

    /**
     * Valid Solution TeX.
     */
    @Test
    public void testSolution() throws Exception {
        Eigen2by2DistinctRealModule eigen = new Eigen2by2DistinctRealModule(0);
        String expected =
                "\\begin{gather*}\n" +
                "A-\\lambda{I}=\\begin{bmatrix}-7-\\lambda&-9\\\\4&8-\\lambda\\end{bmatrix}\n" +
                "\\end{gather*}\n" +
                "\\begin{align*}\n" +
                "p(\\lambda)&=|A-\\lambda{I}|\\\\\n" +
                "&=\\begin{vmatrix}-7-\\lambda&-9\\\\4&8-\\lambda\\end{vmatrix}\\\\\n" +
                "&=(-7-\\lambda)(8-\\lambda)-(4\\times-9)\\\\\n" +
                "&=-56-\\lambda+\\lambda^2+36\\\\\n" +
                "&=\\lambda^2-\\lambda-20\\\\\n" +
                "&=(\\lambda-5)(\\lambda+4)\n" +
                "\\end{align*}\n" +
                "Solving $p(\\lambda)=0$ yields the eigenvalues $\\lambda_1=5$ and $\\lambda_2=-4$.";
        String actual = eigen.getSection("solution");
        out.println(actual);
        assertEquals(expected, actual);
    }

    /**
     * Valid Answer TeX.
     */
    @Test
    public void testAnswer() throws Exception {
        Eigen2by2DistinctRealModule eigen = new Eigen2by2DistinctRealModule(0);
        String expected = "$\\lambda_1=5,\\lambda_2=-4$";
        String actual = eigen.getSection("answer");
        out.println(actual);
        assertEquals(expected, actual);
    }

}
