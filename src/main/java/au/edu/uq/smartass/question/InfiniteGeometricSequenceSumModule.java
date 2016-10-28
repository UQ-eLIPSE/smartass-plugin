package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

/**
 */
public class InfiniteGeometricSequenceSumModule extends SimpleQuestionModule {

    public InfiniteGeometricSequenceSumModule() {
        this(
                getRandomInt(9, 2) * new Double(Math.pow(-1, getRandomInt(2, 0))).intValue(),
                getRandomInt(9, 2) * new Double(Math.pow(-1, getRandomInt(2, 0))).intValue()
        );
    }

    InfiniteGeometricSequenceSumModule(int a, int r) {
        assert( 2 <= Math.abs(a) && Math.abs(a) <= 10 );
        assert( 2 <= Math.abs(r) && Math.abs(r) <= 10 );

        int numer = a;
        int b_denom = r;
        int c_denom = r * r;

        int ans_n = a * r;
        int ans_d = r - 1;

        setQuestion(createQuestionTex(a, numer, b_denom, c_denom));
        setSolution(createSolutionTex(r, a, numer, b_denom, ans_n, ans_d));
        setAnswer(createAnswerTex(ans_n, ans_d));
    }

    private String createQuestionTex(int a, int n, int b1, int c1) {
        String b_str = fmtDfracTex(n, b1);
        String c_str = fmtDfracTex(n, c1);
        String tex =
                String.format("Let $%1$d,%2$s,%3$s$ ", a, b_str, c_str) +
                "be the first three terms of an infinite geometric sequence. \n" +
                "What is the sum of the corresponding series?";
        return tex;
    }

    private String createSolutionTex(int r, int a, int b_n, int b_d, int ans_n, int ans_d) {
        String b_str = fmtDfracTex(b_n, b_d);
        String r_str = fmtDfracTex(1, r);
        String r_sign = (r < 0) ? "-" : "";
        int r_abs = Math.abs(r);
        String a_sign = (a < 0) ? "-" : "";
        int a_abs = Math.abs(a);

        String tex =
                "\\begin{align*}\n" +
                "S_{\\infty}&=\\dfrac{a}{1-r}\\text{, where }\n" +
                String.format(
                        "r=%2$s\\div%1$d=%3$s\\text{ and }a=%1$d.\\\\\n",
                        a, b_str, r_str) +
                String.format(
                        "S_{\\infty}&=%2$s\\dfrac{%1$d}{1%4$s\\tfrac{1}{%3$d}}\\\\\n",
                        a_abs, a_sign, r_abs, (r < 0) ? "+" : "-") +
                String.format("&=%2$s\\dfrac{%1$d}{\\tfrac{%3$d}{%4$d}}\\\\\n", a_abs, a_sign, Math.abs(r-1), r_abs) +
                String.format("&=%s\n", fmtDfracTex(ans_n, ans_d)) +
                "\\end{align*}";
        return tex;
    }

    private String createAnswerTex(int numer, int denom) {
        return String.format("$%s$", fmtDfracTex(numer, denom));
    }

    private String fmtDfracTex(int numer, int denom) {
        String sign = ( 0 > numer*denom ) ? "-" : "";
        return
                (0 == numer%denom) ?    String.format("%1$s%2$d", sign, Math.abs(numer/denom)) :
                (0 == denom%numer) ?    String.format("%1$s\\dfrac{1}{%2$d}", sign, Math.abs(denom/numer)) :
                                        fmtDfracTeXHelper(sign, Math.abs(numer), Math.abs(denom));
    }

    private String fmtDfracTeXHelper(String sign, int numerator, int denominator) {
        assert(numerator > 0);
        assert(denominator > 0);
        int common = getCommonDivisor(numerator, denominator);
        return String.format(
                "%1$s\\dfrac{%2$d}{%3$d}", sign, numerator/common, denominator/common
            );
    }

    private int getCommonDivisor(int a, int b) {
        for (int d = 9; d >= 1; --d ) {
            if (0 == a%d && 0 == b%d) return d;
        }
        throw new Error();
    }

}
