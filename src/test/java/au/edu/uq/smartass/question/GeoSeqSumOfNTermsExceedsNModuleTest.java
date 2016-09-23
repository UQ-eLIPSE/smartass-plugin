package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

/**
 * Tests for GeoSeqSumOfNTermsExceedsNModule.
 */
public class GeoSeqSumOfNTermsExceedsNModuleTest {

    /**
     * Create a global instance of GeoSeqSumOfNTermsExceedsNModule using known values,
     * provides a known output to test against.
     */
    GeoSeqSumOfNTermsExceedsNModule a = new GeoSeqSumOfNTermsExceedsNModule(2, 3, 1000);

    /**
     * Create a global instance of GeoSeqSumOfNTermsExceedsNModule using random values.
     */
    GeoSeqSumOfNTermsExceedsNModule b = new GeoSeqSumOfNTermsExceedsNModule();

    @Before
    public void setUp() throws Exception {

    }
    @After
    public void tearDown() throws Exception {

    }

    /**
     * Test to ensure a constructor exists and is accessible.
     * @throws Exception
     */
    @Test
    public void testGeoSeqSumOfNTermsExceedsNModule() throws Exception {
        try {
            Constructor<GeoSeqSumOfNTermsExceedsNModule> constructor
                    = GeoSeqSumOfNTermsExceedsNModule.class.getConstructor();
           assert(true);
        } catch (NoSuchMethodException Ex) {
           fail();
        }
    }

    /**
     * Test the generated properties against known values.
     */
    @Test
    public void testGeneratedValues() {
        assertEquals(2, a.numA);
        assertEquals(6, a.numB);
        assertEquals(18, a.numC);
        assertEquals(8, a.result);
    }

    /**
     * Test to compare the expected "Question" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionQuestion() {
        String expected = "Let $2,6,18$ be a geometric sequence. How many sums are needed for the sum to first exceed $1000$?";
        assertEquals(expected, a.getSection("question"));
    }

    /**
     * Test to compare the expected "Solution" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionSolution() {
        String expected = "Let's solve this as an equation then round appropriately. \\\\";
               expected += "$S_n=\\dfrac{a(r^{n-1})}{r-1}$, where $r=6\\div2=3$ and $a=2$.\\\\";
               expected += "$1000=\\dfrac{2(3^{n-1})}{3-1}$\\\\";
               expected += "$1000\\times 2 =2(3^{n-1})$\\\\";
               expected += "$2000=2(3^{n-1})$\\\\";
               expected += "$1000=3^{n-1}$\\\\";
               expected += "$\\ln 1000 = \\ln(3^{n-1})$\\\\";
               expected += "$\\ln 1000 = (n-1)\\ln3$\\\\";
               expected += "$n-1=\\dfrac{\\ln 1000}{\\ln 3}$\\\\";
               expected += "$n=\\dfrac{\\ln 1000}{\\ln 3}+1$\\\\";
               expected += "$= 7.29...$\\\\";                                   // Example sheet reads 7.28
               expected += "Therefore $8$ sums are needed.";
        assertEquals(expected, a.getSection("solution"));
    }

    /**
     * Test to compare the expected "Answer" LaTeX output against a known string using known values.
     */
    @Test
    public void testGetSectionAnswer() {
        assertEquals("=8", a.getSection("answer"));
    }

    /**
     * Tests LaTeX string for Question using random values.
     * Outputs to question_output.tex
     * @throws Exception
     */
    @Test
    public void testRandomIntsQuestion() throws Exception{
        try {
            writeTexFile("question_output.tex", b.getSection("question"));
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Tests LaTeX string for Solution using random values.
     * Outputs to solution_output.tex
     * @throws Exception
     */
    @Test
    public void testRandomIntsSolution() throws Exception {
        try {
            writeTexFile("solution_output.tex", b.getSection("solution"));
        } catch (Exception ex) {
            fail();
        }
    }

    /**
     * Tests LaTeX string for Answer using random values.
     * Outputs to answer_output.tex
     * @throws Exception
     */
    @Test
    public void testRandomIntsAnswer() throws Exception {
        try {
            writeTexFile("answer_output.tex", b.getSection("answer"));
        } catch (Exception ex) {
            fail();
        }
    }

    private void writeTexFile(String filename, String str) throws Exception {
        Files.write(Paths.get("./" + filename), str.getBytes());
    }
}
