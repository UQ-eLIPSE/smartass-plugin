package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void testCrossProductQuestion() {

        String expected = "Let $ \\mathbf{u}= \\left(\\begin{array}{c} " +
                "5 \\\\ 2\\\\7\\end{array} \\right)$ and $ \\mathbf{ v} " +
                "=\\left(\\begin{array}{c} -2\\\\ 5\\\\-9\\end{array} " +
                "\\right) $. Determine ${\\bf u} \\times {\\bf v}$.";
        assertEquals(crossProduct.getSection("question"), expected);
    }

    @Test
    public void testCrossProductAnswer() {

        String expected = "-53\\textbf{i} + 31\\textbf{j} + 29\\textbf{k}";
        assertEquals(crossProduct.getSection("answer"), expected);
    }

    @Test
    public void testCrossProductSolution() {

        String expected = "$\\textbf{u}\\times\\textbf{v} = \\left| \\begin{array}{crc}\n" +
                "\\textbf{i} & \\textbf{j} & \\textbf{k} \\\\\n" +
                "5&2&7 \\\\\n" +
                "-2&5&-9\\end{array} \\right|=\\textbf{i} \\left| \\begin{array}{rc}\n" +
                "2&7 \\\\\n" +
                "5&-9 \\end{array} \\right|\n" +
                "-\\textbf{j} \\left| \\begin{array}{cc}\n" +
                "5&7 \\\\\n" +
                "-2&-9 \\end{array} \\right|\n" +
                "+\\textbf{k} \\left| \\begin{array}{cr}\n" +
                "5&2 \\\\\n" +
                "-2&5 \\end{array} \\right|\n" +
                "=-53\\textbf{i} + 31\\textbf{j} + 29\\textbf{k}$";
        assertEquals(crossProduct.getSection("solution"), expected);
    }

    @Test
    public void testGetSectionFail() throws Exception {
        try {
            crossProduct.getSection("NonExistantSectionName");
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
}
