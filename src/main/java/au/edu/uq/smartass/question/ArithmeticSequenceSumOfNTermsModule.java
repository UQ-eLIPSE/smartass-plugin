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
        this.setNumC();
        this.setSolution();
        this.createQuestionTex();
        this.createSolutionTex();
    }

    public ArithmeticSequenceSumOfNTermsModule() {
        this.setNumA(2, 5);
        this.setDiff();
        this.setNumB();
        this.setNumC();
        this.setTerm(this.numC, 50);
        this.setSolution();
        this.createQuestionTex();
        this.createSolutionTex();
    }

    private void setNumA(int min, int max) {
        this.numA = random.nextInt(max + 1 - min) + min;
    }

    private void setNumB() {
        this.numB = this.numA + this.diff;
    }

    private void setNumC() {
        this.numC = this.numB + this.diff;
    }

    private void setDiff() {
        this.diff = random.nextInt(10 + 1 + 10) - 10;
    }

    private void setTerm(int min, int max) {
        this.term = random.nextInt(max +1 - min) + min;
    }

    private void setSolution() {
        this.result = this.term / 2 * (2 * this.numA + (this.term - 1) * this.diff);
    }

    private void createQuestionTex() {
        StringBuilder sb = new StringBuilder();
        sb.append("Let $" + this.numA + "," + this.numB + "," + this.numC + "$ be an arithmetic sequence. Determine the sum of the first $" + this.term + "$ terms in the sequence.\\\\");
        sectionTeX.put(Section.QUESTION, sb.toString());
        try {
            writeTexFile("question_output.tex", sb.toString());
        } catch (Exception ex) {

        }
    }

    private void createSolutionTex() {
        StringBuilder sb = new StringBuilder();
        sb.append("$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=" + this.numB + "-" + this.numA + "=" + this.diff + "$ and $a=" + this.numA + "$.\\\\" )
                .append("Therefore $S_{" + this.term + "}=\\dfrac{" + this.term + "}{2}(2\\cdot " + this.numA + " +(" + this.term + "-1)\\cdot " + this.diff + ")$\\\\" )
                .append("$=" + (this.term / 2) + "(" + (2 * this.numA) + "+" + (this.term - 1) + " \\cdot" + this.diff + ")$\\\\")
                .append("$=" + this.result + "$");
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
