package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 */
public abstract class AbstractEigen2x2Module extends SimpleQuestionModule {

    protected int a11, a12, a21, a22;
    protected int a, b, c;
    protected int[] l_1, l_2;


    AbstractEigen2x2Module(int index) {
        intialise(index);
        createTeX();
    }

    protected abstract String getData(int i);

    private void intialise(int index) {

        //
        // Example data[] elements :
        //
        // "A: [-7 -9];[4 8]"
        // "Characteristic polynomial: x^2 - x - 20"
        //                             x^2 - 144
        // "Eigenvalues & eigenvectors: 5 with eigenvector [-3, 4];-4 with eigenvector [-3, 1]"
        //
        String data[] = getData(index).split("\\|");

        String rx;
        Matcher m;

        rx = "A:\\s*\\[(.*)\\s(.*)\\];\\[(.*)\\s(.*)\\]";
        m = Pattern.compile(rx).matcher(data[0]);
        if (!m.matches())
                throw new PatternSyntaxException("Bad data [matrix A]!", rx, -1);
        a11 = Integer.parseInt(m.group(1));
        a12 = Integer.parseInt(m.group(2));
        a21 = Integer.parseInt(m.group(3));
        a22 = Integer.parseInt(m.group(4));

        rx = "Characteristicpolynomial:(.*)x\\^2((?:.*?\\*?x)|(?:.{0}))(.*)";
        m = Pattern.compile(rx).matcher(data[1].replace(" ", ""));
        if (!m.matches())
                throw new PatternSyntaxException("Bad data! [Characteristic Equation]", rx, -1);
        a =
                m.group(1).isEmpty()    ?    1 :
                m.group(1).equals("-")  ?   -1 :
                                            Integer.parseInt(m.group(1));
        b =
                m.group(2).isEmpty()    ?    0 :
                m.group(2).equals("+x") ?    1 :
                m.group(2).equals("-x") ?   -1 :
                                            Integer.parseInt(m.group(2).replace("*x", ""));
        c =
                m.group(3).isEmpty()    ?    0 :
                                            Integer.parseInt(m.group(3));

        String eigen[] = data[2].split(":")[1].split(";");
        rx = "(.*)\\swith\\seigenvector\\s\\[(.*)\\]";

        m = Pattern.compile(rx).matcher(eigen[0].trim());
        if (!m.matches())
                throw new PatternSyntaxException("Bad data! [lambda_1]", rx, -1);
        l_1 = decodeEigenValue(m.group(1));

        m = Pattern.compile(rx).matcher(eigen[1].trim());
        if (!m.matches())
                throw new PatternSyntaxException("Bad data! [lambda_2]", rx, -1);
        l_2 = decodeEigenValue(m.group(1));

        assert(a == 1);           // always +1
        assert(b == (-a11-a22));
        assert(c == (a11*a22)-(a21*a12));
    }

    private int[] decodeEigenValue(String input) {
        int[] value = new int[]{0,0};
        String cleanIn = input.replace(" ", "");

        if (!cleanIn.contains("I")) {
            value[0] = Integer.parseInt(cleanIn);

        } else {
            String rx = "([+-]?\\d+)??([+-]?(?:\\d*\\*)?)?I";
            Matcher m = Pattern.compile(rx).matcher(cleanIn);
            if (!m.matches())
                throw new PatternSyntaxException(
                        String.format("BadData decoding Eigen Value! [%s]", input), rx, -1
                );
            value[0] =
                    null == m.group(1)  ?    0 :
                                            Integer.parseInt(m.group(1));
            value[1] =
                    null == m.group(2)      ?    1 :
                    m.group(2).isEmpty()    ?    1 :
                    m.group(2).equals("+")  ?    1 :
                    m.group(2).equals("-")  ?   -1 :
                                                Integer.parseInt(m.group(2).replace("*", ""));
        }
        return value;
    }

    private void createTeX() {
        //
        //  . Create TeX .
        //

        setQuestion(String.format(QUESTION_TEMPLATE, a11,a12,a21,a22));

        String m11 = String.format(Mjj,a11);
        String m22 = String.format(Mjj,a22);
        String mVals = String.format(M_VALUES, m11, a12, a21, m22);

        String bs = String.format("%+d", b);
        if (1 == Math.abs(b)) bs = bs.replace("1", "");

        String lambda1 = formatLambda(l_1);
        String lambda1_neg = formatLambda(new int[]{-l_1[0], -l_1[1]});
        String lambda2 = formatLambda(l_2);
        String lambda2_neg = formatLambda(new int[]{-l_2[0], -l_2[1]});

        setSolution(String.format(
                "%1s\n\\begin{align*}\n%2s\n%3s\n%4s\n%5s\n%6s\n\\end{align*}\n%7s",
                String.format(SOLUTION_LINE01, mVals),
                String.format(SOLUTION_LINE02, mVals),
                String.format(SOLUTION_LINE03, m11, m22, a21,a12),
                String.format(SOLUTION_LINE04, (a11*a22), bs, -(a21*a12)),
                String.format(SOLUTION_LINE05, /*a,*/ bs, c),
                String.format(SOLUTION_LINE06, lambda1_neg, lambda2_neg),
                String.format(SOLUTION_LINE07, lambda1, lambda2)
        ));

        setAnswer(String.format(ANSWER_TEMPLATE, lambda1, lambda2));
    }

    private String formatLambda(int[] num) {
        StringBuilder sb = new StringBuilder();
        if (0==num[1])              // Simple Number (Eigen Value)
            sb.append(num[0]);
        else {                      // Complex Number (Eigen Value)
            if (0 != num[0]) sb.append(String.format("%+d", num[0]));
            if (1 == num[1]) sb.append("+");
            else if (-1 == num[1]) sb.append("-");
            else
                sb.append(String.format("%+d", num[1]));
            sb.append("i");
        }
        return sb.toString();
    }

    //
    //  .STATIC templates.
    //
    private static final String QUESTION_TEMPLATE =
            new StringBuilder()
                    .append("Find the eigenvalues of ")
                    .append("\\(\\begin{bmatrix}")
                    .append("%d&%d\\\\%d&%d")
                    .append("\\end{bmatrix}\\).")
                    .toString()
            ;

    private static final String Mjj = "%d-\\lambda";
    private static final String M_VALUES = "%s&%d\\\\%d&%s";

    private static final String SOLUTION_LINE01 =
            new StringBuilder()
                    .append("\\begin{gather*}\n")
                    .append("A-\\lambda{I}=\\begin{bmatrix}")
                    .append("%s")
                    .append("\\end{bmatrix}.\n")
                    .append("\\end{gather*}")
                    .toString()
            ;
    private static final String SOLUTION_LINE02 =
            new StringBuilder()
                    .append("p(\\lambda)&=|A-\\lambda{I}|=")
                    .append("\\begin{vmatrix}%s\\end{vmatrix}.\\\\")
                    .toString()
            ;
    private static final String SOLUTION_LINE03 =
            new StringBuilder()
                    .append("&=(%s)(%s)-(%d\\times%d)\\\\")
                    .toString()
            ;
    private static final String SOLUTION_LINE04 =
            new StringBuilder()
                    .append("&=%d%s\\lambda+\\lambda^2%+d\\\\")
                    .toString()
            ;
    private static final String SOLUTION_LINE05 =
            "&=\\lambda^2%s\\lambda%+d\\\\";
    private static final String SOLUTION_LINE06 =
            "&=(\\lambda%s)(\\lambda%s)";
    private static final String SOLUTION_LINE07 =
            "Solving $p(\\lambda)=0$ yields the eigenvalues $\\lambda_1=%s$ and $\\lambda_2=%s$.";


    private static final String ANSWER_TEMPLATE =
            new StringBuilder()
                    .append("$\\lambda_1=%1$s,\\lambda_2=%2$s$")
                    .toString()
            ;

}
