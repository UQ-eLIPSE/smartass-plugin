package au.edu.uq.smartass.engine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 */
public class SimpleQuestionModuleTest {

    private class SimpleQuestionModuleImpl extends SimpleQuestionModule {
        void setQuestionImpl(String q) { this.setQuestion(q); }
        void setSolutionImpl(String s) { this.setQuestion(s); }
        void setAnswerImpl(String a) { this.setQuestion(a); }
    }

    private SimpleQuestionModule module = new SimpleQuestionModuleImpl();


    @Before
    public void setUp()throws Exception {
        this.module = new SimpleQuestionModuleImpl();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void setQuestion() throws Exception {
        String text = "Question!";
        module.setQuestion(text);
        assertEquals(text, module.getSection("QUESTION"));
    }

    @Test
    public void setSolution() throws Exception {
        String text = "Solution!";
        module.setSolution(text);
        assertEquals(text, module.getSection("SOLUTION"));
    }

    @Test
    public void setAnswer() throws Exception {
        String text = "Answer!";
        module.setAnswer(text);
        assertEquals(text, module.getSection("ANSWER"));
    }

    @Test
    public void getSection() throws Exception {
        assertEquals(module.getSection("question"), module.getSection("QUESTION"));
        assertEquals(module.getSection("SolUTion"), module.getSection("sOlUtION"));
        assertEquals(module.getSection("ansWER"), module.getSection("ANSwer"));

        try {
            module.getSection("NonExistantSectionName");
            fail();
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

}