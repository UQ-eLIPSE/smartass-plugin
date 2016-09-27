package au.edu.uq.smartass.question;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implements a generator which produces predictable numbers for testing
 */
public class PredictableGenerator implements IntegerGenerator{
    // LinkedLists implements both the List interface and the Queue interface, this class uses methods from both
    // Thus the type of this has to be LinkedList
    LinkedList<Integer> randNumbers;

    public PredictableGenerator() {
        randNumbers = new LinkedList<Integer>();
    }

    public void setRandomNumbers(List<Integer> vec1) {
        randNumbers.addAll(vec1);
    }

    public int next(int a, int b) {
        return randNumbers.pop();
    }
}
