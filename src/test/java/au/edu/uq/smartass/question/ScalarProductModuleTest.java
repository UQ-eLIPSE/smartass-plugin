package au.edu.uq.smartass.question;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 */
public class ScalarProductModuleTest {

    ScalarProductModule scalarProduct = new ScalarProductModule();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetQuestion() throws Exception {
        assertEquals("", scalarProduct.getQuestion());
    }

    @Test
    public void testGetSolution() throws Exception {
        assertEquals("", scalarProduct.getSolution());
    }

    @Test
    public void testGetAnswer() throws Exception {
        assertEquals("", scalarProduct.getAnswer());
    }

    @Test
    public void testGetSection() throws Exception {
        assertEquals(scalarProduct.getQuestion(), scalarProduct.getSection(ScalarProductModule.Section.QUESTION.name()));
        assertEquals(scalarProduct.getSolution(), scalarProduct.getSection(ScalarProductModule.Section.SOLUTION.name()));
        assertEquals(scalarProduct.getAnswer(), scalarProduct.getSection(ScalarProductModule.Section.ANSWER.name()));

        try {
            scalarProduct.getSection("NonExistantSectionName");
            fail();
        } catch (IllegalArgumentException ex) {
            assertEquals(
                    "No enum constant au.edu.uq.smartass.question.ScalarProductModule.Section.NonExistantSectionName",
                    ex.getMessage()
                );
        }
    }
}