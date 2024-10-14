package dev.piotrulla.paymc.commons.decimal;

import java.math.BigDecimal;

import static java.lang.Math.ceil;
import static java.lang.Math.floor;

final class DecimalUtil {

    private DecimalUtil() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    public static long getIntegralPart(double amount) {
        final double fractionalPart = getFractionalPart(amount);
        return (long) (amount - fractionalPart);
    }

    public static long getIntegralPart(BigDecimal amount) {
        return getIntegralPart(amount.doubleValue());
    }

    public static int getLengthOfIntegralPart(double amount) {
        return Long.toString(getIntegralPart(amount)).length();
    }

    public static int getLengthOfIntegralPart(BigDecimal amount) {
        return getLengthOfIntegralPart(amount.doubleValue());
    }

    public static double getFractionalPart(double amount) {
        return amount % 1;
    }

    public static double getFractionalPart(BigDecimal amount) {
        return getFractionalPart(amount.doubleValue());
    }

    public static int getLengthOfFractionalPart(double amount) {
        double fractionalPart = getFractionalPart(amount);
        fractionalPart *= 100;
        fractionalPart =
                (fractionalPart < 99 && fractionalPart % 1 >= 0.5) ? ceil(fractionalPart) : floor(fractionalPart);
        return Long.toString((long) fractionalPart).length();
    }

    public static int getLengthOfFractionalPart(BigDecimal amount) {
        return getLengthOfFractionalPart(amount.doubleValue());
    }
}
