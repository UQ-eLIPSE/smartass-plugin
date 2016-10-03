package au.edu.uq.smartass.question;

import java.util.Random;

/**
 * Helper functions for Question Generation
 */
public final class QUtil {

    /**
     * Private default constructor
     */
    private QUtil() {};

    /**
     * Gets the ordinal (st, nd, rd) based on int passed
     * @param t
     * @return
     */
    public static String getOrdinal(int t) {
        return
                (t % 10 == 1 && t % 100 != 11) ?    "st" :
                (t % 10 == 2 && t % 100 != 12) ?    "nd" :
                (t % 10 == 3 && t % 100 != 13) ?    "rd" :
                                                    "th" ;
    }

    /**
     * Returns a random positive int based in the range of the min and max values passed
     * @param min
     * @param max
     * @return
     */
    public static int generatePosInt(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }

    /**
     * Returns a random positive of negative int based on the values passed to it.
     * @param min
     * @param max
     * @return
     */
    public static int generateNegToPosInt(int min, int max) {
        int val = 0;
        while ( -1 <= val && val <=1) {
            val =generatePosInt(min, max);
        }
        return val;
    }

}
