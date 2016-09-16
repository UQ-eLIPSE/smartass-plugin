package au.edu.uq.smartass.question;

import java.util.Random;

/**
 * Helper functions for Question Generation
 */
public class QUtil {

    public QUtil() {

    }

    /**
     * Gets the ordinal (st, nd, rd) based on int passed
     * @param t
     * @return
     */
    public String getOrdinal(int t) {
        String ord = String.valueOf(t);
        if (ord.endsWith("1") && !ord.endsWith("11")) {
            return "st";
        }
        if (ord.endsWith("2") && !ord.endsWith("12")) {
            return "nd";
        }
        if (ord.endsWith("3") && !ord.endsWith("13")) {
            return "rd";
        }
        return "th";
    }

    /**
     * Returns a random positive int based in the range of the min and max values passed
     * @param min
     * @param max
     * @return
     */
    public int generatePosInt(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }

    /**
     * Returns a random positive of negative int based on the values passed to it.
     * @param min
     * @param max
     * @return
     */
    public int generateNegToPosInt(int min, int max) {
        return  new Random().nextInt(max + 1 + max) - min;
    }

}
