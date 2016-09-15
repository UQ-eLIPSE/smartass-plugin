package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by uqamoon1 on 15/09/2016.
 */
public class GeometricSequenceTermsToNModule implements QuestionModule {

    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<ArithmeticSequenceSumOfNTermsModule.Section,String> sectionTeX = new EnumMap<>(ArithmeticSequenceSumOfNTermsModule.Section.class);
    private Random random = new Random();

    public int numA, numB, numC, term, ratio, result;

    public GeometricSequenceTermsToNModule( int numA, int numB, int term) {
        this.numA = numA;
        this.numB = numB;
        this.term = term;
        this.ratio = this.numB / this.numA;
        this.numC = this.numB * this.ratio;
        this.result = setSolution();
    }

    public GeometricSequenceTermsToNModule() {
        this.numA = setNumA(2, 2);
        this.ratio = setRatio();
        this.numB = setNumB();
        this.numC = setNumC();
        this.term = setTerm(this.numC, 50);
        this.result = setSolution();

    }

    private int setNumA(int min, int max) {
        return random.nextInt(max + 1 - min) + min;
    }

    private int setNumB() {
        return this.numA + this.ratio;
    }

    private int setNumC() {
        return this.numB + this.ratio;
    }

    private int setRatio() {
        return random.nextInt(5 + 1 + 5) - 5;
    }

    private int setTerm(int min, int max) {
        return random.nextInt(max +1 - min) + min;
    }

    private int setSolution() {
        // (a, b, n) => a * Math.pow(b / a, n - 1);
       return (int)(this.numA * Math.pow(this.numB / this.numA, this.term - 1));
    }

    public String getOrdinal(int t) {
        String ord = String.valueOf(t);
        if (ord.endsWith("1") && !ord.endsWith("11")) {
            return "st";
        }
        if (ord.endsWith("2") && !ord.endsWith("12")) {
            return "nd";
        }
        if (ord.endsWith("3") && !ord.endsWith("13")) {
            return "rd";
        }
        return "th";
    }

    /**
     * Accessor for LaTeX associated with a section name.
     *
     * @param name The section name for which the LaTeX is required.
     * @return The LaTeX associated with the given section name, or NULL.
     * @throws IllegalArgumentException if the given name does not translate to a valid section.
     */
    @Override public String getSection(final String name) throws IllegalArgumentException {
        return sectionTeX.get(Enum.valueOf(ArithmeticSequenceSumOfNTermsModule.Section.class, name.toUpperCase()));
    }

}
