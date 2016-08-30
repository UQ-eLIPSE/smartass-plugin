package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Geometric Sequence Module.
 *
 * Required LaTeX packages (should be inculed in 'global' smartass.tex):
 *      \\usepackage{amsmath}
 *      \\usepackage{enumerate}
 *      \\usepackage{siunitx}
 */
public class GeomSeqSumModule implements QuestionModule {

    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);

    private int n;
    private int a;
    private int r;

    private int[] seq;

    private void createQuestion(int a, int r, int n) {
        this.a = a;
        this.r = r;
        this.n = n;

        seq = new int[3];
        seq[0] = a;
        seq[1] = a * r;
        seq[2] = a * r * r;

        createQuestionTeX();
        createSolutionTeX();
        createAnswerTeX();
    }

    public GeomSeqSumModule(int a, int r, int n) {
        createQuestion(a, r, n);
    }

    /**
     *
     */
    public GeomSeqSumModule() {
        Random rand = new Random();
        int a = rand.nextInt(4) + 1;
        int r = rand.nextInt(10) - 5;
        int n = rand.nextInt(10) + 10;

        createQuestion(a, r, n);
    }

    private void createQuestionTeX() {
        String sb = "";
        sb += "Let $" + this.seq[0] + ", " + this.seq[1] + ", " + this.seq[2] + "$ be a geometric sequence. What is the sume of the first " + this.n + " terms?";

        sectionTeX.put(Section.QUESTION, sb);
    }

    private void createSolutionTeX() {
        String sb = "";
        
        sb += "$S_n=\\dfrac{a(r^n-1)}{r-1}$, where $r=" + this.seq[1] + 
            "\\div" + this.seq[0] + "=" + this.r + "$ and $a=" + this.a + "$.\\\\" +
            "$S_{" + this.n + "}=\\dfrac{" + this.a + "(" + this.r + "^{" + this.n + "-1})}{" + this.n + "-1}$\\\\" +
            "$=\\dfrac{" + this.a + "\\cdot" + this.r + "^{" + (this.n-1) + "}}{" + (this.n-1) + "}$\\\\" +
            "$\\approx " + (this.a * Math.pow(this.r, this.n-1) / this.n-1) + "$";


        sectionTeX.put(Section.SOLUTION, sb);
    }

    private void createAnswerTeX() {
        String sb = "";

        sb += "$\\approx " + (this.a * Math.pow(this.r, this.n-1) / this.n-1) + "$";

        sectionTeX.put(Section.ANSWER, sb);
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
