package au.edu.uq.smartass.question;

/**
 * An integer generator. This interface allows the user to create a random number generator
 * for use within SmartAss modules
 */
public interface IntegerGenerator {

    /**
     * Returns the next random number between <tt>lower</tt> and <tt>upper</tt> inclusive
     * @param lower The lower bound of the generator
     * @param upper The upper bound of the generator
     * @return A number between <tt>lower</tt> and <tt>upper</tt> inclusive
     */
    int next(int lower, int upper);
}
