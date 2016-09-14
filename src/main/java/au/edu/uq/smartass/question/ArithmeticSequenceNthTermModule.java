package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by uqamoon1 on 12/09/2016.
 * Find the nth term in an Arithmetic Sequence.
 */

public class ArithmeticSequenceNthTermModule implements QuestionModule {
    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);
    private Random random = new Random();

    public int numA, numB, numC, diff, term, result;

    // Constructor used for testing only
    public ArithmeticSequenceNthTermModule(int numA, int numB, int term) {
        this.numA = numA;
        this.numB = numB;
        this.term = term;
        this.diff = this.numB - this.numA;
        setNumC();
        setSolution();
        createQuestionTeX();
        createSolutionTeX();
    }

    public ArithmeticSequenceNthTermModule() {
        setNumA(2, 2);
        setDiff();
        setNumB();
        setNumC();
        setTerm(this.numB, 50);
        setSolution();
        createQuestionTeX();
        createSolutionTeX();
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
        this.result = this.numA + (this.term -1) * this.diff;
    }

    private void createQuestionTeX() {
        String ord = getOrdinal(this.term);
        StringBuilder sb = new StringBuilder();
        sb.append("Let ");
        sb.append("$" + this.numA + "," + this.numB + "," + this.numC + "$");
        sb.append(" be an arithmetic sequence. Determine the ");
        sb.append("$" + this.term + "$");
        sb.append(ord + " term in the sequence.\\\\");
        sectionTeX.put(Section.QUESTION, sb.toString());
        try {
            writeTexFile("question_output.tex", sb.toString());
        } catch (Exception ex) {

        }
    }

    private void createSolutionTeX() {
        StringBuilder sb = new StringBuilder();
        sb.append("$a_n=a+(n-1)d$, where $d=");
        sb.append(this.numB + "-" + this.numA + "=" + this.diff + "$ and $a=" + this.numA + "$.\\\\");
        sb.append("Therefore $a_{" + this.term + "}=" + this.numA);
        sb.append("+(" + this.term + "-1)\\cdot " + this.diff + "$\\\\");
        sb.append("$=" + this.numA + "+" + (this.term - 1) + " \\cdot" + this.diff + "$\\\\");
        sb.append("$=" + this.result + "$");
        sectionTeX.put(Section.SOLUTION, sb.toString());
        try {
            writeTexFile("solution_output.tex", sb.toString());
        } catch (Exception ex) {

        }
    }

    private void createAnswerTeX() {
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
        return sectionTeX.get(Enum.valueOf(Section.class, name.toUpperCase()));
    }
}

