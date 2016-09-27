package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

import static java.lang.String.format;

/**
 * Creates a randomised question, solution and answer
 * Find the nth term within a given sequence.
 */
public class ArithmeticSequenceNthTermModule implements QuestionModule {
    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);

    public int numA, numB, numC, diff, term, result;

    /**
     * Constructor for ArithmeticSequenceSumOfNTermsModule takes 3 parameters
     * @param numA First number in the sequence.
     * @param diff Difference between each number in the sequence.
     * @param term The nth term.
     */
    public ArithmeticSequenceNthTermModule(int numA, int diff, int term) {
        this.numA = numA;
        this.term = term;
        this.diff = diff;
        this.numB = numA + diff;
        this.numC = this.numB + diff;
        this.result = numA + (term -1) * diff;

        sectionTeX.put(Section.QUESTION, createQuestionTeX(this.numA, this.numB, this.numC, this.term));
        sectionTeX.put(Section.SOLUTION, createSolutionTeX(this.numA, this.numB, this.diff, this.term, this.result));
        sectionTeX.put(Section.ANSWER, createAnswerTeX(this.result));
    }

    /**
     * Publicly accessible constructor takes no parameters.
     * Generates random values for numA, diff and term.
     * Passes random values to package private constructor.
     */
    public ArithmeticSequenceNthTermModule() {
        this(
                new QUtil().generatePosInt(2,2),
                new QUtil().generateNegToPosInt(-10, 10),
                new QUtil().generatePosInt(10, 50)
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
    private String createQuestionTeX(int numA, int numB, int numC, int term) {
        String ord = new QUtil().getOrdinal(term);
        String sb = ("Let ");
        sb += format("$%1$d,%2$d,%3$d$", numA, numB, numC);
        sb += format(" be an arithmetic sequence. Determine the $%d$", term);
        sb += format("%s term in the sequence.\\\\", ord);
        return sb;
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
    private String createSolutionTeX(int numA, int numB, int diff, int term, int result) {
        String sb = ("$a_n=a+(n-1)d$, where $d=");
        sb += format("%2$d-%1$d=%3$d$ and $a=%1$d$.\\\\", numA, numB, diff);
        sb += format("Therefore $a_{%2$d}=%1$d", numA, term);
        sb += format("+(%1$d-1)\\cdot %2$d$\\\\", term, diff);
        sb += format("$=%1$d+%2$d \\cdot%3$d$\\\\", numA, term - 1, diff);
        sb += format("$=%d$", result);
        return sb;
    }


    /**
     * Creates the LaTeX string for the Answer Section.
     * @param result The final result of the sum of all numbers in sequence up to the nth term
     * @return LaTeX formatted string for Answer.
     */
    private String createAnswerTeX(int result) {
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
        return sectionTeX.get(Enum.valueOf(Section.class, name.toUpperCase()));
    }
}

