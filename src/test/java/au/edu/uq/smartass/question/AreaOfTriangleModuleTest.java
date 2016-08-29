package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class AreaOfTriangleModuleTest {

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
    public void testAreaOfTriangleModule() throws Exception {
        try {
            Constructor<AreaOfTriangleModule> constructor
                    = AreaOfTriangleModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testTriangleVertex() throws Exception {
        AreaOfTriangleModule triModule = new AreaOfTriangleModule();
        AreaOfTriangleModule.TriangleVertex vert = triModule.new TriangleVertex("A", 5, 1, 2);

        assertEquals(vert.getX(), 5);
        assertEquals(vert.getY(), 1);
        assertEquals(vert.getZ(), 2);
        assertEquals(vert.getName(), "A");
        assertEquals(vert.formatString(), "A(5, 1, 2)");
    }

    @Test
    public void testAreaCalculation() throws Exception {
        AreaOfTriangleModule triModule = new AreaOfTriangleModule();

        AreaOfTriangleModule.TriangleVertex vert1 = triModule.new TriangleVertex("A", 1, 1, 1);
        AreaOfTriangleModule.TriangleVertex vert2 = triModule.new TriangleVertex("B", 5, 1, 2);
        AreaOfTriangleModule.TriangleVertex vert3 = triModule.new TriangleVertex("C", 1, 2, 3);

        AreaOfTriangleModule.TriangleVertex vert12 = vert2.subtract(vert1);
        AreaOfTriangleModule.TriangleVertex vert13 = vert3.subtract(vert1);

        AreaOfTriangleModule.AreaCalculation calc = triModule.new AreaCalculation(vert12, vert13);

        String expected = "$\\frac{\\sqrt{81.0}}{2} units^2$";

        assertEquals(calc.getAnswer(), expected);

    }

    @Test
    public void testSubtract() throws Exception {
        AreaOfTriangleModule triModule = new AreaOfTriangleModule();
        AreaOfTriangleModule.TriangleVertex vert1 = triModule.new TriangleVertex("A", 1, 1, 1);
        AreaOfTriangleModule.TriangleVertex vert2 = triModule.new TriangleVertex("B", 5, 1, 2);
        AreaOfTriangleModule.TriangleVertex result = vert2.subtract(vert1);

        assertEquals(result.getX(), 4);
        assertEquals(result.getY(), 0);
        assertEquals(result.getZ(), 1);
        assertEquals(result.getName(), "\\vec{AB}");
        assertEquals(result.formatString(), "\\vec{AB}(4, 0, 1)");
    }

    @Ignore
    @Test
    public void testGetSectionPackage() throws Exception {
        AreaOfTriangleModule dot = new AreaOfTriangleModule(new AreaOfTriangleModule.IntegerGenerator() {
            private int[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            private int idx = 0;
            @Override public int next(int lower, int uppper) {
                   return ints[idx++];
                }
            });

    }

    @Test
    public void testGetSection() throws Exception {
        AreaOfTriangleModule aot = new AreaOfTriangleModule(new AreaOfTriangleModule.IntegerGenerator() {
            private int[] ints = {1, 1, 1, 4, 5, 6, 2, 1, 3};
            private int idx = 0;
            @Override public int next(int lower, int uppper) {
                return ints[idx++];
            }
        });

        // Test the question string
        String expected = "Let A(1, 1, 1), B(4, 5, 6) and C(2, 1, 3) be the three vertices of a triangle. Determine the area of the triangle ABC.\\\\";
        String actual = aot.getSection("question");
        assertEquals(expected, actual);

        // Test the answer string
        String actualAnswer = aot.getSection("answer");
        String expectedAnswer = "$\\frac{\\sqrt{81.0}}{2} units^2$";
        assertEquals(actualAnswer, expectedAnswer);

        // Test the solution string
        String expectedSolution = "\\begin{align*}Area & = \\frac{1}{2}\\|\\vec{AB} \\times \\vec{AC}|\\\\\\vec{AB} & = (3, 4, 5)\\\\\\vec{AC} & = (1, 0, 2)\\\\\\\\\\vec{AB}\\times\\vec{AC}& = \\left| \\begin{array}{crc}" +
            "\\textbf{i} & \\textbf{j} & \\textbf{k} \\\\" +
            "3&4&5 \\\\" +
            "1&0&2\\end{array} \\right|=\\textbf{i} \\left| \\begin{array}{rc}" +
            "4&5 \\\\" +
            "0&2 \\end{array} \\right|" +
            "-\\textbf{j} \\left| \\begin{array}{cc}" +
            "3&5 \\\\" +
            "1&2 \\end{array} \\right|" +
            "+\\textbf{k} \\left| \\begin{array}{cr}" +
            "3&4 \\\\" +
            "1&0 \\end{array} \\right|" +
            "=8\\textbf{i} - 1\\textbf{j} - 4\\textbf{k}\\\\|\\vec{AB} \\times \\vec{AC}| & = \\sqrt{8^2 + 1^2 + 4^2}\\\\&= \\sqrt{81.0}\\\\Therefore\\ area & = \\frac{1}{2} \\times \\sqrt{81.0}\\\\ & = \\frac{\\sqrt{81.0}}{2} units^2\\\\\\end{align*}";
        String actualSolution = aot.getSection("solution");

    }

    @Test
    public void testGetSectionFail() throws Exception {
        AreaOfTriangleModule aot = new AreaOfTriangleModule();
        try {
            aot.getSection("NonExistantSectionName");
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
}
