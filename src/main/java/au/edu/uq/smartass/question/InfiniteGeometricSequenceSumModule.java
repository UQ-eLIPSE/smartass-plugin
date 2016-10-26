package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.Date;
import java.util.Random;

/**
 */
public class InfiniteGeometricSequenceSumModule extends SimpleQuestionModule {

    public InfiniteGeometricSequenceSumModule() {
        this(
                getRandomInt(9, 2) * new Double(Math.pow(-1, getRandomInt(2, 0))).intValue()
        );
    }

    InfiniteGeometricSequenceSumModule(int r) {
        int a = 2;
        int numer = a;
        int b_denom = r;
        int c_denom = r * r;

        if (0 == r % 2) {
            numer = 1;
            b_denom /= 2;
            c_denom /= 2;
        }

        int ans_n = a * r;
        int ans_d = r - 1;

        setQuestion(createQuestionTex(a, numer, b_denom, c_denom));
        setSolution(createSolutionTex(r, a, numer, b_denom, ans_n, ans_d));
        setAnswer(createAnswerTex(ans_n, ans_d));
    }

    private String createQuestionTex(int a, int n, int b1, int c1) {
        String tex =
                String.format("Let $%1$d,\\dfrac{%2$d}{%3$d},\\dfrac{%2$d}{%4$d}$ ", a, n, b1, c1) +
                "be the first three terms of an infinite geometric sequence. \n" +
                "What is the sum of the series?";
        return tex;
    }

    private String createSolutionTex(int r, int a, int b_n, int b_d, int ans_n, int ans_d) {
        String tex =
                "\\begin{align*}\n" +
                "S_{\\infty}&=\\dfrac{a}{1-r}\\text{, where }\n" +
                String.format(
                        "r=\\dfrac{%2$d}{%3$d}\\div%1$d=\\dfrac{1}{%4$d}\\text{ and }a=%1$d.\\\\\n",
                        a, b_n, b_d, r) +
                String.format("S_{\\infty}&=\\dfrac{%1$d}{1-\\tfrac{1}{%2$d}}\\\\\n", a, r) +
                String.format("&=\\dfrac{%1$d}{\\tfrac{%2$d}{%3$d}}\\\\\n", a, r-1, r) +
                String.format("&=\\dfrac{%1$d}{%2$d}\n", ans_n, ans_d) +
                "\\end{align*}";
        return tex;
    }

    private String createAnswerTex(int numer, int denom) {
        String tex = String.format("$\\dfrac{%1$d}{%2$d}$", numer, denom);
        return tex;
    }

}
