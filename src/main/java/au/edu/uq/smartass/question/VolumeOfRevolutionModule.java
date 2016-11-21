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
 */
public class VolumeOfRevolutionModule extends SimpleQuestionModule {

    /** The lower bound for the a, b, c variables **/
    private final int LOWER_BOUND = 1;

    /** The upper bound for the a, b, c variables **/
    private final int UPPER_BOUND = 10;

    /** The answer to the question **/
    private double answer;

    /**
     * Calculates part of the equation
     * @param a The a parameter, from below
     * @param bound The bound
     */
    private double calculatePart(int a, int bound) {
        return (Math.PI/(a+1) * Math.pow(bound, (a+1)) * Math.log(bound) - (Math.PI * Math.pow(bound, (a+1))/(Math.pow((a+1), 2))));
    }

    /**
     * Calculates the final answer, as a double
     * @return The answer to the question
     */
    private double calculateAnswer(int a, int b, int c) {
        return calculatePart(a, c) - calculatePart(a, b);
    }

    public double getAnswer() {
        return answer;
    }

    /**
     * Creates a volume of revolution question
     *
     * f(x) = sqrt(x^a ln(x)) about the x-axis over [b, c]
     * The variables a, b, c will be integers within the range [LOWER_BOUND, UPPER_BOUND], c &gt; b
     *
     * @param a The a parameter above
     * @param b The b parameter above
     * @param c The c parameter above
     */
    private void createQuestion(int a, int b, int c) {

        answer = calculateAnswer(a, b, c);

        createQuestionTeX(a, b, c);
        createSolutionTeX(a, b, c);
        createAnswerTeX(answer);
    }

    /**
     * Creates a VolumeOfRevolutionModule
     *
     * For params, see createQuestion docs
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
        Random rand = new Random();
        int selection = rand.nextInt(7);

        //createQuestion(selection);
    }

    private void createQuestionTeX(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();

        sb.append("Find the volume of the solid obtained by rotating ");
        sb.append("$f(x)=\\sqrt{x^{" + a + "}\\ln{x}}$ about the $x$-axis over $[" + b + ", " + c + "]$.\n\n");

        setQuestion(sb.toString());
    }

    private void createSolutionTeX(int a, int b, int c) {
        StringBuilder sb = new StringBuilder();
        int a1 = a + 1;

        sb.append("\\begin{align*}\n");
        sb.append("\\text{Volume }&=\\pi\\int_a^b(f(x))^2\\,dx\\\\\n");
        sb.append("&=\\pi\\int_" + b + "^{" + c + "}\\left(\\sqrt{x^{" + a + "}\\ln{x}}\\,\\right)^2\\,dx\\\\\n");
        sb.append("&=\\pi\\int_" + b + "^{" + c + "}{x^{" + a + "}\\ln{x}}\\,dx\\\\\n");
        sb.append("\\end{align*}\n");

        sb.append("Let's use integration by parts as the two functions are not \n");
        sb.append("related to each other in terms of derivatives.\n");
        sb.append("\\begin{align*}\n");
        sb.append("&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n");
        sb.append("&\\text{Let }v'=x^{" + a + "}\\text{, then }v=\\dfrac{1}{" + a + "+1}x^{" + a + "+1}=\\dfrac{1}{" + a1 + "}\\cdot{x^" + a1 + "}.\\\\\n");
        sb.append("\\end{align*}\n");

        sb.append("\\begin{align*}\n");
        sb.append("\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n");
        sb.append("\\text{So }\\pi\\int_" + b + "^{" + c + "}{x}^{" + a + "}\\ln{x}\\,dx&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x^" + a1 + "}\\ln{x}\\right]_" + b + "^{" + c + "}");
        sb.append("-\\pi\\int_" + b + "^{" + c + "}\\dfrac{1}{x}\\cdot\\dfrac{1}{" + a1 + "}\\cdot{x^" + a1 + "}\\,dx\\\\\n");
        sb.append("&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\ln{x}\\right]_" + b + "^{" + c + "}");
        sb.append("-\\dfrac{\\pi}{" + a1 + "}\\int_" + b + "^{" + c + "}{x}^{" + b + "}\\,dx\\\\\n");
        sb.append("&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\ln{x}");
        sb.append("-\\dfrac{\\pi}{" + a1 + "}\\cdot\\dfrac{1}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\right]_" + b + "^{" + c + "}\\\\\n");
        sb.append("&=\\left[\\dfrac{\\pi}{" + a1 + "}\\cdot{x}^{" + a1 + "}\\ln{x}-\\dfrac{\\pi}{" + (a1 * a1 ) + "}\\cdot{x}^{" + a1 + "}\\right]_" + b + "^{" + c + "}\\\\\n");
        sb.append("&=\\dfrac{\\pi}{" + a1 + "}\\cdot{" + c + "}^{" + a1 + "}\\ln{" + c + "}-\\dfrac{\\pi}{" + a1 + "}\\cdot{" + b + "}^{" + a1 + "}\\ln{" + b + "}-\\dfrac{\\pi}{" + (a1 * a1) + "}\\cdot{" + c + "}^{" + a1 + "}+\\dfrac{\\pi}{" + (a1 * a1) + "}\\cdot{" + b + "}^{" + a1 + "}\\\\\n");
        sb.append("&=\\dfrac{\\pi}{" + a1 + "}\\cdot{" + a + "}^{" + a1 + "}\\left(2^" + a1 + "\\ln{" + c + "}-\\ln{" + b + "}-\\dfrac{2^" + a1 + "}{" + a1 + "}+\\dfrac{1}{" + a1 + "}\\right)\\\\\n");
        sb.append(String.format("&\\approx %1$,.3f\n", answer).replaceAll(",", "")); 
        sb.append("\\end{align*}\n");

        setSolution(sb.toString());
    }

    private void createAnswerTeX(double answer) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("&\\approx %1$,.3f\n\n", answer).replaceAll(",", "")); 

        setAnswer(sb.toString());
    }
}
