package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Created by uqamoon1 on 15/09/2016.
 */
public class GeometricSequenceTermsToNModuleTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGeometricSequenceTermsToNModule() throws Exception {
        try {
            Constructor<GeometricSequenceTermsToNModule> constructor = GeometricSequenceTermsToNModule.class.getConstructor();
        } catch (NoSuchMethodException ex) {
           fail();
        }
    }

    @Test
    public void testGeneratedProperties() {
        GeometricSequenceTermsToNModule a = new GeometricSequenceTermsToNModule(2, 6, 15);
        assertEquals(2, a.numA);
        assertEquals(6, a.numB);
        assertEquals(18, a.numC);
        assertEquals(3, a.ratio);
        assertEquals(15, a.term);
        assertEquals(9565938, a.result);
    }

    @Test
    public void testGetSectionQuestion() {

    }

    @Test
    public void testGetSectionSolution() {

    }

    @Test
    public void testRandomIntsQuestion() {

    }

    @Test
    public void testRandomIntsSolution() {

    }

}
