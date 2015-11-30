package au.edu.uq.smartass.question.python;

import au.edu.uq.smartass.python.PythonFunction;
import au.edu.uq.smartass.python.PythonMaths;
import au.edu.uq.smartass.python.PythonMathsVariable;
import au.edu.uq.smartass.python.PythonNumber;
import au.edu.uq.smartass.python.RandomPythonGen;

/**
 * Describe class BasicFuncEvalModule here.
 *
 *
 * Created: Tue Dec 16 13:50:42 2008
 *
 * @author <a href="mailto:ilm@morot.maths.uq.edu.au">Ivan Miljenovic</a>
 * @version 1.0
 */
public class BasicFuncEvalModule extends AbstractPythonEvalModule {

    /**
     * Creates a new <code>BasicFuncEvalModule</code> instance.
     *
     */
    public BasicFuncEvalModule() {
        super();

        generate();
    }

    private void generate() {

        PythonFunction f;
        PythonMaths fx;

        PythonMathsVariable x = new PythonMathsVariable("x");

        do {
            clearScript();

            f = RandomPythonGen.randomIntFunc("f", "x", 1, false);

            add(f);

            fx = f.callFunction(x);

            add(RandomPythonGen.assignRandNum(x));
            add(print(fx));
            add(print(f.callFunction(PythonNumber.ZERO)));
            add(print(f.callFunction(x.succ())));
            add(f.callFunction(RandomPythonGen.randomExpr(x, 3, 10, 20, true)));

        } while (!isCalculable());
    }

}
