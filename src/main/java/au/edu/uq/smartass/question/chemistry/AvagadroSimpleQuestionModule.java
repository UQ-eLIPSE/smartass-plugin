package au.edu.uq.smartass.question.chemistry;


import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.Date;
import java.util.Random;


/**
 */
public abstract class AvagadroSimpleQuestionModule extends SimpleQuestionModule {

    protected static final double AVAGADRO_NUM = 6.022e23;    // [ mol^-1 ]

    protected static final String SI_NOTA =
            "\\newcommand{\\scientific}[1]{" +
            "\\num[round-precision=4,round-mode=figures,scientific-notation=true]{#1}}\n";

    protected static long generateRandomInt(int bound) {
        return new Random(new Date().getTime()).nextInt(bound);
    }

    protected String createSolutionTex(String substance, double molar, double grm, double mol, double ans) {
        String tex = SI_NOTA +
                String.format("The molar mass of %1$s is $%2$.2f$g mol$^{-1}$.\\\\\n", substance, molar) +
                "The number of moles $=$ mass $\\div$ molar mass " +
                String.format("$=\\dfrac{%1$.2f}{%2$.1f}=%3$.4f$ moles.\\\\\n", grm, molar, mol) +
                "Therefore the number of molecules $=$ moles $\\times$ Avogadro’s number \n" +
                String.format("$=%1$.4f\\times(\\scientific{%2$.4e})\n", mol, AVAGADRO_NUM ) +
                String.format("=\\scientific{%.4e}$ molecules.", ans)
                ;
        return tex;
    }

    protected String createAnswerTex(double ans) {
        String tex = SI_NOTA + String.format("\\scientific{%1$.4e}", ans);
        return tex;
    }
}
