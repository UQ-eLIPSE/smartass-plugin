package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class Eigen2x2ComplexModuleTest {

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
    public void Eigen2x2ComplexModuleDefault() throws Exception {
        try {
            Constructor<Eigen2x2ComplexModule> constructor
                    = Eigen2x2ComplexModule.class.getConstructor();
            assertTrue(true);
        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void Eigen2x2ComplexModuleIndex() throws Exception {
        try {
            new Eigen2x2ComplexModule(0);
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        try {
            new Eigen2x2ComplexModule(Eigen2x2ComplexModule.getDataSize() - 1);
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
        try {
            new Eigen2x2ComplexModule(Eigen2x2ComplexModule.getDataSize());
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }
        try {
            for (int i = 0; i < Eigen2x2ComplexModule.getDataSize(); ++i) {
                System.out.println(String.format("----------  %03d  ----------", i));
                new Eigen2x2ComplexModule(i);
            }
            assertTrue(true);
        } catch (Exception ex) {
            fail(ex.getMessage());
        }
    }

    @Test
    public void testQuesiton() throws Exception {
        Eigen2x2ComplexModule eigen = new Eigen2x2ComplexModule(0);
        String expected =
                "Find the eigenvalues of \\(\\begin{bmatrix}3&4\\\\-8&-5\\end{bmatrix}\\).";
        String actual = eigen.getSection("question");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testSolution() throws Exception {
        Eigen2x2ComplexModule eigen = new Eigen2x2ComplexModule(0);
        String expected =
                "\\begin{gather*}\n" +
                "A-\\lambda{I}=\\begin{bmatrix}3-\\lambda&4\\\\-8&-5-\\lambda\\end{bmatrix}\n" +
                "\\end{gather*}\n" +
                "\\begin{align*}\n" +
                "p(\\lambda)&=|A-\\lambda{I}|\\\\\n" +
                "&=\\begin{vmatrix}3-\\lambda&4\\\\-8&-5-\\lambda\\end{vmatrix}\\\\\n" +
                "&=(3-\\lambda)(-5-\\lambda)-(-8\\times4)\\\\\n" +
                "&=-15+2\\lambda+\\lambda^2+32\\\\\n" +
                "&=\\lambda^2+2\\lambda+17\\\\\n" +
                "&=(\\lambda+1-4i)(\\lambda+1+4i)\n" +
                "\\end{align*}\n" +
                "Solving $p(\\lambda)=0$ yields the eigenvalues $\\lambda_1=-1+4i$ and $\\lambda_2=-1-4i$.";
        String actual = eigen.getSection("solution");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testAnswer() throws Exception {
        Eigen2x2ComplexModule eigen = new Eigen2x2ComplexModule(0);
        String expected =
                "$\\lambda_1=-1+4i,\\lambda_2=-1-4i$";
        String actual = eigen.getSection("answer");
        System.out.println(actual);
        assertEquals(expected, actual);
    }

}