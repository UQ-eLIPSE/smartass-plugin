package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

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
public class AreaOfTriangleModule implements QuestionModule {

    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);

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

            formatString = name + "(" + x + ", " + y + ", " + z + ")";
            formatVectorString = "(" + x + ", " + y + ", " + z + ")";
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
            formatString = "";
            formatString += "Area & = \\frac{1}{2}\\|" + vertexA.getName() + " \\times " + vertexB.getName() + "|\\\\";
            formatString += vertexA.getName() + " & = " + vertexA.formatVector() + "\\\\";
            formatString += vertexB.getName() + " & = " + vertexB.formatVector() + "\\\\";

            formatString += crossProduct.getWorking();
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
            working = new String();
	    working += "\\\\" + vertA.getName() + "\\times" + vertB.getName() + "& = \\left|";
	    working += " \\begin{array}{crc}\n" +
		    "\\textbf{i} & \\textbf{j} & \\textbf{k} \\\\\n" +
		    vertA.getX() + "&" + vertA.getY() + "&" + vertA.getZ() + " \\\\\n" +
		    vertB.getX() + "&" + vertB.getY() + "&" + vertB.getZ() + "\\end{array} \\right|";

	    working += "=";
	    working += "\\textbf{i} \\left| \\begin{array}{rc}\n" +
		    vertA.getY() + "&" + vertA.getZ()+ " \\\\\n" +
		    vertB.getY() + "&" + vertB.getZ() + " \\end{array} \\right|\n" +
		    "-";
	    working += "\\textbf{j} \\left| \\begin{array}{cc}\n" +
		    vertA.getX() + "&" + vertA.getZ() + " \\\\\n" +
		    vertB.getX() + "&" + vertB.getZ() + " \\end{array} \\right|\n" +
		    "+";

	    working += "\\textbf{k} \\left| \\begin{array}{cr}\n" +
		    vertA.getX() + "&" + vertA.getY() + " \\\\\n" +
		    vertB.getX() + "&" + vertB.getY() + " \\end{array} \\right|\n" +
		    "=";


	    working += result;
	    working += "\\\\";

            working += "|" + vertA.getName() + " \\times " + vertB.getName() + "| & = \\sqrt{" + i + "^2 + " + j + "^2 + " + k + "^2}\\\\";
            working += "&= \\sqrt{" + String.valueOf(squaredAnswer) + "}\\\\";
            working += "Therefore\\ area & = \\frac{1}{2} \\times \\sqrt{" + String.valueOf(squaredAnswer) + "}\\\\";
            working += " & = \\frac{\\sqrt{" + String.valueOf(squaredAnswer) + "}}{2} units^2\\\\";
            answer = "$\\frac{\\sqrt{" + String.valueOf(squaredAnswer) + "}}{2} units^2$";

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
		    result += " - ";
		    // Make it positive
		    result += String.valueOf(vectors.get(key) * -1);
		} else {
		    result += " + ";
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
        StringBuilder sb = new StringBuilder();
        
        sb.append("Let " + vertA.formatString() + ", " + vertB.formatString() + " and " + vertC.formatString() + " be the three vertices of a triangle. Determine the area of the triangle ABC.\\\\");

        sectionTeX.put(Section.QUESTION, sb.toString());
    }

    private void createSolutionTeX() {
        StringBuilder sb = new StringBuilder();

        sb.append("\\begin{align*}");
        sb.append(areaCalc.formatString());
        sb.append("\\end{align*}");
        
        sectionTeX.put(Section.SOLUTION, sb.toString());
    }

    private void createAnswerTeX() {
        StringBuilder sb = new StringBuilder();
        sb.append(areaCalc.getAnswer());
        sectionTeX.put(Section.ANSWER, sb.toString());
    }

    /**
     * Accessor for LaTeX associated with a section name.
     *
     * @param name The section name for which the LaTeX is required.
     * @return The LaTeX associated with the given section name, or NULL.
     * @throws IllegalArgumentException if the given name does not translate to a valid section.
     */
    @Override public String getSection(final String name) throws IllegalArgumentException {
        return sectionTeX.get(Enum.valueOf(Section.class, name.toUpperCase()));
    }

}
