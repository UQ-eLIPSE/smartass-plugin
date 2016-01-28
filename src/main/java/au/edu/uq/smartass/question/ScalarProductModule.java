package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Vector Dot Product Module.
 * Generates randomised questions to test knowledge of the vector dot product. The questions
 * are generated as LaTeX sections for insertion into a larger document. Questions, Short
 * Answers and Worked Solutions are produced in each case.
 *
 * Required LaTeX packages (should be inculed in 'global' smartass.tex):
 *      \\usepackage{amsmath}
 *      \\usepackage{enumerate}
 *      \\usepackage{siunitx}
 */
public class ScalarProductModule implements QuestionModule {

    /** Define supported TeX Sections. */
    public enum Section { QUESTION, SOLUTION, ANSWER }

    /** Lookup TeX string. */
    private Map<Section,String> sectionTeX = new EnumMap<>(Section.class);

    /** */
    interface IntegerGenerator { int next(int lower, int uppper); }

    /**
     * Utility class representing 'mathematical' vector.
     */
    class Vector {
        private String name;
        private int dimension;

        private int[] vector;

        private String formatName;
        private String formatDefinition;

        private String normalName;
        private String normalExpand;
        private String normalGroup;
        private int normalSqr;

        Vector(final String name, final int dimension) {
            this.name = name;
            this.dimension = dimension;

            initialiseVector();
            initializeName();
            initializeNormal();
        }

        /** Init vector with random numbers (-9 <= x <= 9). */
        private void initialiseVector() {
            vector = new int[dimension];
            for (int i = 0; i < dimension; ++i) vector[i] = integers.next(-9, 9);
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

    class DotProduct {
        private Vector u;
        private Vector v;

        private String formatName;
        private String formatExpanded;
        private String formatGrouped;
        private String formatResult;
        private String formatAngle;

        DotProduct(final Vector u, final Vector v){
            this.u = u;
            this.v = v;

            assert(u.dimension() == v.dimension());

            formatName = new StringBuilder()
                    .append(u.formatName()).append("\\cdot").append(v.formatName())
                    .toString()
                ;

            List<String> expandTokens = new ArrayList<>();
            List<String> groupdTokens = new ArrayList<>();
            int result = 0;

            for (int i = 0; i < u.dimension(); ++i) {
                int ui = u.get(i);
                int vi = v.get(i);

                expandTokens.add(String.format("%d.%d", ui, vi));
                groupdTokens.add(String.format("%d", ui * vi));
                result += ui * vi;
            }

            formatExpanded = String.join("+", expandTokens);
            formatGrouped = String.join("+", groupdTokens);
            formatResult = String.format("%d", result);
            formatAngle = String.format(
                    "\\approx\\ang{%d}",
                    Math.round(Math.toDegrees(Math.acos(result / (u.normal() * v.normal()))))
                );
        }

        String formatName() { return formatName; }
        String formatExpanded() { return formatExpanded; }
        String formatGrouped() { return formatGrouped; }
        String formatResult() { return formatResult; }
        String formatAngle() { return formatAngle; }
    }

    private IntegerGenerator integers;

    int dim;
    Vector u;
    Vector v;
    DotProduct uv;


    /**
     *
     */
    ScalarProductModule() {
        this(new IntegerGenerator() {
                private java.util.Random random = new java.util.Random();
                @Override public int next(final int lower, final int upper) {
                    return random.nextInt(upper + 1 - lower) + lower;
                }
            });
    }

    ScalarProductModule(final IntegerGenerator ints) {
        this.integers = ints;

        initialise();
        createQuestionTeX();
        createSolutionTeX();
        createAnswerTeX();
    }

    private void initialise() {
        dim = integers.next(2, 3);
        u = new Vector("u", dim);
        v = new Vector("v", dim);
        uv = new DotProduct(u, v);
    }

    private void createQuestionTeX() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("\\[\\text{Let }").append(u.formatDefinition())
                .append("\\text{and }").append(v.formatDefinition())
                .append("\\text{.}\\]")
            ;
        sb.append("\\begin{enumerate}[(a)]");
        sb.append("\\item Determine $").append(uv.formatName()).append("$.");
        sb
                .append("\\item Determine the angle in degrees (to the nearest degree) between $")
                .append(u.formatName())
                .append("$ and $")
                .append(v.formatName())
                .append("$.")
            ;
        sb.append("\\end{enumerate}");
        sectionTeX.put(Section.QUESTION, sb.toString());
    }

    private void createSolutionTeX() {
        StringBuilder sb = new StringBuilder();
        sb.append("\\begin{enumerate}[(a)]");
        sb.append("\\item \\begin{align*}");
        sb.append(uv.formatName()).append("&=").append(uv.formatExpanded()).append("\\\\");
        sb.append("&=").append(uv.formatGrouped()).append("\\\\");
        sb.append("&=").append(uv.formatResult());
        sb.append("\\end{align*}");
        sb.append("\\item \\begin{align*}");
        sb
                .append("\\text{From (a) }")
                .append(uv.formatName())
                .append("&=")
                .append(uv.formatResult())
                .append("\\\\")
            ;
        sb.append("\\\\");
        sb.append(u.normalName()).append("&=").append(u.normalExpand()).append("\\\\");
        sb.append("&=").append(u.normalGroup()).append("\\\\");
        sb.append("\\\\");
        sb.append(v.normalName()).append("&=").append(v.normalExpand()).append("\\\\");
        sb.append("&=").append(v.normalGroup()).append("\\\\");
        sb.append("\\\\");
        sb
                .append(uv.formatName())
                .append("&=")
                .append(u.normalName())
                .append(v.normalName())
                .append("\\cos\\theta\\\\")
            ;
        sb.append("\\\\");
        sb
                .append("\\text{So }").append(uv.formatResult())
                .append("&=")
                .append(u.normalGroup())
                .append(v.normalGroup())
                .append("\\cos\\theta\\\\")
            ;
        sb.append("\\\\");
        sb
                .append("\\text{Therefore }\\theta")
                .append("&=\\arccos\\dfrac{")
                .append(uv.formatResult())
                .append("}{")
                .append(u.normalGroup())
                .append(v.normalGroup())
                .append("}")
                .append("\\\\")
            ;
        sb
                .append("\\text{So }\\theta&")
                .append(uv.formatAngle())
            ;
        sb.append("\\end{align*}");
        sb.append("\\end{enumerate}");
        sectionTeX.put(Section.SOLUTION, sb.toString());
    }

    private void createAnswerTeX() {
        StringBuilder sb = new StringBuilder();
        sb.append("\\begin{enumerate}[(a)]");
        sb
                .append("\\item $")
                .append(uv.formatName())
                .append("=")
                .append(uv.formatResult())
                .append("$")
            ;
        sb.append("\\item $\\theta").append(uv.formatAngle()).append("$");
        sb.append("\\end{enumerate}");
        sectionTeX.put(Section.ANSWER, sb.toString());
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
