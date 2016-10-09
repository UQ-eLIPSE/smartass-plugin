package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Creates a randomised question, solution and answer
 * Find how many sums are needed for the sum to first exceed a given number. 
 */
public class GeoSeqSumOfNTermsExceedsNModule extends SimpleQuestionModule{

    int numA, numB, numC;
    int sum;
    int ratio;
    int result;

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

        setQuestion(createQuestion(this.numA, this.numB, this.numC, this.sum));
        setSolution(createSolution(this.numA, this.numB, this.ratio, this.sum, this.resultD, this.result));
        setAnswer(createAnswer(this.result));
    }

    /**
     * Public Constructor for GeoSeqSumOfTermsExceedsNModule.
     * Generates 3 random values numA, ratio, sum.
     * Passes them to secondary constructor.
     */
    public GeoSeqSumOfNTermsExceedsNModule() {
        this(
                QUtil.generatePosInt(2, 10),
                QUtil.generateNegToPosInt(-5, 5),
                QUtil.generateNegToPosInt(10, 10000)
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
        return format(
                "Let $%1$d,%2$d,%3$d$ be the first three terms of a geometric sequence. " +
                "How many terms are needed for the sum to first exceed $%4$d$?",
                numA, numB, numC, sum
            );
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
        String tex =
                "Let's solve this as an equation then round appropriately.\n" +
                "\\begin{align*}\n" +
                "S_n&=\\dfrac{a(r^{n-1})}{r-1}" +
                format("\\text{, where }r=%2$d\\div%1$d=%3$d\\text{ and }a=%1$d.\\\\\n", numA, numB, ratio) +
                format("%3$d&=\\dfrac{%1$d(%2$d^{n-1})}{%2$d-1}\\\\\n", numA, ratio, sum) +
                format("%3$d\\times%4$d&=%1$d(%2$d^{n-1})\\\\\n", numA, ratio, sum, ratio - 1) +
                format("%3$d&=%1$d(%2$d^{n-1})\\\\\n", numA, ratio, sum * (ratio - 1)) +
                format("%1$d&=%2$d^{n-1}\\\\\n", sum, ratio) +
                format("\\ln%1$d&=\\ln(%2$d^{n-1})\\\\\n", sum, ratio) +
                format("\\ln%1$d&=(n-1)\\ln%2$d\\\\\n", sum, ratio) +
                format("n-1&=\\dfrac{\\ln%1$d}{\\ln%2$d}\\\\\n", sum, ratio) +
                format("n&=\\dfrac{\\ln%1$d}{\\ln%2$d}+1\\\\\n", sum, ratio) +
                format("&=%.2f...\n", resultD) +
                "\\end{align*}\n" +
                format("Therefore $%d$ terms are needed.", result);
        return tex;
    }

    /**
     * Creates the LaTeX string for the Answer Section.
     * @param result The final result.
     * @return Returns a LaTeX String for the Answer.
     */
    private String createAnswer(int result) {
        return format("%d", result);
    }
}
