package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;
import java.util.EnumMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Creates a randomised question, solution and answer
 * Determine the nth term in a Geometric Sequence.
 */
public class GeometricSequenceTermsToNModule implements QuestionModule {

    /** Define supported TeX Sections. */
    enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<GeometricSequenceTermsToNModule.Section,String> sectionTeX = new EnumMap<>(GeometricSequenceTermsToNModule.Section.class);

    int numA, numB, numC, term, ratio, result;

    /**
     * Constructor for GeometricSequenceTermsToNModule takes three parameters.
     * @param numA The first number in the sequence.
     * @param ratio The ratio between each number within the sequence.
     * @param term The term for which to find the value.
     */
    GeometricSequenceTermsToNModule( int numA, int ratio, int term) {
        this.ratio = ratio;
        this.numA = numA;
        this.numB = numA * ratio;
        this.numC = this.numB * this.ratio;
        this.term = term;
        this.result = (int)(this.numA * Math.pow(this.numB / this.numA, this.term - 1));

        sectionTeX.put(Section.QUESTION, createQuestionTeX(this.term, this.numA, this.numB, this.numC));
        sectionTeX.put(Section.SOLUTION, createSolutionTeX(this.numA, this.numB, this.ratio, this.term, this.result));
        sectionTeX.put(Section.ANSWER, createAnswerTeX(this.result));
    }

    /**
     * Publicly accessible constructor takes no parameters.
     * Generates random values for numA, ratio and term.
     * Passes random values to secondary constructor.
     */
    public GeometricSequenceTermsToNModule() {
        this(
                new QUtil().generatePosInt(2,2),
                new QUtil().generateNegToPosInt(-5, 5),
                new QUtil().generatePosInt(5, 50)
        );
    }

    /**
     * Creates the LaTex string for the Question Section.
     * @param term The term for which to find the value.
     * @param numA The first number in the sequence.
     * @param numB The second number in the sequence.
     * @param numC The third number in the sequence.
     * @return Returns a LaTeX formatted String for the Question Section.
     */
    private String createQuestionTeX(int term, int numA, int numB, int numC) {
        String ord = new QUtil().getOrdinal(term);
        String sb = "Let ";
        sb += format("$%d,%d,%d$", numA, numB, numC);
        sb += format(" be a geometric sequence. Determine the ");
        sb += format("$%d$", term);
        sb += format("%s term in the sequence.\\\\", ord);
        return sb;
    }

    /**
     * Creates the LaTeX string for the Solution Section.
     * @param numA The first number in the sequence.
     * @param numB The second number in the sequence.
     * @param ratio The ratio between each number in the sequence.
     * @param term The term for which to find the value.
     * @param result The final result.
     * @return Returns a LaTeX formatted String for the Solution Section.
     */
    private String createSolutionTeX(int numA, int numB, int ratio, int term, int result) {
        String sb = format("$a_n=ar^{n-1}$, where $r=%2$d\\div%1$d=%3$d$ and $a=%1$d$.\\\\", numA, numB, ratio);
        sb += format("so $a_{%3$d}=%1$d\\cdot %2$d^{%4$d}$\\\\", numA, ratio, term, term - 1);
        sb += format("$=%d$", result);
        return sb;
    }

    /**
     * Creates the LaTeX string for the Answer Section.
     * @param result The final result.
     * @return Returns a LaTeX String for the Answer.
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
        return sectionTeX.get(Enum.valueOf(GeometricSequenceTermsToNModule.Section.class, name.toUpperCase()));
    }

}
