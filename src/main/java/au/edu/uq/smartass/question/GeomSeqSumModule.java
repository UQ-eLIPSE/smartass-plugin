package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.Date;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Geometric Sequence Module.
 *
 * Required LaTeX packages (should be inculed in 'global' smartass.tex):
 *      \\usepackage{amsmath}
 *      \\usepackage{enumerate}
 *      \\usepackage{siunitx}
 */
public class GeomSeqSumModule extends SimpleQuestionModule {

    private int n;
    private int a;
    private int r;

    private long ans;

    private int[] seq;

    private void createQuestion(int a, int r, int n) {
        this.a = a;
        this.r = r;
        this.n = n;

        this.ans = new Double( a * (Math.pow(r, n)-1) / (r-1) ).longValue();

        seq = new int[3];
        seq[0] = a;
        seq[1] = a * r;
        seq[2] = a * r * r;

        createQuestionTeX();
        createSolutionTeX();
        createAnswerTeX();
    }

    public GeomSeqSumModule(int a, int r, int n) {
        assert( 2 == a );
        assert( 2 <= Math.abs(r) && Math.abs(r) <= 5 );
        assert( 10 <= n && n <= 20 );

        createQuestion(a, r, n);
    }

    /**
     * Default Constructor.
     *
     * Generates values for a, r , n:
     *      a = 2
     *      2 <= |r| <= 5
     *      10 <= n <= 20
     */
    public GeomSeqSumModule() {
        this(
                2,                                     // a = 2
                getRandomInt(4, 2) * new Double(Math.pow(-1, getRandomInt(2, 0))).intValue(),
                getRandomInt(11, 10)
        );
    }

    private static final Random NUM_GEN = new Random(new Date().getTime());
    private static int getRandomInt(int count, int shift) {
        return NUM_GEN.nextInt(count) + shift;
    }


    private void createQuestionTeX() {
        String tex =
                String.format("Let $%1$d, %2$d, %3$d$ ", seq[0],seq[1],seq[2]) +
                "be the first three terms of a geometric sequence. " +
                String.format("What is the sum of the first %d terms?", n);
        setQuestion(tex);
    }

    private void createSolutionTeX() {
        String tex =
                "\\begin{align*}\n" +
                "S_n&=\\dfrac{a(r^n-1)}{r-1}\\text{, where }" +
                String.format("r=%2$d\\div%1$d=%3$d\\text{ and }a=%4$d.\\\\\n", seq[0], seq[1], r, a) +
                String.format("S_{%3$d}&=\\dfrac{%1$d\\cdot(%2$d^{%3$d}-1)}{%2$d-1}\\\\\n", a, r, n) +
                String.format("&=\\dfrac{%1$d\\cdot(%2$d^{%3$d}-1)}{%4$d}\\\\\n", a, r, n, r-1) +
                String.format("&=%1$d\n", ans) +
                "\\end{align*}";
        setSolution(tex);
    }

    private void createAnswerTeX() {
        String tex = String.format("$%1$d$", ans);
        setAnswer(tex);
    }
}
