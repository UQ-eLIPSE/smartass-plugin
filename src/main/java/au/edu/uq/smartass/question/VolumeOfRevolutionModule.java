/**
 * Name: Volume of Revolution
 * Description: Calculates the volume of a revolution
 * Keywords: integration, volume, revolution
 * Author: Roy Portas
 * Classification: [MATHEMATICS] Derivatives and integration
 */
package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;
import java.util.Random;
import java.lang.Math;
import java.util.Locale;

/**
 * <code>QuestionModule</code> for SmartAss plugin.
 *
 * Creates a volume of revolution question
 *
 *     f(x) = sqrt(x^a ln(x)) about the x-axis over [b, c]
 *
 * where the power of the x-term (a) and the limits of integration (b, c) are within the range
 * [LOWER_BOUND, UPPER_BOUND] and b < c;
 */
public class VolumeOfRevolutionModule extends SimpleQuestionModule {

    /** The lower bound for the a, b, c variables **/
    private final int LOWER_BOUND = 2;

    /** The upper bound for the a, b, c variables **/
    private final int UPPER_BOUND = 10;

    /** The answer to the question **/
    private double answer;

    /**
     * Calculates part of the equation
     * @param a The power of the x-term (ie x^a)
     * @param bound An integration limit at which to calculate.
     */
    private double calculatePart(int a, int bound) {
        return (
                Math.PI/(a+1) * Math.pow(bound, (a+1)) * Math.log(bound)
                - (Math.PI * Math.pow(bound, (a+1))/(Math.pow((a+1), 2)))
            );
    }

    /**
     * Calculates the final answer, as a double
     * @return The answer to the question
     */
    private double calculateAnswer(int a, int b, int c) {
        return calculatePart(a, c) - calculatePart(a, b);
    }

    double getAnswer() {
        return answer;
    }

    /**
     * Initialise a volume of revolution question with known parameters.
     *
     * @param a The power of x-term
     * @param b The lower integration limit
     * @param c The upper integration limit
     */
    private void createQuestion(int a, int b, int c) {
        assert( 2 <= a && a <= 10 );
        assert( 2 <= b && b < c );
        assert( 3 <= c && c <= 10 );

        answer = calculateAnswer(a, b, c);

        createQuestionTeX(a, b, c);
        createSolutionTeX(a, b, c);
        createAnswerTeX(answer);
    }

    /**
     * Construct a VolumeOfRevolutionModule with known parameters.
     *
     * For params, see class docs.
     */
    public VolumeOfRevolutionModule(int a, int b, int c) {
        createQuestion(a, b, c);
    }

    /**
     * Default public constructor.
     * Used by plugin architecture <code>ServiceLoader</code>.
     * Creates object with random parameter in range 0 < a <= 10.
     */
    public VolumeOfRevolutionModule() {
        int a = getRandomInt(UPPER_BOUND - LOWER_BOUND + 1, LOWER_BOUND);
        int c = getRandomInt(UPPER_BOUND - LOWER_BOUND, LOWER_BOUND + 1);
        int b = getRandomInt(c - LOWER_BOUND, LOWER_BOUND);

        createQuestion(a, b, c);
    }

    private void createQuestionTeX(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        sb.append("Find the volume of the solid obtained by rotating ");
        sb.append("$f(x)=\\sqrt{x^{" + a + "}\\ln{x}}$ about the $x$-axis over $[" + b + ", " + c + "]$.");
        setQuestion(sb.toString());
    }

    private void createSolutionTeX(int a, int b, int c) {
        int a1 = a + 1;

        StringBuilder sb = new StringBuilder();
        sb.append("\\begin{align*}\n");
        sb.append("\\text{Volume }&=\\pi\\int_a^b(f(x))^2\\,dx\\\\\n");
        sb.append("&=\\pi\\int_{" + b + "}^{" + c + "}\\left(\\sqrt{x^{" + a + "}\\ln{x}}\\,\\right)^2\\,dx\\\\\n");
        sb.append("&=\\pi\\int_{" + b + "}^{" + c + "}{x^{" + a + "}\\ln{x}}\\,dx\\\\\n");
        sb.append("\\end{align*}\n");
        sb.append("Let's use integration by parts as the two functions are not \n");
        sb.append("related to each other in terms of derivatives.\n");
        sb.append("\\begin{align*}\n");
        sb.append("&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n");
        sb.append("&\\text{Let }v'=x^{" + a + "}\\text{, then }v=\\dfrac{1}{" + a + "+1}x^{" + a + "+1}");
        sb.append("=\\dfrac{1}{" + a1 + "}\\cdot{x}^{" + a1 + "}.\\\\\n");
        sb.append("\\end{align*}\n");
        sb.append("\\begin{align*}\n");
        sb.append("\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n");
        sb.append("\\text{So }\\pi\\int_{" + b + "}^{" + c + "}{x}^{" + a + "}\\ln{x}\\,dx");
        sb.append("&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\ln{x}\\right]_{" + b + "}^{" + c + "}");
        sb.append("-\\pi\\int_{" + b + "}^{" + c + "}\\dfrac{1}{x}\\cdot\\dfrac{1}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\,dx\\\\\n");
        sb.append("&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\ln{x}\\right]_{" + b + "}^{" + c + "}");
        sb.append("-\\dfrac{\\pi}{" + a1 + "}\\int_{" + b + "}^{" + c + "}{x}^{" + a + "}\\,dx\\\\\n");
        sb.append("&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\ln{x}");
        sb.append("-\\dfrac{\\pi}{" + a1 + "}\\cdot\\dfrac{1}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\right]_{" + b + "}^{" + c + "}\\\\\n");
        sb.append("&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\ln{x}");
        sb.append("-\\dfrac{\\pi}{" + (a1 * a1 ) + "}\\cdot{x}^{" + a1 + "}\\right]_{" + b + "}^{" + c + "}\\\\\n");
        sb.append("&=\\dfrac{\\pi}{" + a1 + "}\\cdot{" + c + "}^{" + a1 + "}\\ln{" + c + "}-\\dfrac{\\pi}{" + a1 + "}\\cdot{" + b + "}^{" + a1 + "}\\ln{" + b + "}-\\dfrac{\\pi}{" + (a1 * a1) + "}\\cdot{" + c + "}^{" + a1 + "}+\\dfrac{\\pi}{" + (a1 * a1) + "}\\cdot{" + b + "}^{" + a1 + "}\\\\\n");
        sb.append(String.format("&\\approx%1$.3f\n", answer));
        sb.append("\\end{align*}");

        setSolution(sb.toString());
    }

    private void createAnswerTeX(double answer) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("$\\approx %1$,.3f$\n", answer).replaceAll(",", "")); 

        setAnswer(sb.toString());
    }
}
