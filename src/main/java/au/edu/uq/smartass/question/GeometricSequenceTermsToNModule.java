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
     * @param numA
     * @param ratio
     * @param term
     */
    GeometricSequenceTermsToNModule( int numA, int ratio, int term) {
        this.ratio = ratio;
        this.numA = numA;
        this.numB = numA * ratio;
        this.numC = this.numB * this.ratio;
        this.term = term;
        this.result = (int)(this.numA * Math.pow(this.numB / this.numA, this.term - 1));

        createQuestionTeX(this.term, this.numA, this.numB, this.numC);
        createSolutionTeX(this.numA, this.numB, this.ratio, this.term, this.result);
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
     * @param term
     * @param numA
     * @param numB
     * @param numC
     */
    private void createQuestionTeX(int term, int numA, int numB, int numC) {
        String ord = new QUtil().getOrdinal(term);
        String sb = "Let ";
        sb += format("$%d,%d,%d$", numA, numB, numC);
        sb += format(" be a geometric sequence. Determine the ");
        sb += format("$%d$", term);
        sb += format("%s term in the sequence.\\\\", ord);
        sectionTeX.put(Section.QUESTION, sb.toString());
    }

    /**
     * Creates the LaTeX string for the Solution Section.
     * @param numA
     * @param numB
     * @param ratio
     * @param term
     * @param result
     */
    private void createSolutionTeX(int numA, int numB, int ratio, int term, int result) {
        String sb = format("$a_n=ar^{n-1}$, where $r=%2$d\\div%1$d=%3$d$ and $a=%1$d$.\\\\", numA, numB, ratio);
        sb += format("so $a_{%3$d}=%1$d\\cdot %2$d^{%4$d}$\\\\", numA, ratio, term, term - 1);
        sb += format("$=%d$", result);
        sectionTeX.put(Section.SOLUTION, sb.toString());
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
