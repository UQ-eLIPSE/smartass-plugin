package au.edu.uq.smartass.question;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 */
public class Eigen2x2RepeatModule extends AbstractEigen2x2Module {

    public Eigen2x2RepeatModule() {
        this( new Random( new Date().getTime() ).nextInt( DATA.size() ));
    }

    Eigen2x2RepeatModule(int index) {
        super(index);
    }

    @Override
    protected String getData(int i) {
        return DATA.get(i);
    }

    static int getDataSize() { return DATA.size(); }

    private static List<String> DATA = Arrays.asList(
    "A:[6 9];[-4 -6]|Characteristic polynomial:x^2|Eigenvalues & eigenvectors:0 with eigenvector [-3, 2];0 with eigenvector [-3, 2];Not diagonalizable",
    "A:[-6 -4];[0 -6]|Characteristic polynomial:x^2 + 12*x + 36|Eigenvalues & eigenvectors:-6 with eigenvector [1, 0];-6 with eigenvector [1, 0];Not diagonalizable",
    "A:[-1 -1];[4 -5]|Characteristic polynomial:x^2 + 6*x + 9|Eigenvalues & eigenvectors:-3 with eigenvector [1, 2];-3 with eigenvector [1, 2];Not diagonalizable",
    "A:[9 0];[-6 9]|Characteristic polynomial:x^2 - 18*x + 81|Eigenvalues & eigenvectors:9 with eigenvector [0, 1];9 with eigenvector [0, 1];Not diagonalizable",
    "A:[-8 -4];[1 -4]|Characteristic polynomial:x^2 + 12*x + 36|Eigenvalues & eigenvectors:-6 with eigenvector [-2, 1];-6 with eigenvector [-2, 1];Not diagonalizable",
    "A:[3 6];[-6 -9]|Characteristic polynomial:x^2 + 6*x + 9|Eigenvalues & eigenvectors:-3 with eigenvector [-1, 1];-3 with eigenvector [-1, 1];Not diagonalizable",
    "A:[1 1];[0 1]|Characteristic polynomial:x^2 - 2*x + 1|Eigenvalues & eigenvectors:1 with eigenvector [1, 0];1 with eigenvector [1, 0];Not diagonalizable",
    "A:[0 0];[3 0]|Characteristic polynomial:x^2|Eigenvalues & eigenvectors:0 with eigenvector [0, 1];0 with eigenvector [0, 1];Not diagonalizable",
    "A:[4 1];[-4 0]|Characteristic polynomial:x^2 - 4*x + 4|Eigenvalues & eigenvectors:2 with eigenvector [-1, 2];2 with eigenvector [-1, 2];Not diagonalizable",
    "A:[7 -5];[5 -3]|Characteristic polynomial:x^2 - 4*x + 4|Eigenvalues & eigenvectors:2 with eigenvector [1, 1];2 with eigenvector [1, 1];Not diagonalizable",
    "A:[7 -1];[9 1]|Characteristic polynomial:x^2 - 8*x + 16|Eigenvalues & eigenvectors:4 with eigenvector [1, 3];4 with eigenvector [1, 3];Not diagonalizable",
    "A:[2 -8];[2 -6]|Characteristic polynomial:x^2 + 4*x + 4|Eigenvalues & eigenvectors:-2 with eigenvector [2, 1];-2 with eigenvector [2, 1];Not diagonalizable",
    "A:[9 0];[-6 9]|Characteristic polynomial:x^2 - 18*x + 81|Eigenvalues & eigenvectors:9 with eigenvector [0, 1];9 with eigenvector [0, 1];Not diagonalizable",
    "A:[-5 -5];[0 -5]|Characteristic polynomial:x^2 + 10*x + 25|Eigenvalues & eigenvectors:-5 with eigenvector [1, 0];-5 with eigenvector [1, 0];Not diagonalizable",
    "A:[-4 -4];[1 0]|Characteristic polynomial:x^2 + 4*x + 4|Eigenvalues & eigenvectors:-2 with eigenvector [-2, 1];-2 with eigenvector [-2, 1];Not diagonalizable",
    "A:[7 -4];[1 3]|Characteristic polynomial:x^2 - 10*x + 25|Eigenvalues & eigenvectors:5 with eigenvector [2, 1];5 with eigenvector [2, 1];Not diagonalizable",
    "A:[4 -1];[9 -2]|Characteristic polynomial:x^2 - 2*x + 1|Eigenvalues & eigenvectors:1 with eigenvector [1, 3];1 with eigenvector [1, 3];Not diagonalizable",
    "A:[-4 1];[-9 2]|Characteristic polynomial:x^2 + 2*x + 1|Eigenvalues & eigenvectors:-1 with eigenvector [1, 3];-1 with eigenvector [1, 3];Not diagonalizable",
    "A:[6 -2];[2 2]|Characteristic polynomial:x^2 - 8*x + 16|Eigenvalues & eigenvectors:4 with eigenvector [1, 1];4 with eigenvector [1, 1];Not diagonalizable",
    "A:[-3 3];[0 -3]|Characteristic polynomial:x^2 + 6*x + 9|Eigenvalues & eigenvectors:-3 with eigenvector [1, 0];-3 with eigenvector [1, 0];Not diagonalizable",
    "A:[-4 4];[-4 4]|Characteristic polynomial:x^2|Eigenvalues & eigenvectors:0 with eigenvector [1, 1];0 with eigenvector [1, 1];Not diagonalizable",
    "A:[-1 0];[8 -1]|Characteristic polynomial:x^2 + 2*x + 1|Eigenvalues & eigenvectors:-1 with eigenvector [0, 1];-1 with eigenvector [0, 1];Not diagonalizable",
    "A:[9 8];[0 9]|Characteristic polynomial:x^2 - 18*x + 81|Eigenvalues & eigenvectors:9 with eigenvector [1, 0];9 with eigenvector [1, 0];Not diagonalizable",
    "A:[7 -4];[4 -1]|Characteristic polynomial:x^2 - 6*x + 9|Eigenvalues & eigenvectors:3 with eigenvector [1, 1];3 with eigenvector [1, 1];Not diagonalizable",
    "A:[4 -1];[4 8]|Characteristic polynomial:x^2 - 12*x + 36|Eigenvalues & eigenvectors:6 with eigenvector [-1, 2];6 with eigenvector [-1, 2];Not diagonalizable"
    );
}
