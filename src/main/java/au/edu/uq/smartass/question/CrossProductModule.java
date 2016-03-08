package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

// @review Do not import whole package.
import java.util.*;

/**
 * Cross Product Module
 * Generates random cross product questions
 */
public class CrossProductModule implements QuestionModule{
    /**
     * Define supported TeX sections
     */
    public enum Section {QUESTION, SOLUTION, ANSWER};

    private Map<Section, String> sectionTex = new EnumMap<>(Section.class);

    // Store the math objects
    // @review Should be static ?
    // @review Accesibility?
    final int dimension = 3;
    Vector u;
    Vector v;
    CrossProduct crossProduct;

    // Create an instance of the random number generator
    IntegerGenerator integers;

    /**
     * A class that implements the IntegerGenerator
     * Used for generating random numbers
     */
    class RandomNumberGenerator implements IntegerGenerator{
        Random random;

        public RandomNumberGenerator() {
            random = new Random();
        }

        // @review Use attributes, they indicate intent (@Override)
        public int next(int lower, int upper) {
            return random.nextInt(upper + 1 - lower) + lower;
        }
    }

    /**
     * Represents the mathematical operation of calculating the cross product
     */
    class CrossProduct {
        private Vector u;
        private Vector v;

        private String formatName;
        private String formatExpanded;
        private String formatGrouped;
        private String formatResult;

        CrossProduct(final Vector u, final Vector v) {
            this.u = u;
            this.v = v;

            assert(u.dimension() == v.dimension());

            formatName = u.formatName() + "\\times" + v.formatName();

            List<String> expandTokens = new ArrayList<>();
            List<String> groupedTokens = new ArrayList<>();

            formatResult = generateResult();
        }

        /**
         * Generates the numerical result of the cross product
         * The result is in the form '3i - 2j + 4k'
         */
        public String generateResult() {
            String result = new String();

            // @review Use Map?
            // @review Use of 'Magic' Numbers!
            int[] vectors = new int[3];
            // @review Use generic type List<>
            ArrayList<String> vecNames = new ArrayList<String>();
            vecNames.add("i");
            vecNames.add("j");
            vecNames.add("k");

            vectors[0] = (u.get(1) * v.get(2)) - (u.get(2) * v.get(1));
            vectors[1] = ((u.get(0) * v.get(2)) - (u.get(2) * v.get(0))) * -1;
            vectors[2] = ((u.get(0) * v.get(1)) - u.get(1) * v.get(0));

            // @review for loop with coditional test based on index?
            for (int i = 0; i < 3; i++) {
                if (i != 0) {
                    // @review Eliminate conditional with formating?
                    if (vectors[i] < 0) {
                        result += " - ";
                        // Make it positive
                        result += String.valueOf(vectors[i] * -1);
                    } else {
                        result += " + ";
                        result += String.valueOf(vectors[i]);
                    }
                    result += "\\textbf{" + vecNames.get(i) + "}";
                } else {
                    result += String.valueOf(vectors[i]);
                    result += "\\textbf{" + vecNames.get(i) + "}";
                }
            }

            return result;
        }
    }

    // @review accesibility? should be package as provided for testing only
    public CrossProductModule(IntegerGenerator generator) {
        initialise(generator);
    }

    public CrossProductModule() {
        initialise(new RandomNumberGenerator());
    }

    private void initialise(IntegerGenerator integers) {
        // @review aim for strong cohesion but low coupling!
        u = new Vector("u", dimension, integers);
        v = new Vector("v", dimension, integers);
        crossProduct = new CrossProduct(u, v);

        createQuestionTex();
        createAnswerTex();
        createSolutionTex();
    }

    private void createQuestionTex() {
        // @review String question = ""; ??
        String question = new String();
        question += "Let $ \\mathbf{u}= \\left(\\begin{array}{c} " + u.get(0) + " \\\\ " +
                u.get(1) + "\\\\" + u.get(2) + "\\end{array} \\right)$ and $ \\mathbf{ v} =\\left(\\begin{array}{c} " + v.get(0) +
                "\\\\ " + v.get(1) + "\\\\" + v.get(2) + "\\end{array} \\right) $. Determine ${\\bf u} \\times {\\bf v}$.";
        sectionTex.put(Section.QUESTION, question);
    }

    private void createSolutionTex() {
        String working = new String();

        working += "$\\textbf{u}\\times\\textbf{v} = \\left|";
        working += " \\begin{array}{crc}\n" +
                "\\textbf{i} & \\textbf{j} & \\textbf{k} \\\\\n" +
                u.get(0) + "&" + u.get(1) + "&" + u.get(2) + " \\\\\n" +
                v.get(0) + "&" + v.get(1) + "&" + v.get(2) + "\\end{array} \\right|";

        working += "=";
        working += "\\textbf{i} \\left| \\begin{array}{rc}\n" +
                u.get(1) + "&" + u.get(2)+ " \\\\\n" +
                v.get(1) + "&" + v.get(2) + " \\end{array} \\right|\n" +
                "-";
        working += "\\textbf{j} \\left| \\begin{array}{cc}\n" +
                u.get(0) + "&" + u.get(2) + " \\\\\n" +
                v.get(0) + "&" + v.get(2) + " \\end{array} \\right|\n" +
                "+";

        working += "\\textbf{k} \\left| \\begin{array}{cr}\n" +
                u.get(0) + "&" + u.get(1) + " \\\\\n" +
                v.get(0) + "&" + v.get(1) + " \\end{array} \\right|\n" +
                "=";

        working += crossProduct.generateResult();
        working += "$";
        sectionTex.put(Section.SOLUTION, working);
    }

    private void createAnswerTex() {
        sectionTex.put(Section.ANSWER, crossProduct.generateResult());
    }

    // @review Attribute!
    public String getSection(final String name) throws IllegalArgumentException {
        return sectionTex.get(Enum.valueOf(Section.class, name.toUpperCase()));
    }

}
