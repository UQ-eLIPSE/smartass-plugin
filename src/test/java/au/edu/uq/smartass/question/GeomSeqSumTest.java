package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class GeomSeqSumTest {

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
    public void testPSeriesModule() throws Exception {
        try {
            Constructor<PSeriesModule> constructor
                    = PSeriesModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testGetSection() throws Exception {
        GeomSeqSumModule geom = new GeomSeqSumModule(2, 3, 20);

        System.out.println(geom.getSection("solution"));
        //assertTrue(false);
    }

    @Test
    public void testGetSectionFail() throws Exception {
        ScalarProductModule dot = new ScalarProductModule();
        try {
            dot.getSection("NonExistantSectionName");
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }
}
