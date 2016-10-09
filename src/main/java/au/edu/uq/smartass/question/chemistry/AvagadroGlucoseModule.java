package au.edu.uq.smartass.question.chemistry;

import java.util.Date;
import java.util.Random;


/**
 */
public class AvagadroGlucoseModule extends AvagadroSimpleQuestionModule {

    private static final String GLUCOSE = "glucose";
    private static final double M_GLUCOSE = 180.16;     // Molar Mass [g/mol]

    private static final double MIN_GRM = 0.5;
    private static final double MAX_GRM = 50.0;


    public AvagadroGlucoseModule() {
        this(
                generateRandomInt( (int)(MAX_GRM-MIN_GRM)*0xa ) / 0xa + MIN_GRM
        );
    }

    AvagadroGlucoseModule(double grm) {
        if (MIN_GRM > grm || grm > MAX_GRM) throw new IllegalArgumentException();

        double mol = grm / M_GLUCOSE;
        double ans = mol * AVAGADRO_NUM;

        setQuestion(createQuestionTex(grm));
        setSolution(createSolutionTex(GLUCOSE, M_GLUCOSE, grm, mol, ans));
        setAnswer(createAnswerTex(ans));
    }

    private String createQuestionTex(double grm) {
        String tex =
            String.format("Calculate the number of molecules in %.2fg", grm) +
            " of glucose powder $(C_6H_{12}O_6)$.";
        return tex;
    }

}
