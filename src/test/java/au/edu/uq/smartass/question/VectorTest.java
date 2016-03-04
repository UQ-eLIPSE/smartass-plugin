package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the Vector class
 */
public class VectorTest {
    Vector vector;

    private List<Integer> numbers = Arrays.asList(1, 2, 3);
    PredictableGenerator generator;

    /**
     * Creates a predictable generator
     * Internally uses a queue for keeping track of numbers
     */
    class PredictableGenerator implements IntegerGenerator {
        LinkedList<Integer> randNumbers;

        public PredictableGenerator() {
            randNumbers = new LinkedList<Integer>();
        }

        public void setRandomNumbers(List<Integer> vec1) {
            randNumbers.addAll(vec1);
        }

        public int next(int a, int b) {
            return randNumbers.pop();
        }
    }

    @Before
    public void setUp() {
        generator = new PredictableGenerator();
        generator.setRandomNumbers(numbers);
        vector = new Vector("vector", 3, generator);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testDimension() {
        assertEquals(vector.dimension(), 3);
    }

    @Test
    public void testFormatName() {
        assertEquals(vector.formatName(), "\\mathbf{vector}");
    }

    @Test
    public void testFormatDefinition() {
        String expected = "\\mathbf{vector}=\\begin{pmatrix}1\\\\2\\\\3\\\\\\end{pmatrix}";
        assertEquals(vector.formatDefinition(), expected);
    }

    @Test
    public void testGet() {
        assertEquals(vector.get(0), 1);
        assertEquals(vector.get(1), 2);
        assertEquals(vector.get(2), 3);
    }
}
