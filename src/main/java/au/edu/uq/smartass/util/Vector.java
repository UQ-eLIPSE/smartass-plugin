package au.edu.uq.smartass.util;

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
    public Vector(final String name, List<Integer> integers) {
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
     * Gets the dimension of the vector
     * @return the dimension of the vector
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * Returns the formatted name of the vector
     */
    public String getFormatName() {
        return formatName;
    }

    /**
     * Returns the formatted definition of the vector
     */
    public String getFormatDefinition() {
       return formatDefinition;
    }

    /**
     * Returns the string representation of the vector
     * in the form '[1, 2, 3]'
     * @return
     */
    public String toString() {
        String output = new String();
        output = "[";
        // vector is of type int[], not List, so we can't use String.join
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
        sb.append(formatName);
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
                .append("\\lVert").append(formatName).append("\\rVert")
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

    /**
     * Returns the normal of the vector
     * @return the normal
     */
    public double normal() { return Math.sqrt(normalSqr); }

    /**
     * Returns the value at the desired index
     * @param index The index of the item
     * @return the item at the index
     */
    public int get(final int index) { return vector[index]; }

    /**
     * Returns the name of the normal
     */
    public String normalName() { return normalName; }

    /**
     * Returns the normal expanded
     */
    public String normalExpand() { return normalExpand; }

    /**
     * Returns the normal group
     */
    public String normalGroup() { return normalGroup; }

    /**
     * Returns the normal sqr
     */
    public int normalSqr() { return normalSqr; }
}
