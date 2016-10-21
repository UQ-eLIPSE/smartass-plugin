package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class Eigen2x2RepeatModuleTest {

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
    public void Eigen2x2RepeatlModuleDefault() throws Exception {
        try {
            Constructor<Eigen2x2RepeatModule> constructor
                    = Eigen2x2RepeatModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void Eigen2x2RepeatModuleIndex() throws Exception {
        try {
            new Eigen2x2RepeatModule(0);
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        try {
            new Eigen2x2RepeatModule(Eigen2x2RepeatModule.getDataSize() - 1);
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        try {
            new Eigen2x2RepeatModule(Eigen2x2RepeatModule.getDataSize());
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }
        try {
            for (int i = 0; i < Eigen2x2RepeatModule.getDataSize(); ++i) {
                new Eigen2x2RepeatModule(i);
            }
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testQuesiton() throws Exception {
        Eigen2x2RepeatModule eigen = new Eigen2x2RepeatModule(0);
        String expected =
                "Find the eigenvalues of \\(\\begin{bmatrix}6&9\\\\-4&-6\\end{bmatrix}\\).";
        String actual = eigen.getSection("question");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testSolution() throws Exception {
        Eigen2x2RepeatModule eigen = new Eigen2x2RepeatModule(0);
        String expected =
                "\\begin{gather*}\n" +
                "A-\\lambda{I}=\\begin{bmatrix}6-\\lambda&9\\\\-4&-6-\\lambda\\end{bmatrix}.\n" +
                "\\end{gather*}\n" +
                "\\begin{align*}\n" +
                "p(\\lambda)&=|A-\\lambda{I}|=\\begin{vmatrix}6-\\lambda&9\\\\-4&-6-\\lambda\\end{vmatrix}.\\\\\n" +
                "&=(6-\\lambda)(-6-\\lambda)-(-4\\times9)\\\\\n" +
                "&=-36+0\\lambda+\\lambda^2+36\\\\\n" +
                "&=\\lambda^2+0\\lambda+0\\\\\n" +
                "&=(\\lambda+0)(\\lambda+0)\n" +
                "\\end{align*}\n" +
                "Solving $p(\\lambda)=0$ yields the eigenvalues $\\lambda_1=0$ and $\\lambda_2=0$.";
        String actual = eigen.getSection("solution");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testAnswer() throws Exception {
        Eigen2x2RepeatModule eigen = new Eigen2x2RepeatModule(0);
        String expected =
                "$\\lambda_1=0,\\lambda_2=0$";
        String actual = eigen.getSection("answer");
        System.out.println(actual);
        assertEquals(expected, actual);
    }
}