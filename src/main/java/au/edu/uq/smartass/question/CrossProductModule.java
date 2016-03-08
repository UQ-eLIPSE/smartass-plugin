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
    // @review Should be static ? done
    // @review Accesibility? done
    static private final int dimension = 3;
    private Vector u;
    private Vector v;
    private CrossProduct crossProduct;

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

        // @review Use attributes, they indicate intent (@Override) done
        @Override
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

            assert (u.dimension() == v.dimension());

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

            // The array is needed, cause we need to maintain the order within the map
            // As far as I can tell, there is no ordered maps
            String[] vecNames = {"i", "j", "k"};

            Map<String, Integer> vectors = new HashMap<String, Integer>();


            vectors.put("i", (u.get(1) * v.get(2)) - (u.get(2) * v.get(1)));
            vectors.put("j", ((u.get(0) * v.get(2)) - (u.get(2) * v.get(0))) * -1);
            vectors.put("k", ((u.get(0) * v.get(1)) - u.get(1) * v.get(0)));

            // Add the first element
            result += String.valueOf(vectors.get(vecNames[0]));
            result += "\\textbf{" + vecNames[0] + "}";

            // @review for loop with coditional test based on index?
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

            return result;
        }
    }

    // @review accesibility? should be package as provided for testing only [done]
    CrossProductModule(IntegerGenerator generator) {
        initialise(generator);
    }

    CrossProductModule() {
        initialise(new RandomNumberGenerator());
    }

    private void initialise(IntegerGenerator integers) {
        // @review aim for strong cohesion but low coupling!


        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < dimension; i++) {
            numbers.add(integers.next(-9, 9));
        }

        u = new Vector("u", numbers);

        numbers.clear();
        for (int i = 0; i < dimension; i++) {
            numbers.add(integers.next(-9, 9));
        }

        v = new Vector("v", numbers);
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

    @Override
    public String getSection(final String name) throws IllegalArgumentException {
        return sectionTex.get(Enum.valueOf(Section.class, name.toUpperCase()));
    }

}
