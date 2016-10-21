package au.edu.uq.smartass.question;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 */
public class Eigen2x2ComplexModule extends AbstractEigen2x2Module {

    public Eigen2x2ComplexModule() {
        this( new Random( new Date().getTime() ).nextInt( DATA.size() ));
    }

    Eigen2x2ComplexModule(int index) {
        super(index);
    }

    @Override
    protected String getData(int i) {
        return DATA.get(i);
    }

    static int getDataSize() { return DATA.size(); }


    static final List<String> DATA = Arrays.asList(
    "A:[3 4];[-8 -5]|Characteristic polynomial:x^2 + 2*x + 17|Eigenvalues & eigenvectors:-1 + 4*I with eigenvector [-1 - I, 2];-1 - 4*I with eigenvector [-1 + I, 2]",
    "A:[-3 8];[-4 5]|Characteristic polynomial:x^2 - 2*x + 17|Eigenvalues & eigenvectors:1 + 4*I with eigenvector [1 - I, 1];1 - 4*I with eigenvector [1 + I, 1]",
    "A:[-4 -4];[9 -4]|Characteristic polynomial:x^2 + 8*x + 52|Eigenvalues & eigenvectors:-4 + 6*I with eigenvector [2*I, 3];-4 - 6*I with eigenvector [-2*I, 3]",
    "A:[1 5];[-5 1]|Characteristic polynomial:x^2 - 2*x + 26|Eigenvalues & eigenvectors:1 + 5*I with eigenvector [-I, 1];1 - 5*I with eigenvector [I, 1]",
    "A:[7 -6];[3 1]|Characteristic polynomial:x^2 - 8*x + 25|Eigenvalues & eigenvectors:4 + 3*I with eigenvector [1 + I, 1];4 - 3*I with eigenvector [1 - I, 1]",
    "A:[-6 -5];[4 -2]|Characteristic polynomial:x^2 + 8*x + 32|Eigenvalues & eigenvectors:-4 + 4*I with eigenvector [-1 + 2*I, 2];-4 - 4*I with eigenvector [-1 - 2*I, 2]",
    "A:[3 -5];[5 -5]|Characteristic polynomial:x^2 + 2*x + 10|Eigenvalues & eigenvectors:-1 + 3*I with eigenvector [4 + 3*I, 5];-1 - 3*I with eigenvector [4 - 3*I, 5]",
    "A:[0 6];[-3 6]|Characteristic polynomial:x^2 - 6*x + 18|Eigenvalues & eigenvectors:3 + 3*I with eigenvector [1 - I, 1];3 - 3*I with eigenvector [1 + I, 1]",
    "A:[9 8];[-4 1]|Characteristic polynomial:x^2 - 10*x + 41|Eigenvalues & eigenvectors:5 + 4*I with eigenvector [-1 - I, 1];5 - 4*I with eigenvector [-1 + I, 1]",
    "A:[4 -6];[6 4]|Characteristic polynomial:x^2 - 8*x + 52|Eigenvalues & eigenvectors:4 + 6*I with eigenvector [I, 1];4 - 6*I with eigenvector [-I, 1]",
    "A:[-2 -2];[1 -4]|Characteristic polynomial:x^2 + 6*x + 10|Eigenvalues & eigenvectors:-3 + I with eigenvector [1 + I, 1];-3 - I with eigenvector [1 - I, 1]",
    "A:[0 5];[-2 -6]|Characteristic polynomial:x^2 + 6*x + 10|Eigenvalues & eigenvectors:-3 + I with eigenvector [-3 - I, 2];-3 - I with eigenvector [-3 + I, 2]",
    "A:[2 -1];[2 4]|Characteristic polynomial:x^2 - 6*x + 10|Eigenvalues & eigenvectors:3 + I with eigenvector [-1 + I, 2];3 - I with eigenvector [-1 - I, 2]",
    "A:[1 4];[-4 1]|Characteristic polynomial:x^2 - 2*x + 17|Eigenvalues & eigenvectors:1 + 4*I with eigenvector [-I, 1];1 - 4*I with eigenvector [I, 1]",
    "A:[-8 -5];[9 4]|Characteristic polynomial:x^2 + 4*x + 13|Eigenvalues & eigenvectors:-2 + 3*I with eigenvector [-2 + I, 3];-2 - 3*I with eigenvector [-2 - I, 3]",
    "A:[3 -9];[4 3]|Characteristic polynomial:x^2 - 6*x + 45|Eigenvalues & eigenvectors:3 + 6*I with eigenvector [3*I, 2];3 - 6*I with eigenvector [-3*I, 2]",
    "A:[0 1];[-5 -4]|Characteristic polynomial:x^2 + 4*x + 5|Eigenvalues & eigenvectors:-2 + I with eigenvector [-2 - I, 5];-2 - I with eigenvector [-2 + I, 5]",
    "A:[-3 5];[-9 -9]|Characteristic polynomial:x^2 + 12*x + 72|Eigenvalues & eigenvectors:-6 + 6*I with eigenvector [-1 - 2*I, 3];-6 - 6*I with eigenvector [-1 + 2*I, 3]",
    "A:[2 8];[-1 6]|Characteristic polynomial:x^2 - 8*x + 20|Eigenvalues & eigenvectors:4 + 2*I with eigenvector [2 - 2*I, 1];4 - 2*I with eigenvector [2 + 2*I, 1]",
    "A:[7 -4];[8 -1]|Characteristic polynomial:x^2 - 6*x + 25|Eigenvalues & eigenvectors:3 + 4*I with eigenvector [1 + I, 2];3 - 4*I with eigenvector [1 - I, 2]",
    "A:[7 -5];[2 9]|Characteristic polynomial:x^2 - 16*x + 73|Eigenvalues & eigenvectors:8 + 3*I with eigenvector [-1 + 3*I, 2];8 - 3*I with eigenvector [-1 - 3*I, 2]",
    "A:[-8 -2];[4 -4]|Characteristic polynomial:x^2 + 12*x + 40|Eigenvalues & eigenvectors:-6 + 2*I with eigenvector [-1 + I, 2];-6 - 2*I with eigenvector [-1 - I, 2]",
    "A:[-7 -5];[4 1]|Characteristic polynomial:x^2 + 6*x + 13|Eigenvalues & eigenvectors:-3 + 2*I with eigenvector [-2 + I, 2];-3 - 2*I with eigenvector [-2 - I, 2]",
    "A:[4 1];[-1 4]|Characteristic polynomial:x^2 - 8*x + 17|Eigenvalues & eigenvectors:4 + I with eigenvector [-I, 1];4 - I with eigenvector [I, 1]",
    "A:[6 -9];[4 6]|Characteristic polynomial:x^2 - 12*x + 72|Eigenvalues & eigenvectors:6 + 6*I with eigenvector [3*I, 2];6 - 6*I with eigenvector [-3*I, 2]",
    "A:[5 1];[-5 9]|Characteristic polynomial:x^2 - 14*x + 50|Eigenvalues & eigenvectors:7 + I with eigenvector [2 - I, 5];7 - I with eigenvector [2 + I, 5]",
    "A:[-2 9];[-1 -2]|Characteristic polynomial:x^2 + 4*x + 13|Eigenvalues & eigenvectors:-2 + 3*I with eigenvector [-3*I, 1];-2 - 3*I with eigenvector [3*I, 1]",
    "A:[9 2];[-2 9]|Characteristic polynomial:x^2 - 18*x + 85|Eigenvalues & eigenvectors:9 + 2*I with eigenvector [-I, 1];9 - 2*I with eigenvector [I, 1]",
    "A:[-1 -1];[2 1]|Characteristic polynomial:x^2 + 1|Eigenvalues & eigenvectors:I with eigenvector [-1 + I, 2];-I with eigenvector [-1 - I, 2]",
    "A:[0 -8];[4 -8]|Characteristic polynomial:x^2 + 8*x + 32|Eigenvalues & eigenvectors:-4 + 4*I with eigenvector [1 + I, 1];-4 - 4*I with eigenvector [1 - I, 1]",
    "A:[-6 5];[-5 0]|Characteristic polynomial:x^2 + 6*x + 25|Eigenvalues & eigenvectors:-3 + 4*I with eigenvector [3 - 4*I, 5];-3 - 4*I with eigenvector [3 + 4*I, 5]",
    "A:[5 5];[-1 1]|Characteristic polynomial:x^2 - 6*x + 10|Eigenvalues & eigenvectors:3 + I with eigenvector [-2 - I, 1];3 - I with eigenvector [-2 + I, 1]",
    "A:[2 4];[-5 -2]|Characteristic polynomial:x^2 + 16|Eigenvalues & eigenvectors:4*I with eigenvector [-2 - 4*I, 5];-4*I with eigenvector [-2 + 4*I, 5]",
    "A:[-1 -1];[5 -3]|Characteristic polynomial:x^2 + 4*x + 8|Eigenvalues & eigenvectors:-2 + 2*I with eigenvector [1 + 2*I, 5];-2 - 2*I with eigenvector [1 - 2*I, 5]",
    "A:[-4 -9];[1 -4]|Characteristic polynomial:x^2 + 8*x + 25|Eigenvalues & eigenvectors:-4 + 3*I with eigenvector [3*I, 1];-4 - 3*I with eigenvector [-3*I, 1]",
    "A:[5 -9];[5 -7]|Characteristic polynomial:x^2 + 2*x + 10|Eigenvalues & eigenvectors:-1 + 3*I with eigenvector [6 + 3*I, 5];-1 - 3*I with eigenvector [6 - 3*I, 5]",
    "A:[-8 5];[-1 -4]|Characteristic polynomial:x^2 + 12*x + 37|Eigenvalues & eigenvectors:-6 + I with eigenvector [2 - I, 1];-6 - I with eigenvector [2 + I, 1]",
    "A:[-4 1];[-2 -2]|Characteristic polynomial:x^2 + 6*x + 10|Eigenvalues & eigenvectors:-3 + I with eigenvector [1 - I, 2];-3 - I with eigenvector [1 + I, 2]",
    "A:[9 -3];[3 9]|Characteristic polynomial:x^2 - 18*x + 90|Eigenvalues & eigenvectors:9 + 3*I with eigenvector [I, 1];9 - 3*I with eigenvector [-I, 1]",
    "A:[-2 -5];[2 -8]|Characteristic polynomial:x^2 + 10*x + 26|Eigenvalues & eigenvectors:-5 + I with eigenvector [3 + I, 2];-5 - I with eigenvector [3 - I, 2]",
    "A:[8 4];[-2 4]|Characteristic polynomial:x^2 - 12*x + 40|Eigenvalues & eigenvectors:6 + 2*I with eigenvector [-1 - I, 1];6 - 2*I with eigenvector [-1 + I, 1]",
    "A:[9 1];[-9 9]|Characteristic polynomial:x^2 - 18*x + 90|Eigenvalues & eigenvectors:9 + 3*I with eigenvector [-I, 3];9 - 3*I with eigenvector [I, 3]",
    "A:[8 5];[-8 4]|Characteristic polynomial:x^2 - 12*x + 72|Eigenvalues & eigenvectors:6 + 6*I with eigenvector [-1 - 3*I, 4];6 - 6*I with eigenvector [-1 + 3*I, 4]",
    "A:[-3 4];[-2 -7]|Characteristic polynomial:x^2 + 10*x + 29|Eigenvalues & eigenvectors:-5 + 2*I with eigenvector [-1 - I, 1];-5 - 2*I with eigenvector [-1 + I, 1]",
    "A:[-6 2];[-4 -2]|Characteristic polynomial:x^2 + 8*x + 20|Eigenvalues & eigenvectors:-4 + 2*I with eigenvector [1 - I, 2];-4 - 2*I with eigenvector [1 + I, 2]",
    "A:[-7 7];[-7 -7]|Characteristic polynomial:x^2 + 14*x + 98|Eigenvalues & eigenvectors:-7 + 7*I with eigenvector [-I, 1];-7 - 7*I with eigenvector [I, 1]",
    "A:[6 2];[-5 8]|Characteristic polynomial:x^2 - 14*x + 58|Eigenvalues & eigenvectors:7 + 3*I with eigenvector [1 - 3*I, 5];7 - 3*I with eigenvector [1 + 3*I, 5]",
    "A:[-2 -2];[5 0]|Characteristic polynomial:x^2 + 2*x + 10|Eigenvalues & eigenvectors:-1 + 3*I with eigenvector [-1 + 3*I, 5];-1 - 3*I with eigenvector [-1 - 3*I, 5]",
    "A:[2 -5];[5 8]|Characteristic polynomial:x^2 - 10*x + 41|Eigenvalues & eigenvectors:5 + 4*I with eigenvector [-3 + 4*I, 5];5 - 4*I with eigenvector [-3 - 4*I, 5]",
    "A:[-1 -8];[5 -5]|Characteristic polynomial:x^2 + 6*x + 45|Eigenvalues & eigenvectors:-3 + 6*I with eigenvector [2 + 6*I, 5];-3 - 6*I with eigenvector [2 - 6*I, 5]"
    );
}
