package au.edu.uq.smartass.question.python;

import au.edu.uq.smartass.auxiliary.RandomChoice;
import au.edu.uq.smartass.python.PythonAnd;
import au.edu.uq.smartass.python.PythonConstruct;
import au.edu.uq.smartass.python.PythonGT;
import au.edu.uq.smartass.python.PythonLT;
import au.edu.uq.smartass.python.PythonLogic;
import au.edu.uq.smartass.python.PythonMathsVariable;
import au.edu.uq.smartass.python.PythonNumber;
import au.edu.uq.smartass.python.PythonResultBuffer;
import au.edu.uq.smartass.python.PythonScript;


/**
 * Describe class AbstractPythonEvalModule here.
 *
 *
 * Created: Tue Dec 16 13:11:03 2008
 *
 * @author <a href="mailto:ilm@morot.maths.uq.edu.au">Ivan Miljenovic</a>
 * @version 1.0
 */
public abstract class AbstractPythonEvalModule extends AbstractPythonModule {

    protected final static String NEWLINE = "\n";

    private static final String QUESTION
        = "Write down all of the output generated by the following Python "
        + "program, evaluating it by hand." + NEWLINE;

    private static final String ANSWER
        = "The output is:" + NEWLINE;

    protected PythonScript script;

    /**
     * Creates a new <code>PythonEvalModule</code> instance.
     *
     */
    public AbstractPythonEvalModule() {
        super();

        clearScript();
    }

    protected final void clearScript() {
        script = new PythonScript();
    }

    @Override
    public String getSection(String name) {
        if (name.equals("code")) {
            return code();
        }

        if (name.equals("answers")) {
            return answers();
        }

        return "Section " + name + " NOT found!";
    }

    private String code() {
        return QUESTION + toTex(script);
    }

    private String answers() {
        return ANSWER + toTex(printOutput());
    }

    private PythonResultBuffer printOutput() {
        return script.printOutput();
    }

    protected final int numPrinting() {
        return printOutput().numPrinting();
    }

    protected final boolean isCalculable() {
        return printOutput().isCalculable();
    }

    protected void add(PythonConstruct op) {
        script.add(op);
    }

    protected void addBlank() {
        script.addBlank();
    }

    protected static PythonLogic randRange(PythonMathsVariable x,
                                           int minNum,
                                           //Often evaluated randomly, so it's
                                           //a PythonNumber
                                           PythonNumber maxNum,
                                           int minSep, int maxSep) {

        int max = (int)maxNum.intValue();

        int l, u, d;

        do {
            l = RandomChoice.randInt(minNum, max);
            u = RandomChoice.randInt(l, max);
            d = u - l - 1; // Non-inclusive
        } while (d < minSep || d > maxSep);

        PythonLogic cond = new PythonGT(x, new PythonNumber(l));

        if (u < max) {
            cond = new PythonAnd(
                    cond,
                    new PythonLT(x, new PythonNumber(u))
                );
        }

        return cond;
    }

}
