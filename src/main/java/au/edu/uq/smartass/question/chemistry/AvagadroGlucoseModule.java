package au.edu.uq.smartass.question.chemistry;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.Date;
import java.util.Random;

/**
 */
public class AvagadroGlucoseModule extends SimpleQuestionModule {

    private static final double AVAGADRO_NUM = 6.022e23;    // [ mol^-1 ]

    private static final double MIN_GRM = 0.5;
    private static final double MAX_GRM = 50.0;

    private static final double M_GLUCOSE = 180.16;     // Molar Mass [g/mol]

    private static final String SI_NOTA =
            "\\num[round-precision=4,round-mode=figures,scientific-notation=true]";

    public AvagadroGlucoseModule() {
        this(
                new Random(new Date().getTime()).nextInt((int)(MAX_GRM-MIN_GRM)*0xa) / 0xa
        );
    }

    AvagadroGlucoseModule(double grm) {
        if (MIN_GRM > grm || grm > MAX_GRM) throw new IllegalArgumentException();

        double mol = grm / M_GLUCOSE;
        double ans = mol * AVAGADRO_NUM;

        setQuestion(createQuestionTex(grm));
        setSolution(createSolutionTex(grm, mol, ans));
        setAnswer(createAnswerTex(ans));
    }

    private String createQuestionTex(double grm) {
        String tex =
            String.format("Calculate the number of molecules in %.2fg", grm) +
            " of glucose powder $(C_6H_{12}O_6)$.";
        return tex;
    }

    private String createSolutionTex(double grm, double mol, double ans) {
        String tex =
                String.format("The molar mass of glucose is $%1$.1f$g mol$^{-1}$.\\\\\n", M_GLUCOSE) +
                "The number of moles $=$ mass $\\div$ molar mass " +
                String.format("$=\\dfrac{%1$.2f}{%2$.1f}=%3$.4f$ moles.\\\\\n", grm, M_GLUCOSE, mol) +
                "Therefore the number of molecules $=$ moles $\\times$ Avogadro’s number " +
                String.format("$=%1$.4f\\times(", mol ) +
                String.format("%s{%e}", SI_NOTA, AVAGADRO_NUM) +
                String.format(")=%s{%e}$ molecules.", SI_NOTA, ans)
            ;
        return tex;
    }

    private String createAnswerTex(double ans) {
        String tex = String.format("%s{%e}", SI_NOTA, ans);
        return tex;
    }
}
