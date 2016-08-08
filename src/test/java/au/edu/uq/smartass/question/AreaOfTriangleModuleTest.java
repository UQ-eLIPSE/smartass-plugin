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
    public void testSubtract() throws Exception {
        AreaOfTriangleModule triModule = new AreaOfTriangleModule();
        AreaOfTriangleModule.TriangleVertex vert1 = triModule.new TriangleVertex("A", 1, 1, 1);
        AreaOfTriangleModule.TriangleVertex vert2 = triModule.new TriangleVertex("B", 5, 1, 2);
        AreaOfTriangleModule.TriangleVertex result = vert2.subtract(vert1);

        assertEquals(result.getX(), 4);
        assertEquals(result.getY(), 0);
        assertEquals(result.getZ(), 1);
        assertEquals(result.getName(), "AB");
        assertEquals(result.formatString(), "AB(4, 0, 1)");
    }

    @Ignore
    @Test
    public void testGetSectionPackage() throws Exception {
        AreaOfTriangleModule dot = new AreaOfTriangleModule(new AreaOfTriangleModule.IntegerGenerator() {
            private int[] ints = {3, 9, -9, 3, 2, 1, 4};
            private int idx = 0;
            @Override public int next(int lower, int uppper) {
                   return ints[idx++];
                }
            });

    }

    @Ignore
    @Test
    public void testGetSection() throws Exception {
        AreaOfTriangleModule aot = new AreaOfTriangleModule(new AreaOfTriangleModule.IntegerGenerator() {
            private int[] ints = {3, 3, -4, 4, -2, 1, 7};
            private int idx = 0;
            @Override public int next(int lower, int uppper) {
                return ints[idx++];
            }
        });

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
