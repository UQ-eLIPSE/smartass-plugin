package au.edu.uq.smartass.question;

import au.edu.uq.smartass.util.Vector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests the Vector class
 */
public class VectorTest {
    Vector vector;

    private List<Integer> numbers = Arrays.asList(1, 2, 3);

    @Before
    public void setUp() {
        vector = new Vector("vector", Arrays.asList(1, 2, 3));
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testDimension() {
        assertEquals(vector.getDimension(), 3);
    }

    @Test
    public void testFormatName() {
        assertEquals(vector.getFormatName(), "\\mathbf{vector}");
    }

    @Test
    public void testFormatDefinition() {
        String expected = "\\mathbf{vector}=\\begin{pmatrix}1\\\\2\\\\3\\\\\\end{pmatrix}";
        assertEquals(vector.getFormatDefinition(), expected);
    }

    @Test
    public void testGet() {
        assertEquals(vector.get(0), 1);
        assertEquals(vector.get(1), 2);
        assertEquals(vector.get(2), 3);
    }
}
