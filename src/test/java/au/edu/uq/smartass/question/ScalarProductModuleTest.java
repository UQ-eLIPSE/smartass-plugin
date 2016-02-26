package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

/**
 *
 */
public class ScalarProductModuleTest {

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
    public void testScalarProductModule() throws Exception {
        try {
            Constructor<ScalarProductModule> constructor
                    = ScalarProductModule.class.getConstructor();
            assertTrue(true);

        } catch (NoSuchMethodException ex) {
            fail();
        }
    }

    @Test
    public void testGetSectionPackage() throws Exception {
        ScalarProductModule dot = new ScalarProductModule(new IntegerGenerator() {
            private int[] ints = {3, 9, -9, 3, 2, 1, 4};
            private int idx = 0;
            @Override public int next(int lower, int uppper) {
                   return ints[idx++];
                }
            });

        assertEquals(3, dot.dim);
        assertEquals(3, dot.u.dimension());
        assertEquals(3, dot.v.dimension());

        assertEquals(9, dot.u.get(0));
        assertEquals(-9, dot.u.get(1));
        assertEquals(3, dot.u.get(2));

        assertEquals("\\mathbf{u}", dot.u.formatName());
        assertEquals("\\mathbf{u}=\\begin{pmatrix}9\\\\-9\\\\3\\\\\\end{pmatrix}", dot.u.formatDefinition());
        assertEquals("\\lVert\\mathbf{u}\\rVert", dot.u.normalName());
        assertEquals("\\sqrt{9^2+(-9)^2+3^2}", dot.u.normalExpand());
        assertEquals("\\sqrt{171}", dot.u.normalGroup());
        assertEquals(171, dot.u.normalSqr());
        assertEquals(13.08, dot.u.normal(), 0.01);

        assertEquals(2, dot.v.get(0));
        assertEquals(1, dot.v.get(1));
        assertEquals(4, dot.v.get(2));

        assertEquals("\\mathbf{v}", dot.v.formatName());
        assertEquals("\\mathbf{v}=\\begin{pmatrix}2\\\\1\\\\4\\\\\\end{pmatrix}", dot.v.formatDefinition());
        assertEquals("\\lVert\\mathbf{v}\\rVert", dot.v.normalName());
        assertEquals("\\sqrt{2^2+1^2+4^2}", dot.v.normalExpand());
        assertEquals("\\sqrt{21}", dot.v.normalGroup());
        assertEquals(21, dot.v.normalSqr());
        assertEquals(4.58, dot.v.normal(), 0.01);

        assertEquals("\\mathbf{u}\\cdot\\mathbf{v}", dot.uv.formatName());
        assertEquals("9.2+-9.1+3.4", dot.uv.formatExpanded());
        assertEquals("18+-9+12", dot.uv.formatGrouped());
        assertEquals("21", dot.uv.formatResult());
        assertEquals("\\approx\\ang{69}", dot.uv.formatAngle());
    }

    @Test
    public void testGetSection() throws Exception {
        ScalarProductModule dot = new ScalarProductModule(new IntegerGenerator() {
            private int[] ints = {3, 3, -4, 4, -2, 1, 7};
            private int idx = 0;
            @Override public int next(int lower, int uppper) {
                return ints[idx++];
            }
        });

        assertEquals(dot.getSection("question"), dot.getSection("QUESTION"));
        assertEquals(dot.getSection("SolUTion"), dot.getSection("sOlUtION"));
        assertEquals(dot.getSection("ansWER"), dot.getSection("ANSwer"));

        assertEquals(
                "\\[\\text{Let }\\mathbf{u}=\\begin{pmatrix}3\\\\-4\\\\4\\\\\\end{pmatrix}" +
                "\\text{and }\\mathbf{v}=\\begin{pmatrix}-2\\\\1\\\\7\\\\\\end{pmatrix}\\text{.}\\]" +
                "\\begin{enumerate}[(a)]" +
                "\\item Determine $\\mathbf{u}\\cdot\\mathbf{v}$." +
                "\\item Determine the angle in degrees (to the nearest degree) between $\\mathbf{u}$ and $\\mathbf{v}$." +
                "\\end{enumerate}"
                ,
                dot.getSection("question")
            );
        assertEquals(
                "\\begin{enumerate}[(a)]" +
                "\\item \\begin{align*}" +
                "\\mathbf{u}\\cdot\\mathbf{v}&=3.-2+-4.1+4.7\\\\&=-6+-4+28\\\\&=18\\end{align*}" +
                "\\item \\begin{align*}" +
                "\\text{From (a) }\\mathbf{u}\\cdot\\mathbf{v}&=18\\\\\\\\" +
                "\\lVert\\mathbf{u}\\rVert&=\\sqrt{3^2+(-4)^2+4^2}\\\\&=\\sqrt{41}\\\\\\\\" +
                "\\lVert\\mathbf{v}\\rVert&=\\sqrt{(-2)^2+1^2+7^2}\\\\&=\\sqrt{54}\\\\\\\\" +
                "\\mathbf{u}\\cdot\\mathbf{v}&=\\lVert\\mathbf{u}\\rVert\\lVert\\mathbf{v}\\rVert\\cos\\theta\\\\" +
                "\\\\" +
                "\\text{So }18&=\\sqrt{41}\\sqrt{54}\\cos\\theta\\\\\\\\" +
                "\\text{Therefore }\\theta&=\\arccos\\dfrac{18}{\\sqrt{41}\\sqrt{54}}\\\\" +
                "\\text{So }\\theta&\\approx\\ang{68}\\end{align*}\\end{enumerate}"
                ,
                dot.getSection("solution")
            );
        assertEquals(
                "\\begin{enumerate}[(a)]" +
                "\\item $\\mathbf{u}\\cdot\\mathbf{v}=18$" +
                "\\item $\\theta\\approx\\ang{68}$" +
                "\\end{enumerate}"
                ,
                dot.getSection("answer")
            );
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
