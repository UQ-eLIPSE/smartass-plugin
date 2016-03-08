package au.edu.uq.smartass.question;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 * Represents a mathematical vector
 */
public class Vector {
    private String name;
    private int dimension;

    private int[] vector;

    private String formatName;
    private String formatDefinition;

    private String normalName;
    private String normalExpand;
    private String normalGroup;
    private int normalSqr;

    /**
     * Contructs the Vector class
     * @param name The name of the vector
     * @param dimension The dimension of the vector
     * @param integers The random number generator to use
     */
    Vector(final String name, List<Integer> integers) {
        this.name = name;
        this.dimension = integers.size();

        LinkedList<Integer> numbers = new LinkedList<Integer>();
        // This is the cleanest way to convert a list into a linked list
        for (int i : integers) {
            numbers.add(i);
        }

        initialiseVector(numbers);
        initializeName();
        initializeNormal();
    }

    /**
     * Returns the string representation of the vector
     * in the form '[1, 2, 3]'
     * @return
     */
    public String toString() {
        String output = new String();
        output = "[";
        // @review Java8 has String.join(conjunction, list)
        for (int i = 0; i < dimension; i++) {
            output += vector[i];
            if (i != dimension - 1) {
                output += ", ";
            }
        }
        output += "]";

        return output;
    }

    /** Init vector with random numbers (-9 <= x <= 9). */
    private void initialiseVector(LinkedList<Integer> numbers) {
        vector = new int[dimension];
        for (int i = 0; i < dimension; ++i) vector[i] = numbers.pop();
    }

    /** */
    private void initializeName() {
        // Init TeX formatted vector name.
        formatName = new StringBuilder()
                .append("\\mathbf{").append(name).append("}")
                .toString()
        ;

        // Init Tex formatted vector definition (ie u = (x))
        StringBuilder sb = new StringBuilder();
        sb.append(formatName());
        sb.append("=");
        sb.append("\\begin{pmatrix}");
        for (int x : vector) {
            sb.append(x).append("\\\\");
        }
        sb.append("\\end{pmatrix}");
        formatDefinition = sb.toString();
    }

    /** */
    private void initializeNormal() {
        normalName = new StringBuilder()
                .append("\\lVert").append(formatName()).append("\\rVert")
                .toString()
        ;

        List<String> elsqr = new ArrayList<>();
        for (int element : vector) {
            elsqr.add(String.format(element < 0 ? "(%d)^2" : "%d^2", element));
            normalSqr += element * element;
        }
        normalExpand = String.format("\\sqrt{%s}", String.join("+", elsqr));
        normalGroup = String.format("\\sqrt{%d}", normalSqr);
    }

    int dimension() { return dimension; }
    double normal() { return Math.sqrt(normalSqr); }

    String formatName() { return formatName; }
    String formatDefinition() { return formatDefinition; }

    int get(final int index) { return vector[index]; }

    String normalName() { return normalName; }
    String normalExpand() { return normalExpand; }
    String normalGroup() { return normalGroup; }

    int normalSqr() { return normalSqr; }
}
