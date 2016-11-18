/**
 * Name: Integration by Parts
 * Description: Solve integration problem by solving each part.
 * Keywords: integration, intergrate
 * Author: Philip Waller
 * Classification: [MATHEMATICS] Derivatives and integration
 */
package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

/**
 * <code>QuestionModule</code> for SmartAss plugin.
 */
public class IntegrateByPartsModule extends SimpleQuestionModule {

    /**
     * Default public constructor.
     * Used by plugin architecture <code>ServiceLoader</code>.
     * Creates object with random parameter in range 0 < a <= 10.
     */
    public IntegrateByPartsModule() {
        this( getRandomInt(10, 1) );
    }

    /**
     * Package private constructor initializes class objects.
     *
     * @param a in range 0 < a <= 10
     */
    IntegrateByPartsModule(final int a) {
        assert( 0 < a && a <= 10 );

        String que = questionTexHelper(a);
        String ans = answerTexHelper(a);

        setQuestion(createQuestionTex(que));
        setSolution(createSolutionTex(a, que, ans));
        setAnswer(createAnswerTex(ans));
    }

    /**
     * Create LaTeX string containing question.
     *
     * @param q LaTeX formatted mathematical part of question.
     * @return LaTeX string question
     */
    private String createQuestionTex(final String q) {
        return String.format("Determine $%s$.", q);
    }

    /**
     * Create LaTeX strign containing solution.
     *
     * @param a power of x for 1st part
     * @param que LaTeX formatted question maths.
     * @param ans LaTeX formatted answer maths.
     * @return LaTeX string solution.
     */
    private String createSolutionTex(final int a, final String que, final String ans) {
        int a1 = a + 1;

        String uv = String.format("\\dfrac{1}{%1$d}\\cdot{x}^{%1$d}\\ln{x}", a1);

        // If power is '1', don't need to represent.
        String xn = "x" + (1==a ? "" : String.format("^{%d}", a));

        String tex =
                "Let's use integration by parts as the two functions are not\n" +
                "related to each other in terms of derivatives.\n" +
                "\\begin{align*}\n" +
                "&\\text{Let }u=\\ln{x}\\text{, then }u'=\\dfrac{1}{x}.\\\\\n" +
                String.format("&\\text{Let }v'=%2$s\\text{, then }v=\\dfrac{1}{%1$d+1}x^{%1$d+1}.\\\\\n", a, xn) +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                "\\int{uv'}\\,dx&=uv-\\int{u'v}\\,dx\\\\\n" +
                String.format("\\text{So }%2$s&=\\dfrac{1}{%1$d+1}x^{%1$d+1}\\cdot\\ln{x}\n", a, que) +
                String.format("-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{%1$d+1}x^{%1$d+1}\\,dx\\\\\n", a) +
                String.format("&=%1$s\n", uv) +
                String.format("-\\int\\dfrac{1}{x}\\cdot\\dfrac{1}{%1$d}x^{%1$d}\\,dx\\\\\n", a1) +
                String.format("&=%1$s\n", uv) +
                String.format("-\\dfrac{1}{%2$d}\\int%1$s\\,dx\\\\\n", xn.replace("x", "{x}"), a1) +
                String.format("&=%1$s\n", uv) +
                String.format("-\\dfrac{1}{%2$d}\\cdot\\dfrac{1}{%1$d+1}\\cdot{x}^{%1$d+1}+C\\\\\n", a, a1) +
                String.format("&=%1$s\n", uv) +
                String.format("-\\left(\\dfrac{1}{%1$d}\\right)^2x^{%1$d}+C\\\\\n", a1) +
                String.format("&=%1$s\n", ans) +
                "\\end{align*}";
        return tex;
    }

    /**
     * Create LaTeX string containing answer.
     *
     * @param ans LaTeX formatted answer maths.
     * @return LaTeX string solution.
     */
    private String createAnswerTex(final String ans) {
        String tex = String.format("$%1$s$", ans);
        return tex;
    }

    /**
     * LaTeX formatting of the answer.
     * @param a power of x for 1st part
     * @return LaTeX formatted question maths.
     */
    private String questionTexHelper(final int a) {
        String term_1 = "\\int{x}";
        if ( 1 < a ) term_1 += String.format("^{%1$d}", a);
        return String.format("%s\\ln{x}\\,dx", term_1);
    }

    /**
     * LaTeX formatting of the answer.
     * @param a power of x for 1st part
     * @return LaTeX formatted answer maths.
     */
    private String answerTexHelper(final int a) {
        int a1 = a + 1;
        int a1sqr = a1 * a1;

        String tex = String.format(
                "\\dfrac{1}{%1$d}\\cdot{x}^{%1$d}\\ln{x}-\\dfrac{1}{%2$d}\\cdot{x}^{%1$d}+C",
                a1, a1sqr
            );
        return tex;
    }
}
