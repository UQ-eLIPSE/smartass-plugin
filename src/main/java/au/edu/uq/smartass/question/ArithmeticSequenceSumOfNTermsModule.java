package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Creates a randomised question, solution and answer
 * Calculate the sum of the first n terms within a given sequence.
 */
public class ArithmeticSequenceSumOfNTermsModule implements QuestionModule {
    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);

    int numA, numB, numC, diff, term, result;

    /**
     * Constructor for ArithmeticSequenceSumOfNTermsModule takes 3 parameters
     * @param numA First number in the sequence.
     * @param diff Difference between each number in the sequence.
     * @param term The nth term.
     */
    ArithmeticSequenceSumOfNTermsModule(int numA, int diff, int term) {
        this.numA = numA;
        this.diff = diff;
        this.term = term;
        this.numB = numA + diff;
        this.numC = this.numB + diff;
        this.result = term / 2 * (2 * numA + (term - 1) * diff);

        sectionTeX.put(Section.QUESTION, createQuestionTex(this.numA, this.numB, this.numC, this.term));
        sectionTeX.put(Section.SOLUTION, createSolutionTex(this.numA, this.numB, this.diff, this.term, this.result));
        sectionTeX.put(Section.ANSWER, createAnswerTex(this.result));
    }

    /**
     * Publicly accessible constructor takes no parameters.
     * Generates random values for numA, diff and term.
     * Passes random values to package private constructor.
     */
    public ArithmeticSequenceSumOfNTermsModule() {
        this(
                new QUtil().generatePosInt(2,2),
                new QUtil().generateNegToPosInt(-10, 10),
                new QUtil().generatePosInt(5, 50)
        );
    }

    /**
     * Creates the LaTeX string for the Question Section.
     * @param numA First number in the sequence.
     * @param numB Second number in the sequence.
     * @param numC Third number in the sequence.
     * @param term The nth term.
     * @return LaTeX formatted string for Question.
     */
    private String createQuestionTex(int numA, int numB, int numC, int term) {
        return format("Let $%1$d,%2$d,%3$d$ be an arithmetic sequence. Determine the sum of the first $%4$d$ terms in the sequence.\\\\", numA, numB, numC, term);
    }

    /**
     * Creates the LaTeX string for the Solution Section.
     * @param numA First number int the sequence.
     * @param numB Second number in the sequence.
     * @param diff Difference between each number in the sequence.
     * @param term The nth term.
     * @param result The final result of the sum of all numbers in sequence up to the nth term.
     * @return LaTeX formatted string for Solution.
     */
    private String createSolutionTex(int numA, int numB, int diff, int term, int result) {
        String sb = format("$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=%2$d-%1$d=%3$d$ and $a=%1$d$.\\\\", numA, numB, diff );
                sb += format("Therefore $S_{%2$d}=\\dfrac{%2$d}{2}(2\\cdot %1$d +(%2$d-1)\\cdot %3$d)$\\\\", numA, term, diff );
                sb += format("$=%2$d(%1$d+%3$d \\cdot%4$d)$\\\\", 2 * numA, term / 2, term - 1, diff);
                sb += format("$=%d$", result);
        return sb;
    }

    /**
     * Creates the LaTeX string for the Answer Section.
     * @param result The final result of the sum of all numbers in sequence up to the nth term
     * @return LaTeX formatted string for Answer.
     */
    private String createAnswerTex(int result) {
        return format("$=%d$", result);
    }

    /**
     * Accessor for LaTeX associated with a section name.
     *
     * @param name The section name for which the LaTeX is required.
     * @return The LaTeX associated with the given section name, or NULL.
     * @throws IllegalArgumentException if the given name does not translate to a valid section.
     */
    @Override public String getSection(final String name) throws IllegalArgumentException {
        return sectionTeX.get(Enum.valueOf(ArithmeticSequenceSumOfNTermsModule.Section.class, name.toUpperCase()));
    }
}
