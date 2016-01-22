package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

/**
 *
 */
public class ScalarProductModule implements QuestionModule {

    public enum Section { QUESTION, SOLUTION, ANSWER }

    class Vector {
        private int[] vector;
        Vector(final int size) {
            vector = new int[size];
        }
    }

    class DotProduct {
        DotProduct(final Vector u, final Vector v){}
    }

    int dim;
    Vector u;
    Vector v;
    DotProduct uv;

    private String question = "";
    private String solution = "";
    private String answer = "";

    private java.util.Random random = new java.util.Random();

    ScalarProductModule(){}

    private void initialise() {
        dim = random.nextInt(2) + 2;    // range(2,3)
        u = new Vector(dim);
        v = new Vector(dim);
        uv = new DotProduct(u, v);
    }

    public String getQuestion() { return question; }
    public String getSolution() { return solution; }
    public String getAnswer() { return answer; }

    @Override public String getSection(final String name) throws IllegalArgumentException {
        Section section = Enum.valueOf(Section.class, name);
        switch (section) {
            case QUESTION: return getQuestion();
            case SOLUTION: return getSolution();
            case ANSWER: return getAnswer();
            default:
                throw new UnsupportedOperationException(String.format("No DATA defined for section '%s'!", name));
        }
    }

}
