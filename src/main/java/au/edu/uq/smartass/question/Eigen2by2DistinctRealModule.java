package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Generate Eigenvalue question for a 2x2 matrix with distinct real solutions.
 *
 * Required LaTeX packages (should be inculed in 'global' smartass.tex):
 *      \\usepackage{amsmath}
 *      \\usepackage{enumerate}
 *      \\usepackage{siunitx}
 */
public class Eigen2by2DistinctRealModule extends SimpleQuestionModule {


   /**
     * Default Constructor - required by framework.
     */
    public Eigen2by2DistinctRealModule() {
        this( new Random( new Date().getTime() ).nextInt( DATA.size() ));
    }

    Eigen2by2DistinctRealModule(int index) {
        if (0 > index || index > DATA.size() - 1) throw new IllegalArgumentException();

        int a11, a12, a21, a22;
        int a, b, c;
        int l_1, l_2;

        //
        // Example data[] elements :
        //
        // "A: [-7 -9];[4 8]"
        // "Characteristic polynomial: x^2 - x - 20"
        //                             x^2 - 144
        // "Eigenvalues & eigenvectors: 5 with eigenvector [-3, 4];-4 with eigenvector [-3, 1]"
        //
        String data[] = DATA.get(index).split("\\|");

        String rx;
        Matcher m;

        rx = "A:\\s\\[(.*)\\s(.*)\\];\\[(.*)\\s(.*)\\]";
        m = Pattern.compile(rx).matcher(data[0]);
        if (m.matches()) {
            a11 = Integer.parseInt(m.group(1));
            a12 = Integer.parseInt(m.group(2));
            a21 = Integer.parseInt(m.group(3));
            a22 = Integer.parseInt(m.group(4));
        } else
            throw new PatternSyntaxException("Bad data!", rx, -1);

        rx = "Characteristicpolynomial:(.*)x\\^2((?:.*?\\*?x)|(?:.{0}))(.*)";
        m = Pattern.compile(rx).matcher(data[1].replace(" ", ""));
        if (m.matches()) {
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
        } else
            throw new PatternSyntaxException("Bad data!", rx, -1);

        String eigen[] = data[2].split(":")[1].split(";");
        rx = "(.*)\\swith\\seigenvector\\s\\[(.*)\\]";

        m = Pattern.compile(rx).matcher(eigen[0].trim());
        if (m.matches()) {
            l_1 = Integer.parseInt(m.group(1));
        } else
            throw new PatternSyntaxException("Bad data!", rx, -1);

        m = Pattern.compile(rx).matcher(eigen[1].trim());
        if (m.matches()) {
            l_2 = Integer.parseInt(m.group(1));
        } else
            throw new PatternSyntaxException("Bad data!", rx, -1);

        assert(a == 1);           // always +1
        assert(b == (-a11-a22));
        assert(c == (a11*a22)-(a21*a12));

        //
        //  . Create TeX .
        //

        setQuestion(String.format(QUESTION_TEMPLATE, a11,a12,a21,a22));

        String m11 = String.format(Mjj,a11);
        String m22 = String.format(Mjj,a22);
        String mVals = String.format(M_VALUES, m11, a12, a21, m22);

        String bs = String.format("%+d", b);
        if (1 == Math.abs(b)) bs = bs.replace("1", "");

        setSolution(String.format(
                "%1s\n\\begin{align*}\n%2s\n%3s\n%4s\n%5s\n%6s\n\\end{align*}\n%7s",
                String.format(SOLUTION_LINE01, mVals),
                String.format(SOLUTION_LINE02, mVals),
                String.format(SOLUTION_LINE03, m11, m22, a21,a12),
                String.format(SOLUTION_LINE04, (a11*a22), bs, -(a21*a12)),
                String.format(SOLUTION_LINE05, /*a,*/ bs, c),
                String.format(SOLUTION_LINE06, (-l_1), (-l_2)),
                String.format(SOLUTION_LINE07, l_1, l_2)
        ));

        setAnswer(String.format(ANSWER_TEMPLATE, l_1, l_2));
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
            "&=(\\lambda%+d)(\\lambda%+d)";
    private static final String SOLUTION_LINE07 =
            "Solving $p(\\lambda)=0$ yields the eigenbalues $\\lambda_1=%d$ and $\\lambda_2=%d$.";


    private static final String ANSWER_TEMPLATE =
            new StringBuilder()
                    .append("$\\lambda_1=%1$d,\\lambda_2=%2$d$")
                    .toString()
            ;

    //
    //
    //
    static final List<String> DATA = Arrays.asList(
    "A: [-7 -9];[4 8]|Characteristic polynomial: x^2 - x - 20|Eigenvalues & eigenvectors: 5 with eigenvector [-3, 4];-4 with eigenvector [-3, 1]",
    "A: [-4 9];[4 1]|Characteristic polynomial: x^2 + 3*x - 40|Eigenvalues & eigenvectors: 5 with eigenvector [1, 1];-8 with eigenvector [-9, 4]",
    "A: [-5 -2];[0 -7]|Characteristic polynomial: x^2 + 12*x + 35|Eigenvalues & eigenvectors: -5 with eigenvector [1, 0];-7 with eigenvector [1, 1]",
    "A: [2 0];[-6 3]|Characteristic polynomial: x^2 - 5*x + 6|Eigenvalues & eigenvectors: 3 with eigenvector [0, 1];2 with eigenvector [1, 6]",
    "A: [1 0];[3 9]|Characteristic polynomial: x^2 - 10*x + 9|Eigenvalues & eigenvectors: 9 with eigenvector [0, 1];1 with eigenvector [-8, 3]",
    "A: [9 9];[0 2]|Characteristic polynomial: x^2 - 11*x + 18|Eigenvalues & eigenvectors: 9 with eigenvector [1, 0];2 with eigenvector [-9, 7]",
    "A: [-4 -8];[-5 -7]|Characteristic polynomial: x^2 + 11*x - 12|Eigenvalues & eigenvectors: 1 with eigenvector [-8, 5];-12 with eigenvector [1, 1]",
    "A: [1 8];[9 2]|Characteristic polynomial: x^2 - 3*x - 70|Eigenvalues & eigenvectors: 10 with eigenvector [8, 9];-7 with eigenvector [-1, 1]",
    "A: [-5 0];[1 -3]|Characteristic polynomial: x^2 + 8*x + 15|Eigenvalues & eigenvectors: -3 with eigenvector [0, 1];-5 with eigenvector [-2, 1]",
    "A: [-7 -1];[-7 -1]|Characteristic polynomial: x^2 + 8*x|Eigenvalues & eigenvectors: 0 with eigenvector [-1, 7];-8 with eigenvector [1, 1]",
    "A: [2 -5];[-4 1]|Characteristic polynomial: x^2 - 3*x - 18|Eigenvalues & eigenvectors: 6 with eigenvector [-5, 4];-3 with eigenvector [1, 1]",
    "A: [-8 0];[9 0]|Characteristic polynomial: x^2 + 8*x|Eigenvalues & eigenvectors: 0 with eigenvector [0, 1];-8 with eigenvector [-8, 9]",
    "A: [-7 -7];[-4 5]|Characteristic polynomial: x^2 + 2*x - 63|Eigenvalues & eigenvectors: 7 with eigenvector [-1, 2];-9 with eigenvector [7, 2]",
    "A: [-7 -9];[0 8]|Characteristic polynomial: x^2 - x - 56|Eigenvalues & eigenvectors: 8 with eigenvector [-3, 5];-7 with eigenvector [1, 0]",
    "A: [4 -3];[-6 1]|Characteristic polynomial: x^2 - 5*x - 14|Eigenvalues & eigenvectors: 7 with eigenvector [-1, 1];-2 with eigenvector [1, 2]",
    "A: [-7 0];[2 6]|Characteristic polynomial: x^2 + x - 42|Eigenvalues & eigenvectors: 6 with eigenvector [0, 1];-7 with eigenvector [-13, 2]",
    "A: [8 0];[7 -3]|Characteristic polynomial: x^2 - 5*x - 24|Eigenvalues & eigenvectors: 8 with eigenvector [11, 7];-3 with eigenvector [0, 1]",
    "A: [-8 9];[0 1]|Characteristic polynomial: x^2 + 7*x - 8|Eigenvalues & eigenvectors: 1 with eigenvector [1, 1];-8 with eigenvector [1, 0]",
    "A: [6 -6];[-7 7]|Characteristic polynomial: x^2 - 13*x|Eigenvalues & eigenvectors: 13 with eigenvector [-6, 7];0 with eigenvector [1, 1]",
    "A: [-4 -1];[0 -9]|Characteristic polynomial: x^2 + 13*x + 36|Eigenvalues & eigenvectors: -4 with eigenvector [1, 0];-9 with eigenvector [1, 5]",
    "A: [-5 3];[-8 9]|Characteristic polynomial: x^2 - 4*x - 21|Eigenvalues & eigenvectors: 7 with eigenvector [1, 4];-3 with eigenvector [3, 2]",
    "A: [4 5];[2 7]|Characteristic polynomial: x^2 - 11*x + 18|Eigenvalues & eigenvectors: 9 with eigenvector [1, 1];2 with eigenvector [-5, 2]",
    "A: [9 0];[7 7]|Characteristic polynomial: x^2 - 16*x + 63|Eigenvalues & eigenvectors: 9 with eigenvector [2, 7];7 with eigenvector [0, 1]",
    "A: [-4 0];[0 0]|Characteristic polynomial: x^2 + 4*x|Eigenvalues & eigenvectors: 0 with eigenvector [0, 1];-4 with eigenvector [1, 0]",
    "A: [1 4];[3 5]|Characteristic polynomial: x^2 - 6*x - 7|Eigenvalues & eigenvectors: 7 with eigenvector [2, 3];-1 with eigenvector [-2, 1]",
    "A: [1 0];[-6 2]|Characteristic polynomial: x^2 - 3*x + 2|Eigenvalues & eigenvectors: 2 with eigenvector [0, 1];1 with eigenvector [1, 6]",
    "A: [0 -4];[0 9]|Characteristic polynomial: x^2 - 9*x|Eigenvalues & eigenvectors: 9 with eigenvector [-4, 9];0 with eigenvector [1, 0]",
    "A: [-4 9];[9 -4]|Characteristic polynomial: x^2 + 8*x - 65|Eigenvalues & eigenvectors: 5 with eigenvector [1, 1];-13 with eigenvector [-1, 1]",
    "A: [-8 -4];[0 -7]|Characteristic polynomial: x^2 + 15*x + 56|Eigenvalues & eigenvectors: -7 with eigenvector [-4, 1];-8 with eigenvector [1, 0]",
    "A: [5 -1];[0 -1]|Characteristic polynomial: x^2 - 4*x - 5|Eigenvalues & eigenvectors: 5 with eigenvector [1, 0];-1 with eigenvector [1, 6]",
    "A: [-7 -5];[0 -1]|Characteristic polynomial: x^2 + 8*x + 7|Eigenvalues & eigenvectors: -1 with eigenvector [-5, 6];-7 with eigenvector [1, 0]",
    "A: [-1 5];[0 7]|Characteristic polynomial: x^2 - 6*x - 7|Eigenvalues & eigenvectors: 7 with eigenvector [5, 8];-1 with eigenvector [1, 0]",
    "A: [7 0];[3 3]|Characteristic polynomial: x^2 - 10*x + 21|Eigenvalues & eigenvectors: 7 with eigenvector [4, 3];3 with eigenvector [0, 1]",
    "A: [-4 -2];[-1 -3]|Characteristic polynomial: x^2 + 7*x + 10|Eigenvalues & eigenvectors: -2 with eigenvector [-1, 1];-5 with eigenvector [2, 1]",
    "A: [0 -5];[0 8]|Characteristic polynomial: x^2 - 8*x|Eigenvalues & eigenvectors: 8 with eigenvector [-5, 8];0 with eigenvector [1, 0]",
    "A: [3 3];[8 8]|Characteristic polynomial: x^2 - 11*x|Eigenvalues & eigenvectors: 11 with eigenvector [3, 8];0 with eigenvector [-1, 1]",
    "A: [-6 -5];[8 8]|Characteristic polynomial: x^2 - 2*x - 8|Eigenvalues & eigenvectors: 4 with eigenvector [-1, 2];-2 with eigenvector [-5, 4]",
    "A: [1 3];[-4 -7]|Characteristic polynomial: x^2 + 6*x + 5|Eigenvalues & eigenvectors: -1 with eigenvector [-3, 2];-5 with eigenvector [-1, 2]",
    "A: [-6 -3];[0 -7]|Characteristic polynomial: x^2 + 13*x + 42|Eigenvalues & eigenvectors: -6 with eigenvector [1, 0];-7 with eigenvector [3, 1]",
    "A: [2 0];[-5 -6]|Characteristic polynomial: x^2 + 4*x - 12|Eigenvalues & eigenvectors: 2 with eigenvector [-8, 5];-6 with eigenvector [0, 1]",
    "A: [8 -3];[9 -4]|Characteristic polynomial: x^2 - 4*x - 5|Eigenvalues & eigenvectors: 5 with eigenvector [1, 1];-1 with eigenvector [1, 3]",
    "A: [6 7];[5 4]|Characteristic polynomial: x^2 - 10*x - 11|Eigenvalues & eigenvectors: 11 with eigenvector [7, 5];-1 with eigenvector [-1, 1]",
    "A: [8 -9];[0 -2]|Characteristic polynomial: x^2 - 6*x - 16|Eigenvalues & eigenvectors: 8 with eigenvector [1, 0];-2 with eigenvector [9, 10]",
    "A: [9 9];[7 -9]|Characteristic polynomial: x^2 - 144|Eigenvalues & eigenvectors: 12 with eigenvector [3, 1];-12 with eigenvector [-3, 7]",
    "A: [-2 1];[-6 -9]|Characteristic polynomial: x^2 + 11*x + 24|Eigenvalues & eigenvectors: -3 with eigenvector [-1, 1];-8 with eigenvector [-1, 6]",
    "A: [-2 6];[0 -6]|Characteristic polynomial: x^2 + 8*x + 12|Eigenvalues & eigenvectors: -2 with eigenvector [1, 0];-6 with eigenvector [-3, 2]",
    "A: [-3 0];[3 -9]|Characteristic polynomial: x^2 + 12*x + 27|Eigenvalues & eigenvectors: -3 with eigenvector [2, 1];-9 with eigenvector [0, 1]",
    "A: [8 6];[8 0]|Characteristic polynomial: x^2 - 8*x - 48|Eigenvalues & eigenvectors: 12 with eigenvector [3, 2];-4 with eigenvector [-1, 2]",
    "A: [2 -2];[0 -7]|Characteristic polynomial: x^2 + 5*x - 14|Eigenvalues & eigenvectors: 2 with eigenvector [1, 0];-7 with eigenvector [2, 9]",
    "A: [-3 5];[0 -9]|Characteristic polynomial: x^2 + 12*x + 27|Eigenvalues & eigenvectors: -3 with eigenvector [1, 0];-9 with eigenvector [-5, 6]",
    "A: [5 3];[-4 -2]|Characteristic polynomial: x^2 - 3*x + 2|Eigenvalues & eigenvectors: 2 with eigenvector [-1, 1];1 with eigenvector [-3, 4]",
    "A: [9 8];[-2 -8]|Characteristic polynomial: x^2 - x - 56|Eigenvalues & eigenvectors: 8 with eigenvector [-8, 1];-7 with eigenvector [-1, 2]",
    "A: [1 4];[4 -5]|Characteristic polynomial: x^2 + 4*x - 21|Eigenvalues & eigenvectors: 3 with eigenvector [2, 1];-7 with eigenvector [-1, 2]",
    "A: [-6 -5];[3 2]|Characteristic polynomial: x^2 + 4*x + 3|Eigenvalues & eigenvectors: -1 with eigenvector [-1, 1];-3 with eigenvector [-5, 3]",
    "A: [-3 0];[5 2]|Characteristic polynomial: x^2 + x - 6|Eigenvalues & eigenvectors: 2 with eigenvector [0, 1];-3 with eigenvector [-1, 1]",
    "A: [2 -6];[0 5]|Characteristic polynomial: x^2 - 7*x + 10|Eigenvalues & eigenvectors: 5 with eigenvector [-2, 1];2 with eigenvector [1, 0]",
    "A: [9 8];[0 -4]|Characteristic polynomial: x^2 - 5*x - 36|Eigenvalues & eigenvectors: 9 with eigenvector [1, 0];-4 with eigenvector [-8, 13]",
    "A: [7 0];[0 3]|Characteristic polynomial: x^2 - 10*x + 21|Eigenvalues & eigenvectors: 7 with eigenvector [1, 0];3 with eigenvector [0, 1]",
    "A: [6 0];[8 -9]|Characteristic polynomial: x^2 + 3*x - 54|Eigenvalues & eigenvectors: 6 with eigenvector [15, 8];-9 with eigenvector [0, 1]",
    "A: [-2 0];[-9 3]|Characteristic polynomial: x^2 - x - 6|Eigenvalues & eigenvectors: 3 with eigenvector [0, 1];-2 with eigenvector [5, 9]"
    );
}
