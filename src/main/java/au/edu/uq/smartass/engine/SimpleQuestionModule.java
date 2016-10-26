package au.edu.uq.smartass.engine;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * Provides base functionality for simple question types. The simple question type is characterised by generating
 * complete question, solution and answer sections that can be used with the simple default template.
 *
 * *note: The class is marked as abstract despite not having abstract methods.
 */
public abstract class SimpleQuestionModule implements QuestionModule {

    /** Define supported TeX Sections. */
    private enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);

    protected void setQuestion(String questionTex) { sectionTeX.put(Section.QUESTION, questionTex); }
    protected void setSolution(String solutionTex) { sectionTeX.put(Section.SOLUTION, solutionTex); }
    protected void setAnswer(String answerTex) { sectionTeX.put(Section.ANSWER, answerTex); }

    protected static final Random NUM_GEN = new Random(new Date().getTime());
    protected static int getRandomInt(int count, int shift) {
        return NUM_GEN.nextInt(count) + shift;
    }

    /**
     * Accessor for LaTeX associated with a section name.
     *
     * @param name The section name for which the LaTeX is required.
     * @return The LaTeX associated with the given section name, or NULL.
     * @throws IllegalArgumentException if the given name does not translate to a valid section.
     */
    @Override public String getSection(final String name) throws IllegalArgumentException {
        return sectionTeX.get(Enum.valueOf(Section.class, name.toUpperCase()));
    }

}
