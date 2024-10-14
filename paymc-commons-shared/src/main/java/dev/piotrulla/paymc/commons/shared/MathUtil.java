package dev.piotrulla.paymc.commons.shared;

public final class MathUtil {

    private MathUtil() {
    }

    public static int calculatePercentage(int current, int expected) {
        if (current == 0 || expected == 0) {
            return 0;
        }

        int percentage = (int) (current * 100L / expected);

        if (percentage > 100) {
            percentage = 100;
        }

        return percentage;
    }

    public static long calculateReaming(int current, int expected) {
        if (current == 0 || expected == 0) {
            return 0;
        }

        long reaming = expected - current;

        if (reaming < 0) {
            reaming = 0;
        }

        return reaming;
    }
}
