package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;
import java.util.Random;

/**
 * P Series Product Module.
 *
 * Required LaTeX packages (should be inculed in 'global' smartass.tex):
 *      \\usepackage{amsmath}
 *      \\usepackage{enumerate}
 *      \\usepackage{siunitx}
 */
public class PSeriesModule extends SimpleQuestionModule {

    private String pValue;
    private boolean doesConverge;

    private void createQuestion(int selection) {
        // Store a list of possible p values
        String[] pValues = new String[7];

        pValues[0] = "\\frac{1}{2}";
        pValues[1] = "\\frac{1}{4}";
        pValues[2] = "\\frac{1}{3}";
        pValues[3] = "1";
        pValues[4] = "2";
        pValues[5] = "3";
        pValues[6] = "4";

        pValue = pValues[selection];
        if (selection < 3) {
            doesConverge = false;
        } else {
            doesConverge = true;
        }

        createQuestionTeX();
        createSolutionTeX();
    }

    public PSeriesModule(int selection) {
        createQuestion(selection);
    }

    /**
     *
     */
    public PSeriesModule() {
        Random rand = new Random();
        int selection = rand.nextInt(7);

        createQuestion(selection);
    }

    private void createQuestionTeX() {
        StringBuilder sb = new StringBuilder();
        sb.append("Does ");
        sb.append("$\\sum\\limits_{n=1}^{\\infty} \\frac{1}{n^{" + pValue + "}}$");
        sb.append(" converge? Explain.");
        setQuestion(sb.toString());
    }

    private void createSolutionTeX() {
        StringBuilder sb = new StringBuilder();
        
        if (doesConverge) {
            sb.append("Yes, it does, by the $p$-series test. Since $p>1$, the series will converge.");
        } else {
            sb.append("No, it doesn't, by the $p$-series test. Since $p<1$, the series will diverge.");
        }
        setSolution(sb.toString());
        setAnswer(sb.toString());
    }

    private void createAnswerTeX() {
        // do nothing;
        // set in createSolutionTex()
    }
}
