package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the cross product module
 */
public class CrossProductModuleTest {
    private CrossProductModule crossProduct;

    private List<Integer> vector1 = Arrays.asList(5, 2, 7);
    private List<Integer> vector2 = Arrays.asList(-2, 5, -9);

    PredictableGenerator generator;

    @Before
    public void setUp() throws Exception {
        generator = new PredictableGenerator();
        generator.setRandomNumbers(vector1);
        generator.setRandomNumbers(vector2);
        crossProduct = new CrossProductModule(generator);
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
    public void testCrossProductModuleDefault() throws Exception {
        try {
            Constructor<CrossProductModule> constructor
                    = CrossProductModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testCrossProductQuestion() {
        String expected =
                "Let $\\mathbf{u}=\\left(\\begin{array}{c}5\\\\2\\\\7\\end{array}\\right)$ \n" +
                "and $\\mathbf{v}=\\left(\\begin{array}{c}-2\\\\5\\\\-9\\end{array}\\right)$. \n" +
                "Determine $\\bf{u}\\times\\bf{v}$.";
        System.out.println(expected);
        assertEquals(crossProduct.getSection("question"), expected);
    }

    @Test
    public void testCrossProductAnswer() {
        String expected = "$-53\\textbf{i}+31\\textbf{j}+29\\textbf{k}$";
        System.out.println(expected);
        assertEquals(crossProduct.getSection("answer"), expected);
    }

    @Test
    public void testCrossProductSolution() {
        String expected =
				"\\begin{align*}\n" +
				"\\textbf{u}\\times\\textbf{v}&=\\left|\\begin{array}{crc}\n" +
				"\\textbf{i}&\\textbf{j}&\\textbf{k}\\\\5&2&7\\\\-2&5&-9\n" +
				"\\end{array}\\right|\\\\\n" +
				"&=\\textbf{i}\\left|\\begin{array}{rc}2&7\\\\5&-9\\end{array}\\right|\n" +
				"-\\textbf{j}\\left|\\begin{array}{cc}5&7\\\\-2&-9\\end{array}\\right|\n" +
				"+\\textbf{k}\\left|\\begin{array}{cr}5&2\\\\-2&5\\end{array}\\right|\\\\\n" +
				"&=-53\\textbf{i}+31\\textbf{j}+29\\textbf{k}\n" +
				"\\end{align*}";
        System.out.println(expected);
        assertEquals(crossProduct.getSection("solution"), expected);
    }

}
