package au.edu.uq.smartass.question.python;

import au.edu.uq.smartass.auxiliary.RandomChoice;
import au.edu.uq.smartass.python.PythonIf;
import au.edu.uq.smartass.python.PythonLT;
import au.edu.uq.smartass.python.PythonMathsVariable;
import au.edu.uq.smartass.python.PythonNumber;
import au.edu.uq.smartass.python.PythonOp;
import au.edu.uq.smartass.python.PythonPrint;
import au.edu.uq.smartass.python.PythonString;
import au.edu.uq.smartass.python.PythonWhileLoop;
import au.edu.uq.smartass.python.RandomPythonGen;


/**
 * Describe class WhileIfEvalModule here.
 *
 *
 * Created: Fri Dec 19 14:17:30 2008
 *
 * @author <a href="mailto:ilm@morot.maths.uq.edu.au">Ivan Miljenovic</a>
 * @version 1.0
 */
public class WhileIfEvalModule extends AbstractPythonEvalModule {

    private static final int MAX = 50;
    private static final int MIN_PRINT = 7;
    private static final int MAX_PRINT = 12;

    /**
     * Creates a new <code>WhileIfEvalModule</code> instance.
     *
     */
    public WhileIfEvalModule() {
        super();

        generate();
    }

    private void generate() {
        PythonMathsVariable x = RandomPythonGen.randomVar();
        PythonString str = new PythonString(x.name() + " =");

        PythonPrint pr = print(new PythonOp[] {str, x});

        PythonNumber maxNum;

        do {
            clearScript();

            add(RandomPythonGen.assignRandNum(x));

            maxNum = RandomPythonGen.randomNum(20,MAX);

            PythonWhileLoop loop = new PythonWhileLoop(new PythonLT(x, maxNum));

            if (RandomChoice.randBool()) {
                loop.add(print(x));
            }

            PythonIf ifs = new PythonIf(randRange(x, 3, maxNum, 5, 10));

            if (RandomChoice.randBool()) {
                ifs.addIf(pr);
            } else {
                ifs.addElse(pr);
            }

            ifs.addIf(RandomPythonGen.randomIncOp(x));

            loop.add(ifs);

            loop.add(RandomPythonGen.randomIncOp(x));

            add(loop);
            add(print(x));

        } while (!isCalculable() || numPrinting() < MIN_PRINT || numPrinting() > MAX_PRINT);
    }

}
