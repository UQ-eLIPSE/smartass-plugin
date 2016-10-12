package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

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

    private int[] seq;

    private void createQuestion(int a, int r, int n) {
        this.a = a;
        this.r = r;
        this.n = n;

        seq = new int[3];
        seq[0] = a;
        seq[1] = a * r;
        seq[2] = a * r * r;

        createQuestionTeX();
        createSolutionTeX();
        createAnswerTeX();
    }

    /**
     * Formats the double so it is displayed corrected by latex
     */
    private String formatDouble(double number) {
        BigDecimal bg = new BigDecimal(number);
        bg = bg.setScale(2, RoundingMode.CEILING);
        return bg.toPlainString(); 
    }

    public GeomSeqSumModule(int a, int r, int n) {
        createQuestion(a, r, n);
    }

    /**
     *
     */
    public GeomSeqSumModule() {
        Random rand = new Random();
        int a = rand.nextInt(4) + 1;
        int r = rand.nextInt(10) - 5;
        int n = rand.nextInt(10) + 10;

        createQuestion(a, r, n);
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
                String.format("S_{%3$d}&=\\dfrac{%1$d(%2$d^{%3$d-1})}{%3$d-1}\\\\\n", a, r, n) +
                String.format("&=\\dfrac{%1$d\\cdot%2$d^{%3$d}}{%3$d}\\\\\n", a, r, n-1) +
                String.format("&\\approx%1$s\n", formatDouble(this.a * Math.pow(this.r, this.n-1) / (this.n-1))) +
                "\\end{align*}";
        setSolution(tex);
    }

    private void createAnswerTeX() {
        String tex =
                String.format("$%1$s$", formatDouble(this.a * Math.pow(this.r, this.n-1) / (this.n-1)));
        setAnswer(tex);
    }
}
