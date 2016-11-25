/**
 * Name: Integration by Parts 2
 * Description: Solve integration problem by solving each part.
 * Keywords: integration, intergrate
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
     * @param a in range 0 < num <= 10
     */
    IntegrateByParts2Module(final int num) {
        assert( 0 < num && num <= 10 );

        String a = Integer.toString(num);

        // Don't display a number if the variable is 1, 
        // instead display nothing
        if (num == 1) {
            a = "";
        }

        String ans = answerTexHelper(a);

        setQuestion(createQuestionTex(a));
        setSolution(createSolutionTex(a, ans));
        setAnswer(createAnswerTex(ans));
    }

    /**
     * Create LaTeX string containing question.
     *
     * @param a The coefficient for the equation 
     * @return LaTeX string question
     */
    private String createQuestionTex(final String a) {
        return "Determine $\\int " + a + " \\ln x \\,dx$. \\\\";
    }

    /**
     * Create LaTeX strign containing solution.
     *
     * @param a the coefficient for the question
     * @param ans LaTeX formatted answer maths.
     * @return LaTeX string solution.
     */
    private String createSolutionTex(final String a, final String ans) {
        String solution = "Let's use integration by parts." +
            "Let $u= \\ln x$, then $u'=\\dfrac1x$.\\\\ Let $v'=" + a + "$, then $v=" + a + "x$.\\\\" +
            "$\\int uv' \\,dx = uv - \\int u'v \\,dx$" +
            "So $\\int " + a + "\\ln x \\,dx = " + a + "x \\cdot \\ln x - \\int \\dfrac1x \\cdot " + a + "x\\,dx$\\\\" +
            "$=" + a + "x \\ln x - \\int " + a + " \\,dx$\\\\" +
            "$" + ans + "$\\\\";
        return solution;
    }

    /**
     * Create LaTeX string containing answer.
     *
     * @param ans LaTeX formatted answer maths.
     * @return LaTeX string solution.
     */
    private String createAnswerTex(final String ans) {
        String tex = "$" + ans + "$\\\\";
        return tex;
    }

    /**
     * LaTeX formatting of the answer.
     * @param a the coefficient of the equation
     * @return LaTeX formatted answer maths.
     */
    private String answerTexHelper(final String a) {
        return "=" + a + "x  \\ln x - " + a + "x +C";
    }
}
