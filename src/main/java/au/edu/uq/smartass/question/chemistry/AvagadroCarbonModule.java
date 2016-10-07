package au.edu.uq.smartass.question.chemistry;


import java.util.Date;
import java.util.Random;


/**
 */
public class AvagadroCarbonModule extends AvagadroSimpleQuestionModule {

    private static final String CARBON = "carbon";
    private static final double ATOMIC_MASS_CARBON = 12.01;     // [g/mol]

    private static final double MIN_CARAT = 0.1;                // carats
    private static final double MAX_CARAT = 50.0;

    private static final double CARAT = 0.2;                    // grams


    public AvagadroCarbonModule() {
        this (
            generateRandomInt( (int)(MAX_CARAT - MIN_CARAT) * 0xa ) / 0xa + MIN_CARAT
        );
    }


    AvagadroCarbonModule(double carat) {
        if (MIN_CARAT > carat || carat > MAX_CARAT) throw new IllegalArgumentException();

        double grm = carat * CARAT;
        double mol = grm / ATOMIC_MASS_CARBON;
        double ans = mol * AVAGADRO_NUM;

        setQuestion(createQuestionTex(carat, grm));
        setSolution(createSolutionTex(CARBON, ATOMIC_MASS_CARBON, grm, mol, ans));
        setAnswer(createAnswerTex(ans));
    }

    private String createQuestionTex(double carat, double grams) {
        String tex =
                "Calculate the number of carbon atoms that are in a " +
                String.format("$%1$.1f$-carat diamond that weighs $%2$.4f$g.", carat, grams);
        return tex;
    }

    private String createSolutionTex(double grm, double mol, double ans) {
        String tex = SI_NOTA +
                String.format("The atomic mass of carbon is $%1$.2f$g mol$^{-1}$.\\\\\n", ATOMIC_MASS_CARBON) +
                "The number of moles $=$ mass $\\div$ molar mass " +
                String.format("$=\\dfrac{%1$.2f}{%2$.1f}=%3$.4f$ moles.\\\\\n", grm, ATOMIC_MASS_CARBON, mol) +
                "Therefore the number of molecules $=$ moles $\\times$ Avogadro's number " +
                String.format("$=%1$.4f\\times(", mol ) +
                String.format("%s{%e}", SI_NOTA, AVAGADRO_NUM) +
                String.format(")=%s{%e}$ molecules.", SI_NOTA, ans)
            ;
        return tex;
    }
}
