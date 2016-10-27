package au.edu.uq.smartass.question;

import au.edu.uq.smartass.engine.QuestionModule;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 */
public class GenerateBulkQuesions {

    public static void main(String[] argv) {
        runEigen2x2distinct();
        runEigen2x2repeated();
        runEigen2x2complex();
        runInfiniteGeometricSequenceSum();
    }

    private static void runInfiniteGeometricSequenceSum() {
        List<String> questions = new ArrayList<>();
        List<String> solutions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        questions.add(String.format(HEADER_TEX, "infinite-geometric-sequence-sum.questions"));
        solutions.add(String.format(HEADER_TEX, "infinite-geometric-sequence-sum.solutions"));
        answers.add(String.format(HEADER_TEX, "infinite-geometric-sequence-sum.answers"));

        for (int a = 2; a <= 10; ++a) {
            for (int r = 2; r <= 10; ++r) {
                addNewQuestionItem(
                        new InfiniteGeometricSequenceSumModule( a, r),
                        questions, solutions, answers
                );
                addNewQuestionItem(
                        new InfiniteGeometricSequenceSumModule(-a, r),
                        questions, solutions, answers
                );
                addNewQuestionItem(
                        new InfiniteGeometricSequenceSumModule( a,-r),
                        questions, solutions, answers
                );
                addNewQuestionItem(
                        new InfiniteGeometricSequenceSumModule(-a,-r),
                        questions, solutions, answers
                    );
            }
        }

        questions.add(FOOTER_TEX);
        solutions.add(FOOTER_TEX);
        answers.add(FOOTER_TEX);

        Path file;
        try {
            file = Paths.get("infinite-geometric-sequence-sum.questions.tex");
            Files.write(file, questions, Charset.forName("UTF-8"));

            file = Paths.get("infinite-geometric-sequence-sum.solutions.tex");
            Files.write(file, solutions, Charset.forName("UTF-8"));

            file = Paths.get("infinite-geometric-sequence-sum.answers.tex");
            Files.write(file, answers, Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //
    //      . Eigen Values 2x2 .
    //

    private static void runEigen2x2distinct() {
        List<String> questions = new ArrayList<>();
        List<String> solutions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        questions.add(String.format(HEADER_TEX, "eigen-2x2-distinct.questions"));
        solutions.add(String.format(HEADER_TEX, "eigen-2x2-distinct.solutions"));
        answers.add(String.format(HEADER_TEX, "eigen-2x2-distinct.answers"));

        for (int i = 0; i < Eigen2by2DistinctRealModule.getDataSize(); ++i) {
            addNewQuestionItem(new Eigen2by2DistinctRealModule(i), questions, solutions, answers);
        }

        questions.add(FOOTER_TEX);
        solutions.add(FOOTER_TEX);
        answers.add(FOOTER_TEX);

        Path file;
        try {
            file = Paths.get("eigen-2x2-distinct.questions.tex");
            Files.write(file, questions, Charset.forName("UTF-8"));

            file = Paths.get("eigen-2x2-distinct.solutions.tex");
            Files.write(file, solutions, Charset.forName("UTF-8"));

            file = Paths.get("eigen-2x2-distinct.answers.tex");
            Files.write(file, answers, Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runEigen2x2repeated() {
        List<String> questions = new ArrayList<>();
        List<String> solutions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        questions.add(String.format(HEADER_TEX, "eigen-2x2-repeat.questions"));
        solutions.add(String.format(HEADER_TEX, "eigen-2x2-repeat.solutions"));
        answers.add(String.format(HEADER_TEX, "eigen-2x2-repeat.answers"));

        for (int i = 0; i < Eigen2x2RepeatModule.getDataSize(); ++i) {
            addNewQuestionItem(new Eigen2x2RepeatModule(i), questions, solutions, answers);
        }

        questions.add(FOOTER_TEX);
        solutions.add(FOOTER_TEX);
        answers.add(FOOTER_TEX);

        Path file;
        try {
            file = Paths.get("eigen-2x2-repeat.questions.tex");
            Files.write(file, questions, Charset.forName("UTF-8"));

            file = Paths.get("eigen-2x2-repeat.solutions.tex");
            Files.write(file, solutions, Charset.forName("UTF-8"));

            file = Paths.get("eigen-2x2-repeat.answers.tex");
            Files.write(file, answers, Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runEigen2x2complex() {
        List<String> questions = new ArrayList<>();
        List<String> solutions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        questions.add(String.format(HEADER_TEX, "eigen-2x2-complex.questions"));
        solutions.add(String.format(HEADER_TEX, "eigen-2x2-complex.solutions"));
        answers.add(String.format(HEADER_TEX, "eigen-2x2-complex.answers"));

        for (int i = 0; i < Eigen2x2ComplexModule.getDataSize(); ++i) {
            addNewQuestionItem(new Eigen2x2ComplexModule(i), questions, solutions, answers);
        }

        questions.add(FOOTER_TEX);
        solutions.add(FOOTER_TEX);
        answers.add(FOOTER_TEX);

        Path file;
        try {
            file = Paths.get("eigen-2x2-complex.questions.tex");
            Files.write(file, questions, Charset.forName("UTF-8"));

            file = Paths.get("eigen-2x2-complex.solutions.tex");
            Files.write(file, solutions, Charset.forName("UTF-8"));

            file = Paths.get("eigen-2x2-complex.answers.tex");
            Files.write(file, answers, Charset.forName("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //
    //      . Generic .
    //

    private static void addNewQuestionItem(
            QuestionModule qmod,
            List<String> q_s,
            List<String>s_s,
            List<String>a_s
    ) {
        q_s.add(ITEM_TEX);
        q_s.add(qmod.getSection("question"));
        s_s.add(ITEM_TEX);
        s_s.add(qmod.getSection("solution"));
        a_s.add(ITEM_TEX);
        a_s.add(qmod.getSection("answer"));
    }

    private static String HEADER_TEX =
            "\\documentclass{article}\n" +
            "\\usepackage[utf8]{inputenc}\n" +
            "\\usepackage{amsmath}\n" +
            "\\usepackage{amssymb}\n" +
            "\\usepackage{siunitx}\n" +
            "\\title{%s}\n" +
            "\\begin{document}\n" +
            "\\begin{enumerate}";

    private static String FOOTER_TEX = "\\end{enumerate}\n\\end{document}";

    private static String ITEM_TEX = "\\item";
}
