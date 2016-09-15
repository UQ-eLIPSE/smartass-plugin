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

    /** Constructor used for testing only */
    public ArithmeticSequenceNthTermModule(int numA, int numB, int term) {
        this.numA = numA;
        this.numB = numB;
        this.term = term;
        this.diff = this.numB - this.numA;
        this.numC = setNumC(this.numB, this.diff);
        this.result = setSolution(this.numA, this.term, this.diff);

        createQuestionTeX(this.term, this.numA, this.numB, this.numC);
        createSolutionTeX(this.numA, this.numB, this.diff, this.term, this.result);
    }

    public ArithmeticSequenceNthTermModule() {
        this.numA =setNumA(2, 2);
        this.diff = setDiff();
        this.numB = setNumB(this.numA, this.diff);
        this.numC = setNumC(this.numB, this.diff);
        this.term = setTerm(this.numC, 50);
        this.result = setSolution(this.numA, this.term, this.diff);

        createQuestionTeX(this.term, this.numA, this.numB, this.numC);
        createSolutionTeX(this.numA, this.numB, this.diff, this.term, this.result);
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

    private int setSolution(int numA, int term, int diff) {
        return numA + (term -1) * diff;
    }

    private void createQuestionTeX(int term, int numA, int numB, int numC) {
        String ord = getOrdinal(term);
        StringBuilder sb = new StringBuilder();
        sb.append("Let ");
        sb.append("$" + numA + "," + numB + "," + numC + "$");
        sb.append(" be an arithmetic sequence. Determine the ");
        sb.append("$" + term + "$");
        sb.append(ord + " term in the sequence.\\\\");
        sectionTeX.put(Section.QUESTION, sb.toString());
        try {
            writeTexFile("question_output.tex", sb.toString());
        } catch (Exception ex) {

        }
    }

    private void createSolutionTeX(int numA, int numB, int diff, int term, int result) {
        StringBuilder sb = new StringBuilder();
        sb.append("$a_n=a+(n-1)d$, where $d=");
        sb.append(numB + "-" + numA + "=" + diff + "$ and $a=" + numA + "$.\\\\");
        sb.append("Therefore $a_{" + term + "}=" + numA);
        sb.append("+(" + term + "-1)\\cdot " + diff + "$\\\\");
        sb.append("$=" + numA + "+" + (term - 1) + " \\cdot" + diff + "$\\\\");
        sb.append("$=" + result + "$");
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

