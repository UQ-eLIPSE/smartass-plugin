package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import static java.lang.String.format;


/**
 * Creates a randomised question, solution and answer
 * Find the nth term within a given sequence.
 */
public class ArithmeticSequenceNthTermModule extends SimpleQuestionModule {

    private int numA, numB, numC, diff, term, result;

    /**
     * Constructor for ArithmeticSequenceSumOfNTermsModule takes 3 parameters
     * @param numA First number in the sequence.
     * @param diff Difference between each number in the sequence.
     * @param term The nth term.
     */
    ArithmeticSequenceNthTermModule(int numA, int diff, int term) {
        this.numA = numA;
        this.term = term;
        this.diff = diff;
        this.numB = numA + diff;
        this.numC = this.numB + diff;
        this.result = numA + (term -1) * diff;

        this.setQuestion(createQuestionTeX(this.numA, this.numB, this.numC, this.term));
        this.setSolution(createSolutionTeX(this.numA, this.numB, this.diff, this.term, this.result));
        this.setAnswer(createAnswerTeX(this.result));
    }

    /**
     * Publicly accessible constructor takes no parameters.
     * Generates random values for numA, diff and term.
     * Passes random values to package private constructor.
     */
    public ArithmeticSequenceNthTermModule() {
        this(
                QUtil.generatePosInt(2,2),
                QUtil.generateNegToPosInt(-10, 10),
                QUtil.generatePosInt(10, 50)
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
        String tex =
                format("Let $%1$d,%2$d,%3$d$", numA, numB, numC) +
                " be the first three terms of a finite arithmetic sequence. " +
                format("Determine the $%1$d$%2$s term in the sequence.", term, QUtil.getOrdinal(term));
        return tex;
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
        String tex =
                "\\begin{align*}\n" +
                format("a_n&=a+(n-1)d,\\text{ where }d=%2$d-%1$d=%3$d\\text{ and }a=%1$d.\\\\\n", numA, numB, diff) +
                format("\\text{Therefore }a_{%2$d}&=%1$d+(%2$d-1)\\cdot%3$d\\\\\n", numA, term, diff) +
                format("&=%1$d+%2$d\\cdot%3$d\\\\\n", numA, term - 1, diff) +
                format("&=%1$d\n", result) +
                "\\end{align*}";
        return tex;
    }

    /**
     * Creates the LaTeX string for the Answer Section.
     * @param result The final result of the sum of all numbers in sequence up to the nth term
     * @return LaTeX formatted string for Answer.
     */
    private String createAnswerTeX(int result) {
        return format("$%d$", result);
    }
}

