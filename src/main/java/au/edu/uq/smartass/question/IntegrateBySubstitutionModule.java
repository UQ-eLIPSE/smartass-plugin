/**
 * Name: Integration by Substitution
 * Description: Solve integration problem using 'substitution' method.
 * Keywords: integration, intergrate
 * Author: Philip Waller
 * Classification: [MATHEMATICS] Derivatives and integration
 */
package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

/**
 * <code>QuestionModule</code> for SmartAss plugin.
 *
 * Given:
 *      Integrate ( (Sam.x^(m-1) + Sbn.x^(n-1)) / (a.x^m + b.x^n) )
 *
 *  where the denominator is a 2 term polynomial with coeffients a,b and
 *  corresponging powers m,n;
 *  the numerator is a multiple (S) of the derivative of the denominator.
 */
public class IntegrateBySubstitutionModule extends SimpleQuestionModule {


    /** Public default Constructor. */
    public IntegrateBySubstitutionModule() {
        int a = getRandomInt(10, 1);
        int m = getRandomInt(10, 1);
        int b = getRandomInt(10, 1);
        int n = getRandomInt(m, 0);
        int S = getRandomInt(10, 1);

        init(a, m, b, n, S);
    }

    /**
     * Package accessible constructor used for creating known state.
     * @param a coefficient of first term.
     * @param m power of first term.
     * @param b coefficient of second term.
     * @param n power of second term.
     * @param S multiple of devivative of the polynomial function
     */
    IntegrateBySubstitutionModule(final int a, final int m, final int b, final int n, final int S) {
        init(a, m, b, n, S);
    }

    /**
     * Initialize the object state.
     * @param a where 0 < a <= 10 is the coefficient of first term.
     * @param m where 0 < m <= 10 is the power of first term.
     * @param b where 0 < b <= 10 is the coefficient of second term.
     * @param n where 0 <= n < m is the power of second term.
     * @param S where 0 < S <= 10 is the multiple of devivative of the polynomial function
     */
    private void init(final int a, final int m, final int b, final int n, final int S) {
        assert( 0 < a && a <= 10 );
        assert( 0 < m && m <= 10 );
        assert( 0 < b && b <= 10 );
        assert( 0 <= n && n < m );
        assert( 0 < S && S <= 10 );

        String poly = formatPolynomial(a, m, b, n);
        String derv = formatPolynomial(a*m, m-1, b*n, n-1);
        String numr = formatPolynomial(S*a*m, m-1, S*b*n, n-1);
        String ques = String.format("\\displaystyle\\int\\dfrac{%1$s}{%2$s}\\,dx", numr, poly);
        String ans = String.format( "%2$s\\ln|%1$s|+C", poly, 1 == S ? "" : Integer.toString(S) );

        setQuestion(createQuestionTex(ques));
        setSolution(createSolutionTex(ques, poly, derv, ans, S));
        setAnswer(createAnswerTex(ans));
    }

    /**
     * Format question LaTeX.
     * @param question string representation of question formula.
     * @return a LaTeX string.
     */
    private String createQuestionTex(final String question) {
        String tex =
                String.format("Determine $%1$s$.", question);
        return tex;
    }

    /**
     * Format solution LaTeX.
     * @param ques string representation of question formula.
     * @param poly string representation of 2 term polynomial (denominator)
     * @param derv string representation of derivative of the denominator.
     * @param ans string representation of the answer formula.
     * @param S integer multiple of the derivative.
     * @return a LaTeX string.
     */
    private String createSolutionTex(
            final String ques, final String poly, final String derv, final String ans,
            final int S
    ) {
        String tex =
                "\\begin{align*}\n" +
                String.format("\\text{Let }u&=%s.\\\\\n", poly) +
                String.format("\\text{Then }\\dfrac{du}{dx}&=%s.\\\\\n", derv) +
                String.format("\\text{So }du&=%s\\,dx.\n", derv) +
                "\\end{align*}\n" +
                "\\begin{align*}\n" +
                ques + "\n" +
                String.format("&=\\displaystyle\\int\\dfrac{%d}{u}\\,du\\\\\n", S) +
                String.format("&=%s\\ln|u|+C\\\\\n", 1==S ? "" : Integer.toString(S)) +
                String.format("&=%1$s\n", ans) +
                "\\end{align*}";
        return tex;
    }

    /**
     * Format answer LaTeX.
     * @param ans string representation of the answer formula.
     * @return a LaTeX string.
     */
    private String createAnswerTex(final String ans) {
        String tex = String.format("$%1$s$", ans);
        return tex;
    }

    /**
     * Create LaTeX string representation of 2 term polynomial.
     * @param a coefficient of first term.
     * @param m power of first term.
     * @param b coefficient of second term.
     * @param n power of second term.
     * @return LaTeX formated string.
     */
    private String formatPolynomial(final int a, final int m, final int b, final int n) {
        String term_1 = formatPolyTerm(a, m);
        String term_2 = formatPolyTerm(b, n);
        String tex = String.format("%1$s%2$s%3$s",
                term_1,
                term_2.startsWith("-") || term_2.isEmpty() ? "" : "+",
                term_2
            );
        return tex;
    }

    /**
     * Create LaTeX string representation of polynomial term.
     * @param a term coefficient
     * @param m term power
     * @return
     *          empty string if a == 0
     *          'a' if m == 0
     *          '[sign]x^{m}' if 1 == |a|
     *          'ax^{m}' generally
     */
    private String formatPolyTerm(final int a, final int m) {
        String tex = "";
        if ( 0 == a ) ;     // do nothing - empty Term
        else if ( 0 == m ) tex += String.format("%d", a);
        else {
            String xpart = (1 == m) ? "x" : String.format("x^{%d}", m);
            if ( 1 == Math.abs(a) )
                tex += String.format("%s%s", a < 0 ? "-" : "", xpart);
            else
                tex += String.format("%d%s", a, xpart);
        }
        return tex;
    }
}
