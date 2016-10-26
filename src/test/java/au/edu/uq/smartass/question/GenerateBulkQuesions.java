package au.edu.uq.smartass.question;

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
        //runEigen2x2distinct();
        //runEigen2x2repeated();
        //runEigen2x2complex();
        runInfiniteGeometricSequenceSum();
    }

    private static void runInfiniteGeometricSequenceSum() {
        List<String> questions = new ArrayList<>();
        List<String> solutions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        questions.add(String.format(HEADER_TEX, "eigen-2x2-distinct.questions"));
        solutions.add(String.format(HEADER_TEX, "eigen-2x2-distinct.solutions"));
        answers.add(String.format(HEADER_TEX, "eigen-2x2-distinct.answers"));

        for (int i = 2; i <= 10; ++i) {
            InfiniteGeometricSequenceSumModule seq;

            seq = new InfiniteGeometricSequenceSumModule(i);
            questions.add(ITEM_TEX);
            questions.add(seq.getSection("question"));
            solutions.add(ITEM_TEX);
            solutions.add(seq.getSection("solution"));
            answers.add(ITEM_TEX);
            answers.add(seq.getSection("answer"));

            seq = new InfiniteGeometricSequenceSumModule(-i);
            questions.add(ITEM_TEX);
            questions.add(seq.getSection("question"));
            solutions.add(ITEM_TEX);
            solutions.add(seq.getSection("solution"));
            answers.add(ITEM_TEX);
            answers.add(seq.getSection("answer"));
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

    private static void runEigen2x2distinct() {
        List<String> questions = new ArrayList<>();
        List<String> solutions = new ArrayList<>();
        List<String> answers = new ArrayList<>();

        questions.add(String.format(HEADER_TEX, "eigen-2x2-distinct.questions"));
        solutions.add(String.format(HEADER_TEX, "eigen-2x2-distinct.solutions"));
        answers.add(String.format(HEADER_TEX, "eigen-2x2-distinct.answers"));

        for (int i = 0; i < Eigen2by2DistinctRealModule.getDataSize(); ++i) {
            Eigen2by2DistinctRealModule ed = new Eigen2by2DistinctRealModule(i);
            questions.add(ITEM_TEX);
            questions.add(ed.getSection("question"));
            solutions.add(ITEM_TEX);
            solutions.add(ed.getSection("solution"));
            answers.add(ITEM_TEX);
            answers.add(ed.getSection("answer"));
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
            Eigen2x2RepeatModule er = new Eigen2x2RepeatModule(i);
            questions.add(ITEM_TEX);
            questions.add(er.getSection("question"));
            solutions.add(ITEM_TEX);
            solutions.add(er.getSection("solution"));
            answers.add(ITEM_TEX);
            answers.add(er.getSection("answer"));
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
            Eigen2x2ComplexModule ec = new Eigen2x2ComplexModule(i);
            questions.add(ITEM_TEX);
            questions.add(ec.getSection("question"));
            solutions.add(ITEM_TEX);
            solutions.add(ec.getSection("solution"));
            answers.add(ITEM_TEX);
            answers.add(ec.getSection("answer"));
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
