package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Creates a randomised question, solution and answer
 * Find how many sums are needed for the sum to first exceed a given number. 
 */
public class GeoSeqSumOfNTermsExceedsNModule implements QuestionModule{
    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);
    int numA,
        numB,
        numC,
        sum,
        ratio,
        result;

    double resultD;

    /**
     * Constructor for GeoSeqSumOfNTermsExceedsNModule, takes 3 parameters.
     * @param numA First number in the sequence.
     * @param ratio The ratio between each number in the sequence.
     * @param sum The target sum of each number in the sequence.
     */
    GeoSeqSumOfNTermsExceedsNModule(int numA, int ratio, int sum) {
        this.numA = numA;
        this.ratio = ratio;
        this.sum = sum;
        this.numB = numA * ratio;
        this.numC = this.numB * ratio;
        this.resultD = Math.log(this.sum) / Math.log(this.numB / this.numA) + 1;
        this.result = (int)Math.ceil(Math.log(this.sum) / Math.log(this.numB / this.numA) + 1);

        sectionTeX.put(Section.QUESTION, createQuestion(this.numA, this.numB, this.numC, this.sum));
        sectionTeX.put(Section.SOLUTION, createSolution(this.numA, this.numB, this.ratio, this.sum, this.resultD, this.result));
        sectionTeX.put(Section.ANSWER, createAnswer(this.result));
    }

    /**
     * Public Constructor for GeoSeqSumOfTermsExceedsNModule.
     * Generates 3 random values numA, ratio, sum.
     * Passes them to secondary constructor.
     */
    public GeoSeqSumOfNTermsExceedsNModule() {
        this(
                new QUtil().generatePosInt(2, 10),
                new QUtil().generateNegToPosInt(-5, 5),
                new QUtil().generateNegToPosInt(10, 10000)
        );
    }

    /**
     * Creates the LaTex string for the Question Section.
     * @param sum The term for which to find the value.
     * @param numA The first number in the sequence.
     * @param numB The second number in the sequence.
     * @param numC The third number in the sequence.
     * @return Returns a LaTeX formatted String for the Question Section.
     */
    private String createQuestion(int numA, int numB, int numC, int sum) {
        return format("Let $%1$d,%2$d,%3$d$ be a geometric sequence. How many sums are needed for the sum to first exceed $%4$d$?", numA, numB, numC, sum);
    }

    /**
     * Creates the LaTeX string for the Solution Section.
     * @param numA The first number in the sequence.
     * @param numB The second number in the sequence.
     * @param ratio The ratio between each number in the sequence.
     * @param sum The term for which to find the value.
     * @param result The final result.
     * @return Returns a LaTeX formatted String for the Solution Section.
     */
    private String createSolution(int numA, int numB, int ratio, int sum, double resultD, int result) {
       String sb = "Let's solve this as an equation then round appropriately. \\\\";
              sb +=  format("$S_n=\\dfrac{a(r^{n-1})}{r-1}$, where $r=%2$d\\div%1$d=%3$d$ and $a=%1$d$.\\\\", numA, numB, ratio);
              sb += format("$%3$d=\\dfrac{%1$d(%2$d^{n-1})}{%2$d-1}$\\\\", numA, ratio, sum);
              sb += format("$%3$d\\times %4$d =%1$d(%2$d^{n-1})$\\\\", numA, ratio, sum, ratio - 1);
              sb += format("$%3$d=%1$d(%2$d^{n-1})$\\\\", numA, ratio, sum * (ratio - 1));
              sb += format("$%1$d=%2$d^{n-1}$\\\\", sum, ratio);
              sb += format("$\\ln %1$d = \\ln(%2$d^{n-1})$\\\\", sum, ratio);
              sb += format("$\\ln %1$d = (n-1)\\ln%2$d$\\\\", sum, ratio);
              sb += format("$n-1=\\dfrac{\\ln %1$d}{\\ln %2$d}$\\\\", sum, ratio);
              sb += format("$n=\\dfrac{\\ln %1$d}{\\ln %2$d}+1$\\\\", sum, ratio);
              sb += format("$= %1$,.2f...$\\\\", resultD);
              sb += format("Therefore $%d$ sums are needed.", result);
        return sb;
    }

    /**
     * Creates the LaTeX string for the Answer Section.
     * @param result The final result.
     * @return Returns a LaTeX String for the Answer.
     */
    private String createAnswer(int result) {
        return format("=%d", result);
    }

    /**
     * Accessor for LaTeX associated with a section name.
     *
     * @param name The section name for which the LaTeX is required.
     * @return The LaTeX associated with the given section name, or NULL.
     * @throws IllegalArgumentException if the given name does not translate to a valid section.
     */
    @Override public String getSection(final String name) throws IllegalArgumentException {
        return sectionTeX.get(Enum.valueOf(GeoSeqSumOfNTermsExceedsNModule.Section.class, name.toUpperCase()));
    }
}
