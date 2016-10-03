package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class QUtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getOrdinal() throws Exception {
        assertEquals("st", QUtil.getOrdinal(1));
        assertEquals("th", QUtil.getOrdinal(11));
        assertEquals("st", QUtil.getOrdinal(21));
        assertEquals("st", QUtil.getOrdinal(151));

        assertEquals("nd", QUtil.getOrdinal(2));
        assertEquals("th", QUtil.getOrdinal(12));
        assertEquals("nd", QUtil.getOrdinal(22));

        assertEquals("rd", QUtil.getOrdinal(3));
        assertEquals("th", QUtil.getOrdinal(13));
        assertEquals("rd", QUtil.getOrdinal(23));
    }

    @Test
    public void generatePosInt() throws Exception {
        int lower = 0;
        int upper = 10;
        for (int i = 0; i < 100; ++i) {
            int val = QUtil.generatePosInt(lower, upper);
            assertTrue(String.format("Generated Value [%d]",val), lower <= val && val <= upper);
        }
    }

    @Test
    public void generateNegToPosInt() throws Exception {
        int lower = -10;
        int upper = 10;
        for (int i = 0; i < 100; ++i) {
            int val = QUtil.generateNegToPosInt(lower, upper);
            assertTrue(String.format("Generated Value [%d]",val), lower <= val && val <= upper);
        }
    }

}