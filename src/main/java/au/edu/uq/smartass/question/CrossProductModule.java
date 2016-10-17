package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.SimpleQuestionModule;

// @review Do not import whole package.
import java.util.*;

/**
 * Cross Product Module
 * Generates random cross product questions
 */
public class CrossProductModule extends SimpleQuestionModule {

    // Store the math objects
    static private final int dimension = 3;
    private Vector u;
    private Vector v;
    private CrossProduct crossProduct;

    // Create an instance of the random number generator
    private IntegerGenerator integers;

    /**
     * A class that implements the IntegerGenerator
     * Used for generating random numbers
     */
    class RandomNumberGenerator implements IntegerGenerator{
        Random random;

        public RandomNumberGenerator() {
            random = new Random();
        }

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
        private String formatResult;

        CrossProduct(final Vector u, final Vector v) {
            this.u = u;
            this.v = v;

            assert (u.dimension() == v.dimension());

            formatName = u.formatName() + "\\times" + v.formatName();
            formatResult = generateResult();
        }

        String getFormatedResult() { return formatResult; }

        /**
         * Generates the numerical result of the cross product
         * The result is in the form '3i - 2j + 4k'
         */
        private String generateResult() {
            String result = new String();

            String[] vecNames = {"i", "j", "k"};

            Map<String, Integer> vectors = new HashMap<String, Integer>();


            vectors.put("i", (u.get(1) * v.get(2)) - (u.get(2) * v.get(1)));
            vectors.put("j", ((u.get(0) * v.get(2)) - (u.get(2) * v.get(0))) * -1);
            vectors.put("k", ((u.get(0) * v.get(1)) - u.get(1) * v.get(0)));

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

            return result;
        }
    }

    CrossProductModule(IntegerGenerator generator) {
        initialise(generator);
    }

    /**
     * A public default constructor is required by the ServiceLoader.
     */
    public CrossProductModule() {
        initialise(new RandomNumberGenerator());
    }

    private void initialise(IntegerGenerator integers) {

        // Create a list of numbers from the random generator then pass it to the Vector

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
        String tex =
                String.format(
                        "Let $\\mathbf{u}=\\left(\\begin{array}{c}%d\\\\%d\\\\%d\\end{array}\\right)$ \n",
                        u.get(0), u.get(1), u.get(2)
                    ) +
                String.format(
                        "and $\\mathbf{v}=\\left(\\begin{array}{c}%d\\\\%d\\\\%d\\end{array}\\right)$. \n",
                        v.get(0), v.get(1), v.get(2)
                    ) +
                "Determine $\\bf{u}\\times\\bf{v}$.";
        setQuestion(tex);
    }

    private void createSolutionTex() {
        String tex =
                "\\begin{align*}\n" +
                "\\textbf{u}\\times\\textbf{v}&=\\left|\\begin{array}{crc}\n" +
                String.format(
                        "\\textbf{i}&\\textbf{j}&\\textbf{k}\\\\%d&%d&%d\\\\%d&%d&%d\n",
                        u.get(0), u.get(1), u.get(2), v.get(0), v.get(1), v.get(2)
                    ) +
                "\\end{array}\\right|\\\\\n" +
                String.format(
                        "&=\\textbf{i}\\left|\\begin{array}{rc}2&7\\\\5&-9\\end{array}\\right|\n",
                        u.get(1), u.get(2), v.get(1), v.get(2)
                    ) +
                String.format(
                        "-\\textbf{j}\\left|\\begin{array}{cc}5&7\\\\-2&-9\\end{array}\\right|\n",
                        u.get(0), u.get(2), v.get(0), v.get(2)
                    ) +
                String.format(
                        "+\\textbf{k}\\left|\\begin{array}{cr}5&2\\\\-2&5\\end{array}\\right|\\\\\n",
                        u.get(0), u.get(1), v.get(0), v.get(1)
                    ) +
                String.format(
                        "&=%s\n", crossProduct.getFormatedResult()) +
                "\\end{align*}";
        setSolution(tex);
    }

    private void createAnswerTex() {
        setAnswer(String.format("$%s$", crossProduct.getFormatedResult()));
    }

}
