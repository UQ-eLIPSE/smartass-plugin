package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.Random;

/**
 */
public class ArithmeticSeqSumNTermsExceedX extends SimpleQuestionModule {

    private static final int MIN_A = 2;
    private static final int MAX_A = 2;
    private static final int MIN_D = 1;
    private static final int MAX_D = 10;
    private static final int MIN_X = 100;
    private static final int MAX_X = 10000;

    public ArithmeticSeqSumNTermsExceedX() {
        this(
                QUtil.generateNegToPosInt(MIN_A,MAX_A),
                QUtil.generateNegToPosInt(MIN_D,MAX_D),
                QUtil.generateNegToPosInt(MIN_X,MAX_X)
        );
    }

    ArithmeticSeqSumNTermsExceedX(int a, int d, int x) {
        if (MIN_A > a || a > MAX_A) { throw new IllegalArgumentException(); }
        if (MIN_D > d || d > MAX_D) { throw new IllegalArgumentException(); }
        if (MIN_X > x || x > MAX_X) { throw new IllegalArgumentException(); }

        int b = a + d;
        int c = b + d;

        double n = solveQuadratic(d, 2*a-d, -2*x);
        int ans = (int)Math.ceil(n);

        setQuestion(createQuestionTex(a,b,c, x));
        setSolution(createSolutionTex(a,b, d, x, n, ans));
        setAnswer(createAnswerTex(ans));
    }

    private String createQuestionTex(int a, int b, int c, int x) {
        String tex =
                String.format("Let $%d,%d,%d$ ", a, b, c) +
                "be the first three terms of a finite sequence.\n" +
                "How many terms are needed for the sum to first exceed " +
                String.format("$%d$?", x);
        return tex;
    }

    private String createSolutionTex(int a, int b, int d, int x, double n, int ans) {

        int a2 = 2*a;
        int x2 = 2*x;

        String tex =
                "Let's solve this as an equation then round appropriately.\n" +
                "\\begin{align*}\n" +
                "S_n&=\\dfrac{n}{2}(2a+(n-1)d),\\text{ where }" +
                String.format("d=%2$d-%1$d=%3$d\\text{ and }a=%1$d\\\\\n", a,b, d) +
                String.format(
                        "\\text{Therefore }%3$d&=\\dfrac{n}{2}(2\\cdot%1$d+(n-1)\\cdot%2$d)\\\\\n",
                        a, d, x
                    ) +
                String.format("%3$d&=\\dfrac{n}{2}(%1$d+%2$dn-%2$d)\\\\\n", a2, d, x) +
                String.format("%3$d&=n(%2$dn+%1$d)\\\\\n", a2-d, d, x2) +
                String.format("0&=%2$dn^2+%1$dn-%3$d\\\\\n", a2-d, d, x2) +
                String.format("n&\\approx%.2f\n", n) +
                "\\end{align*}\n" +
                String.format("So $%1$d$ terms are required.", ans);
        return tex;
    }

    private String createAnswerTex(int n) {
        String tex = String.format("$%d$", n);
        return tex;
    }

    double solveQuadratic(int a, int b, int c) {
        int discriminant = b*b - 4*a*c;
        if (0 > discriminant) throw new IllegalArgumentException();     // complex solution not possible!
        double rtdis = Math.sqrt(discriminant);
        double rtp = (-b + rtdis)/(2*a);
        double rtn = (-b - rtdis)/(2*a);                                // should be negative

        return rtp;
    }

}
