package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

    /**
     * Question specific classes
     */
    class TriangleVertex {

        String name;
        int x;
        int y;
        int z;
        
        String formatString;

        public TriangleVertex(String name, int x, int y, int z) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.z = z;

            formatString = name + "(" + x + ", " + y + ", " + z + ")";
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
         * Subtracts a vertex from the current vertex and returns the result
         * e.g. AB = B.subtract(A)
         */
        public TriangleVertex subtract(TriangleVertex other) {
            // Note the order of the name, because vector subtraction
            TriangleVertex result = new TriangleVertex(
                    other.name + this.name,
                    this.x - other.getX(),
                    this.y - other.getY(),
                    this.z - other.getZ()
                );

            return result;
        }
    }

    class AreaCalculation {

        TriangleVertex vertexA;
        TriangleVertex vertexB;

        String formatString;


        public AreaCalculation(TriangleVertex vertexA, TriangleVertex vertexB) {
            this.vertexA = vertexA;
            this.vertexB = vertexB;
            
            generateFormatString();
        }

        private void generateFormatString() { 
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

    AreaOfTriangleModule(final IntegerGenerator ints) {
        this.integers = ints;

        initialise();
        createQuestionTeX();
        createSolutionTeX();
        createAnswerTeX();
    }

    private void initialise() {
        // Setup the components
    }

    private void createQuestionTeX() {
        StringBuilder sb = new StringBuilder();
        sectionTeX.put(Section.QUESTION, sb.toString());
    }

    private void createSolutionTeX() {
        StringBuilder sb = new StringBuilder();
        sectionTeX.put(Section.SOLUTION, sb.toString());
    }

    private void createAnswerTeX() {
        StringBuilder sb = new StringBuilder();
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
