package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.String;

/**
 * Area of a Triangle Module.
 * Generates randomised questions to test knowledge of the area of a triangle. The questions
 * are generated as LaTeX sections for insertion into a larger document. Questions, Short
 * Answers and Worked Solutions are produced in each case.
 *
 * Required LaTeX packages (should be inculed in 'global' smartass.tex):
 *      \\usepackage{amsmath}
 *      \\usepackage{enumerate}
 *      \\usepackage{siunitx}
 */
public class AreaOfTriangleModule extends SimpleQuestionModule {

    /** */
    interface IntegerGenerator { int next(int lower, int uppper); }
    private IntegerGenerator integers;

    TriangleVertex vertA;
    TriangleVertex vertB;
    TriangleVertex vertC;

    TriangleVertex vertAB;
    TriangleVertex vertAC;

    AreaCalculation areaCalc;

    /**
     * Question specific classes
     */
    class TriangleVertex {

        String name;
        int x;
        int y;
        int z;
        
        String formatString;
        String formatVectorString;

        public TriangleVertex(String name, int x, int y, int z) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.z = z;

            formatString = String.format("%s(%d,%d,%d)", name, x, y, z);
            formatVectorString = String.format("(%d,%d,%d)", x, y, z);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getZ() {
            return z;
        }

        public String getName() {
            return name;
        }

        /**
         * Returns the string representation of the vertex
         * e.g. A(1, 2, 3)
         */
        public String formatString() {
            return formatString;
        }

        /**
         * Returns the vector format
         */
        public String formatVector() {
            return formatVectorString;
        }

        /**
         * Subtracts a vertex from the current vertex and returns the result
         * e.g. AB = B.subtract(A)
         */
        public TriangleVertex subtract(TriangleVertex other) {
            // Note the order of the name, because vector subtraction
            TriangleVertex result = new TriangleVertex(
                    "\\vec{" + other.name + this.name + "}",
                    this.x - other.getX(),
                    this.y - other.getY(),
                    this.z - other.getZ()
                );

            return result;
        }
    }

    public class AreaCalculation {

        TriangleVertex vertexA;
        TriangleVertex vertexB;
        CrossProduct crossProduct;

        String formatString;

        public AreaCalculation(TriangleVertex vertexA, TriangleVertex vertexB) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;

            crossProduct = new CrossProduct(vertexA, vertexB);
            
            generateFormatString();
        }

        private void generateFormatString() {
            formatString =
                    "\\begin{align*}\n" +
                    String.format(
                            "\\text{Area}&=\\frac{1}{2}|%s\\times%s|\\\\\n",
                            vertexA.getName(), vertexB.getName()) +
                    String.format("%s&=%s\\\\\n", vertexA.getName(), vertexA.formatVector()) +
                    String.format("%s&=%s\\\\\\\\\n", vertexB.getName(), vertexB.formatVector()) +

                    crossProduct.getWorking() +

                    "\\end{align*}";
        }

        /**
         * Returns the latex string of the equation
         */
        public String formatString() {
            return formatString;
        }

        public String getAnswer() {
            return crossProduct.getAnswer();
        }
    }

    /**
     * Calculates and generates latex code for the cross product of two TriangleVertex
     */
    public class CrossProduct {
        private TriangleVertex vertA;
        private TriangleVertex vertB;

        // The i, j and k components
        private int i;
        private int j;
        private int k;
        private double squaredAnswer;

        private String result;
        private String working;
        private String answer;

        public CrossProduct(TriangleVertex vertA, TriangleVertex vertB) {
            this.vertA = vertA;
            this.vertB = vertB;

            generateResult();
            generateWorking();
        }

        public String getResult() {
            return result;
        }

        public String getWorking() {
            return working;
        }

        public String getAnswer() {
            return answer;
        }

        private void generateWorking() {
            working =
                    String.format("%s\\times%s", vertA.getName(), vertB.getName()) +
                    "&=\\left|\\begin{array}{crc}\\textbf{i}&\\textbf{j}&\\textbf{k}\\\\" +
                    String.format("%d&%d&%d\\\\", vertA.getX(), vertA.getY(), vertA.getZ()) +
                    String.format("%d&%d&%d", vertB.getX(), vertB.getY(), vertB.getZ()) +
                    "\\end{array}\\right|\\\\\n" +

                    "&=\\textbf{i}\\left|\\begin{array}{rc}" +
                    String.format("%d&%d\\\\%d&%d", vertA.getY(), vertA.getZ(), vertB.getY(), vertB.getZ()) +
                    "\\end{array}\\right|-\\textbf{j}\\left|\\begin{array}{cc}" +
                    String.format("%d&%d\\\\%d&%d", vertA.getX(), vertA.getZ(), vertB.getX(), vertB.getZ()) +
                    "\\end{array}\\right|+\\textbf{k}\\left|\\begin{array}{cr}" +
                    String.format("%d&%d\\\\%d&%d", vertA.getX(), vertA.getY(), vertB.getX(), vertB.getY()) +
                    "\\end{array} \\right|\\\\\n" +

                    "&=" + result + "\\\\\\\\\n" +

                    String.format("|%s\\times%s|", vertA.getName(), vertB.getName()) +
                    String.format("&=\\sqrt{%d^2+%d^2+%d^2}\\\\\n", i, j, k) +
                    String.format("&=\\sqrt{%.1f}\\\\\\\\\n", squaredAnswer) +

                    String.format("\\text{Therefore area }&=\\frac{1}{2}\\times\\sqrt{%.1f}\\\\\n", squaredAnswer) +
                    String.format("&=\\frac{\\sqrt{%.1f}}{2}\\text{ units}^2\\\\\n", squaredAnswer);

            answer = "$\\frac{\\sqrt{" + String.valueOf(squaredAnswer) + "}}{2}$ units$^2$";
        }


        private void generateResult() {
	        result = new String();
            String[] vecNames = {"i", "j", "k"};
            int dimension = 3;

            Map<String, Integer> vectors = new HashMap<String, Integer>();

            vectors.put("i", (vertA.getY() * vertB.getZ()) - (vertA.getZ() * vertB.getY()));
            vectors.put("j", ((vertA.getX() * vertB.getZ()) - (vertA.getZ() * vertB.getX())) * -1);
            vectors.put("k", ((vertA.getX() * vertB.getY()) - vertA.getY() * vertB.getX()));

            i = Math.abs(vectors.get("i"));
            j = Math.abs(vectors.get("j"));
            k = Math.abs(vectors.get("k"));
            squaredAnswer = Math.pow(i, 2) + Math.pow(j, 2) + Math.pow(k, 2);

            // Add the first element
            result += String.valueOf(vectors.get(vecNames[0]));
            result += "\\textbf{" + vecNames[0] + "}";

            for (int i = 1; i < dimension; i++) {
                String key = vecNames[i];
                if (vectors.get(key) < 0) {
                    result += "-";
                    // Make it positive
                    result += String.valueOf(vectors.get(key) * -1);
                } else {
                    result += "+";
                    result += String.valueOf(vectors.get(key));
                }
                result += "\\textbf{" + key + "}";
            }

        }
    }

    /**
     *
     */
    public AreaOfTriangleModule() {
        this(new IntegerGenerator() {
                private java.util.Random random = new java.util.Random();
                @Override public int next(final int lower, final int upper) {
                    return random.nextInt(upper + 1 - lower) + lower;
                }
            });
    }

    public AreaOfTriangleModule(final IntegerGenerator ints) {
        this.integers = ints;

        initialise();
        createQuestionTeX();
        createSolutionTeX();
        createAnswerTeX();

    }

    private void initialise() {
        // Setup the components

        vertA = new TriangleVertex("A", 
                integers.next(0, 10),
                integers.next(0, 10),
                integers.next(0, 10));

        vertB = new TriangleVertex("B", 
                integers.next(0, 10),
                integers.next(0, 10),
                integers.next(0, 10));

        vertC = new TriangleVertex("C", 
                integers.next(0, 10),
                integers.next(0, 10),
                integers.next(0, 10));

        vertAB = vertB.subtract(vertA);
        vertAC = vertC.subtract(vertA);

        areaCalc = new AreaCalculation(vertAB, vertAC);

    }

    private void createQuestionTeX() {
        setQuestion( String.format(
                "Let $%s$, $%s$ and $%s$ be the three vertices of a triangle. " +
                "Determine the area of $\\triangle ABC$.",
                 vertA.formatString(), vertB.formatString(), vertC.formatString()
            ));
    }

    private void createSolutionTeX() {
        setSolution(areaCalc.formatString());
    }

    private void createAnswerTeX() {
        setAnswer(areaCalc.getAnswer());
    }
}
