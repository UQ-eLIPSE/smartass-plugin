package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import static java.lang.String.format;


/**
 * Creates a randomised question, solution and answer
 * Determine the nth term in a Geometric Sequence.
 */
public class GeometricSequenceTermsToNModule extends SimpleQuestionModule {

    /**
     * Constructor for GeometricSequenceTermsToNModule takes three parameters.
     * @param numA The first number in the sequence.
     * @param ratio The ratio between each number within the sequence.
     * @param term The term for which to find the value.
     */
    GeometricSequenceTermsToNModule(int numA, int ratio, int term) {
        int numB = numA * ratio;
        int numC = numB * ratio;
        int result = (int)(numA * Math.pow(numB / numA, term - 1));

        setQuestion(createQuestionTeX(term, numA, numB, numC));
        setSolution(createSolutionTeX(numA, numB, ratio, term, result));
        setAnswer(createAnswerTeX(result));
    }

    /**
     * Publicly accessible constructor takes no parameters.
     * Generates random values for numA, ratio and term.
     * Passes random values to secondary constructor.
     */
    public GeometricSequenceTermsToNModule() {
        this(
                QUtil.generatePosInt(2,2),
                QUtil.generateNegToPosInt(-5, 5),
                QUtil.generatePosInt(5, 50)
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
        String ord = QUtil.getOrdinal(term);

        String tex = format(
                "Let $%1$d,%2$d,%3$d$ be the first three terms of a geometric sequence. " +
                "Determine the $%4$d$%5$s term in the sequence.",
                numA, numB, numC, term, ord
            );
        return tex;
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
        String tex =
                "\\begin{align*}" +
                format("a_n&=ar^{n-1}\\text{, where }r=%2$d\\div%1$d=%3$d\\text{ and }a=%1$d.\\\\", numA, numB, ratio) +
                format("\\text{So }a_{%3$d}&=%1$d\\cdot%2$d^{%4$d}\\\\", numA, ratio, term, term - 1) +
                format("&=%d", result) +
                "\\end{align*}";
        return tex;
    }

    /**
     * Creates the LaTeX strng for the Answer Section.
     * @param result The final result.
     * @return Returns a LaTeX String for the Answer.
     */
    private String createAnswerTeX(int result) {
        return format("$%d$", result);
    }

}
