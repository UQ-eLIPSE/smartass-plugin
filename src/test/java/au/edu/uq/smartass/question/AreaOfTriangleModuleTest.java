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

        assertEquals(5, vert.getX());
        assertEquals(1, vert.getY());
        assertEquals(2, vert.getZ());
        assertEquals("A", vert.getName());
        assertEquals("A(5,1,2)", vert.formatString());
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

        String expected = "$\\frac{\\sqrt{81.0}}{2}$ units$^2$";

        assertEquals(expected, calc.getAnswer());

    }

    @Test
    public void testSubtract() throws Exception {
        AreaOfTriangleModule triModule = new AreaOfTriangleModule();
        AreaOfTriangleModule.TriangleVertex vert1 = triModule.new TriangleVertex("A", 1, 1, 1);
        AreaOfTriangleModule.TriangleVertex vert2 = triModule.new TriangleVertex("B", 5, 1, 2);
        AreaOfTriangleModule.TriangleVertex result = vert2.subtract(vert1);

        assertEquals(4, result.getX());
        assertEquals(0, result.getY());
        assertEquals(1, result.getZ());
        assertEquals("\\vec{AB}", result.getName());
        assertEquals("\\vec{AB}(4,0,1)", result.formatString());
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
        String expected =
                "Let $A(1,1,1)$, $B(4,5,6)$ and $C(2,1,3)$ be the three vertices of a triangle. " +
                "Determine the area of $\\triangle ABC$.";
        System.out.println(expected);
        String actual = aot.getSection("question");
        assertEquals(expected, actual);

        // Test the answer string
        String expectedAnswer = "$\\frac{\\sqrt{81.0}}{2}$ units$^2$";
        System.out.println(expectedAnswer);
        String actualAnswer = aot.getSection("answer");
        assertEquals(expectedAnswer, actualAnswer);

        // Test the solution string
        String expectedSolution =
				"\\begin{align*}\n" +
				"\\text{Area}&=\\frac{1}{2}|\\vec{AB}\\times\\vec{AC}|\\\\\n" +
				"\\vec{AB}&=(3,4,5)\\\\\n" +
				"\\vec{AC}&=(1,0,2)\\\\\\\\\n" +
				"\\vec{AB}\\times\\vec{AC}&=\\left|\\begin{array}{crc}\\textbf{i}&\\textbf{j}&\\textbf{k}\\\\" +
                    "3&4&5\\\\1&0&2\\end{array}\\right|\\\\\n" +
				"&=\\textbf{i}\\left|\\begin{array}{rc}4&5\\\\0&2\\end{array}\\right|" +
                    "-\\textbf{j}\\left|\\begin{array}{cc}3&5\\\\1&2\\end{array}\\right|" +
                    "+\\textbf{k}\\left|\\begin{array}{cr}3&4\\\\1&0\\end{array} \\right|\\\\\n" +
				"&=8\\textbf{i}-1\\textbf{j}-4\\textbf{k}\\\\\\\\\n" +
				"|\\vec{AB}\\times\\vec{AC}|&=\\sqrt{8^2+1^2+4^2}\\\\\n" +
				"&=\\sqrt{81.0}\\\\\\\\\n" +
				"\\text{Therefore area }&=\\frac{1}{2}\\times\\sqrt{81.0}\\\\\n" +
				"&=\\frac{\\sqrt{81.0}}{2}\\text{ units}^2\\\\\n" +
				"\\end{align*}";
        System.out.println(expectedSolution);
        assertEquals(expectedSolution, aot.getSection("solution"));
    }
}
