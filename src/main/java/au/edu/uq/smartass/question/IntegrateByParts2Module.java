/**
 * Name: Integration by Parts 2
 * Description: Solve integration problem by solving each part.
 * Keywords: integration, integrate
 * Author: Roy Portas
 * Classification: [MATHEMATICS] Derivatives and integration
 */
package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

/**
 * <code>QuestionModule</code> for SmartAss plugin.
 *
 * Given:
 *      Integrate (a ln x)
 *
 * where a is a integer between 1 and 10 inclusive
 */
public class IntegrateByParts2Module extends SimpleQuestionModule {

    /**
     * Default public constructor.
     * Used by plugin architecture <code>ServiceLoader</code>.
     * Creates object with random parameter in range 0 < a <= 10.
     */
    public IntegrateByParts2Module() {
        this( getRandomInt(10, 1) );
    }

    /**
     * Package private constructor initializes class objects.
     *
     * @param num in range 0 < num <= 10
     */
    IntegrateByParts2Module(final int num) {
        assert( 0 < num && num <= 10 );

        String aterm = ( 1 == num) ? "" : Integer.toString(num);

        String ans = getAnswerTex(aterm);

        setQuestion(createQuestionTex(aterm));
        setSolution(createSolutionTex(num, aterm, ans));
        setAnswer(createAnswerTex(ans));
    }

    /**
     * Returns the latex answer to the problem
     * For use is both the answer and solution section
     * @param aterm The coefficient of the equation
     * @returns The answer tex
     */
    String getAnswerTex(final String aterm) {
        return aterm + "x\\ln{x}-" + aterm + "x+C";
    }

    /**
     * Create LaTeX string containing question.
     *
     * @param aterm The coefficient for the equation
     * @return LaTeX string question
     */
    private String createQuestionTex(final String aterm) {
        return "Determine $\\displaystyle\\int" + aterm + "\\ln{x}\\,dx$.\n";
    }

    /**
     * Create LaTeX strign containing solution.
     *
     * @param a the coefficient for the question
     * @param aterm The coefficient of the equation
     * @param ans LaTeX formatted answer maths.
     * @return LaTeX string solution.
     */
    private String createSolutionTex(final int a, final String aterm, final String ans) {
        String part001 = aterm + "x\\ln{x}";
        String tex =
                "Let's use integration by parts as the two functions are not\n" +
                "related to each other in terms of derivatives.\n" +
                "\\begin{align*}\n" +
                "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" +
                "&\\text{Let }v'=" + a + "\\text{, then }v=" + aterm + "x.\\\\\n" +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" +
                "\\text{So }\\int{" + aterm + "}\\ln{x}\\,dx\n" +
                "&=" + part001 + "-\\int\\dfrac{1}{x}\\cdot{" + aterm + "x}\\,dx\\\\\n" +
                "&=" + part001 + "-\\int" + a + "\\,dx\\\\\n" +
                "&=" + ans + "\n" +
                "\\end{align*}\n";
        return tex;
    }

    /**
     * Create LaTeX string containing answer.
     *
     * @param ans LaTeX formatted answer maths.
     * @return LaTeX string solution.
     */
    private String createAnswerTex(final String ans) {
        return "$" + ans + "$\n";
    }

}
