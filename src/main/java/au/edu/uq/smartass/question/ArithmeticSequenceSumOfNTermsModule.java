package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.EnumMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Creates a randomised question, solution and answer
 * Calculate the sum of the first n terms within a given sequence.
 */
public class ArithmeticSequenceSumOfNTermsModule extends SimpleQuestionModule {

    /**
     * Constructor for ArithmeticSequenceSumOfNTermsModule takes 3 parameters
     * @param numA First number in the sequence.
     * @param diff Difference between each number in the sequence.
     * @param term The nth term.
     */
    ArithmeticSequenceSumOfNTermsModule(int numA, int diff, int term) {
        int numB = numA + diff;
        int numC = numB + diff;
        int result = term/2 * (2 * numA + (term-1) * diff);

        setQuestion(createQuestionTex(numA, numB, numC, term));
        setSolution(createSolutionTex(numA, numB, diff, term, result));
        setAnswer(createAnswerTex(result));
    }

    /**
     * Publicly accessible constructor takes no parameters.
     * Generates random values for numA, diff and term.
     * Passes random values to package private constructor.
     */
    public ArithmeticSequenceSumOfNTermsModule() {
        this(
                QUtil.generatePosInt(2,2),
                QUtil.generateNegToPosInt(-10, 10),
                QUtil.generatePosInt(5, 50)
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
        return format(
                "Let $%1$d,%2$d,%3$d$ be the first three terms of a finite arithmetic sequence. " +
                "Determine the sum of the first $%4$d$ terms in the sequence.",
                numA, numB, numC, term
            );
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
        String tex =
                "\\begin{align*}\n" +
                format(
                        "S_n&=\\dfrac{n}{2}(2a+(n-1)d)\\text{, where }d=%2$d-%1$d=%3$d\\text{ and }a=%1$d.\\\\\n",
                        numA, numB, diff ) +
                format(
                        "\\text{Therefore }S_{%2$d}&=\\dfrac{%2$d}{2}(2\\cdot %1$d +(%2$d-1)\\cdot %3$d)\\\\\n",
                        numA, term, diff ) +
                format("&=%2$d(%1$d+%3$d\\cdot%4$d)\\\\\n", 2 * numA, term / 2, term - 1, diff) +
                format("&=%d\n", result) +
                "\\end{align*}";
        return tex;
    }

    /**
     * Creates the LaTeX string for the Answer Section.
     * @param result The final result of the sum of all numbers in sequence up to the nth term
     * @return LaTeX formatted string for Answer.
     */
    private String createAnswerTex(int result) {
        return format("$%d$", result);
    }
}
