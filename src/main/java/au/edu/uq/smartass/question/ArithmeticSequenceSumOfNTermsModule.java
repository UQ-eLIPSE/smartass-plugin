package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by uqamoon1 on 13/09/2016.
 */
public class ArithmeticSequenceSumOfNTermsModule implements QuestionModule {
    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);
    private Random random = new Random();

    public int numA, numB, numC, diff, term, result;

    /** Constructor used for testing only */
    public ArithmeticSequenceSumOfNTermsModule(int numA, int numB, int term) {
        this.numA = numA;
        this.numB = numB;
        this.term = term;
        this.diff = this.numB - this.numA;
        this.numC = setNumC(this.numB, this.diff);
        this.result = setSolution(this.term, this.numA, this.diff);
        createQuestionTex(this.numA, this.numB, this.numC, this.term);
        createSolutionTex(this.numA, this.numB, this.diff, this.term, this.result);
    }

    public ArithmeticSequenceSumOfNTermsModule() {
        this.numA = setNumA(2, 5);
        this.diff = setDiff();
        this.numB = setNumB(this.numA, this.diff);
        this.numC = setNumC(this.numB, this.diff);
        this.term = setTerm(this.numC, 50);
        this.result = setSolution(this.term, this.numA, this.diff);
        createQuestionTex(this.numA, this.numB, this.numC, this.term);
        createSolutionTex(this.numA, this.numB, this.diff, this.term, this.result);
    }

    private int setNumA(int min, int max) {
        return random.nextInt(max + 1 - min) + min;
    }

    private int setNumB(int numA, int diff) {
        return numA + diff;
    }

    private int setNumC(int numB, int diff) {
        return numB + diff;
    }

    private int setDiff() {
        return random.nextInt(10 + 1 + 10) - 10;
    }

    private int setTerm(int min, int max) {
        return random.nextInt(max +1 - min) + min;
    }

    private int setSolution(int term, int numA, int diff) {
        return this.term / 2 * (2 * this.numA + (this.term - 1) * this.diff);
    }

    private void createQuestionTex(int numA, int numB, int numC, int term) {
        StringBuilder sb = new StringBuilder();
        sb.append("Let $" + numA + "," + numB + "," + numC + "$ be an arithmetic sequence. Determine the sum of the first $" + term + "$ terms in the sequence.\\\\");
        sectionTeX.put(Section.QUESTION, sb.toString());
        try {
            writeTexFile("question_output.tex", sb.toString());
        } catch (Exception ex) {

        }
    }

    private void createSolutionTex(int numA, int numB, int diff, int term, int result) {
        StringBuilder sb = new StringBuilder();
        sb.append("$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=" + numB + "-" + numA + "=" + diff + "$ and $a=" + numA + "$.\\\\" )
                .append("Therefore $S_{" + term + "}=\\dfrac{" + term + "}{2}(2\\cdot " + numA + " +(" + term + "-1)\\cdot " + diff + ")$\\\\" )
                .append("$=" + (term / 2) + "(" + (2 * numA) + "+" + (term - 1) + " \\cdot" + diff + ")$\\\\")
                .append("$=" + result + "$");
        sectionTeX.put(Section.SOLUTION, sb.toString());
        try {
            writeTexFile("solution_output.tex", sb.toString());
        } catch (Exception ex) {

        }
    }

    private void writeTexFile(String filename, String str) throws Exception {
        Files.write(Paths.get("./" + filename), str.getBytes());
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
