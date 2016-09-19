package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * Created by uqamoon1 on 13/09/2016.
 */
public class ArithmeticSequenceSumOfNTermsModule implements QuestionModule {
    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);

    public int numA, numB, numC, diff, term, result;

    /** Constructor used for testing only */
    ArithmeticSequenceSumOfNTermsModule(int numA, int diff, int term) {
        this.numA = numA;
        this.diff = diff;
        this.term = term;
        this.numB = numA + diff;
        this.numC = this.numB + diff;
        this.result = term / 2 * (2 * numA + (term - 1) * diff);

        createQuestionTex(this.numA, this.numB, this.numC, this.term);
        createSolutionTex(this.numA, this.numB, this.diff, this.term, this.result);
    }

    public ArithmeticSequenceSumOfNTermsModule() {
        this(
                new QUtil().generatePosInt(2,2),
                new QUtil().generateNegToPosInt(-10, 10),
                new QUtil().generatePosInt(5, 50)
        );
    }

    private void createQuestionTex(int numA, int numB, int numC, int term) {
        String sb = format("Let $%1$d,%2$d,%3$d$ be an arithmetic sequence. Determine the sum of the first $%4$d$ terms in the sequence.\\\\", numA, numB, numC, term);
        sectionTeX.put(Section.QUESTION, sb.toString());
        try {
            writeTexFile("question_output.tex", sb.toString());
        } catch (Exception ex) {

        }
    }

    private void createSolutionTex(int numA, int numB, int diff, int term, int result) {
        String sb = format("$S_n=\\dfrac{n}{2}(2a+(n-1)d)$, where $d=%2$d-%1$d=%3$d$ and $a=%1$d$.\\\\", numA, numB, diff );
                sb += format("Therefore $S_{%2$d}=\\dfrac{%2$d}{2}(2\\cdot %1$d +(%2$d-1)\\cdot %3$d)$\\\\", numA, term, diff );
                sb += format("$=%2$d(%1$d+%3$d \\cdot%4$d)$\\\\", 2 * numA, term / 2, term - 1, diff);
                sb += format("$=%d$", result);
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
