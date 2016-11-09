package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.text.DecimalFormat;

/**
 */
public class VolumeSolidModule extends SimpleQuestionModule {

    public VolumeSolidModule() {
        int a = getRandomInt(5, 1);
        int b = getRandomInt(10-a, 1+a);
        init(a, b);
    }

    VolumeSolidModule(final int a, final int b) {
        init(a, b);
    }

    private void init(final int a, final int b) {
        assert( 0 < a && a <= 5 );
        assert( a < b && b <= 10 );

        int aa = a*a;
        int bb = b*b;
        int ab = a*b;

        int ans2 = bb - 2*ab + aa;  // answer multiplied by 2
        DecimalFormat dfmt = new DecimalFormat("0.##");
        String ansfmt = String.format("%1$s\\pi\\text{ units}^3", dfmt.format(ans2/2F));

        setQuestion(createQuestionTex(a, b));
        setSolution(createSolutionTex(a, b, aa, bb, ab, ansfmt));
        setAnswer(createAnswerTex(ansfmt));
    }


    private String createQuestionTex(final int a, final int b) {
        String tex =
                "Find the volume of the solid obtained by rotating\n" +
                String.format("$f(x)=\\sqrt{x-%1$d}$ about the $x$-axis over $[%1$d,%2$d]$.", a, b);

        return tex;
    }

    private String createSolutionTex(
            final int a, final int b,
            final int aa, final int bb, final int ab,
            final String ans
    ) {
        String tex =
                "\\begin{align*}\n" +
                "\\text{Volume }&=\\pi\\int_a^b(f(x))^2\\,dx\\\\\n" +
                String.format("&=\\pi\\int_%1$d^{%2$d}\\left({\\sqrt{x-%1$d}}\\,\\right)^2\\,dx\\\\\n", a, b) +
                String.format("&=\\pi\\int_%1$d^{%2$d}(x-%1$d)\\,dx\\\\\n", a, b) +
                String.format("&=\\pi\\left[{\\dfrac{x^2}{2}-%1$dx}\\right]_%1$d^{%2$d}\\\\\n", a, b) +
                String.format("&=\\left(\\dfrac{(%2$d)^2}{2}-%1$d\\cdot(%2$d)\\right)\\pi\n", a, b) +
                String.format("-\\left(\\dfrac{(%1$d)^2}{2}-%1$d\\cdot(%1$d)\\right)\\pi\\\\\n", a) +
                String.format("&=\\left(\\dfrac{%1$d}{2}-%2$d\\right)\\pi\n", bb, ab) +
                String.format("-\\left(\\dfrac{%1$d}{2}-%1$d\\right)\\pi\\\\\n", aa) +
                String.format("&=%1$s\n", ans) +
                "\\end{align*}";
        return tex;
    }
    private String createAnswerTex(final String ans) {
        String tex =
            String.format("$%1$s$", ans);
        return tex;
    }

}
