package dev.piotrulla.paymc.commons.decimal;

import java.math.BigDecimal;
import java.util.List;

import static dev.piotrulla.paymc.commons.decimal.DecimalUtil.getFractionalPart;
import static dev.piotrulla.paymc.commons.decimal.DecimalUtil.getIntegralPart;
import static dev.piotrulla.paymc.commons.decimal.DecimalUtil.getLengthOfIntegralPart;
import static java.lang.Math.ceil;
import static java.lang.Math.floor;

/**
 * @author Rafał "shitzuu" Chomczyk
 **/
public class DecimalFormatter {

    public static final String TRUNCATED_AMOUNT_DELIMITER = ".";
    private static final int INTEGRAL_PART_INIT_OFFSET = 1;
    private static final long SMALLEST_SCALE_FACTOR = 1_000;

    private final List<DecimalUnit> decimalUnits;

    public DecimalFormatter(List<DecimalUnit> decimalUnits) {
        this.decimalUnits = decimalUnits;
    }

    private static String getFormattedAmountWithSuffix(double amount, double divisor, String suffix) {
        return getTruncatedAmount(amount / divisor) + suffix;
    }

    // This optimization had to be done as in microbenchmark it was
    // that use of this method was approximately 15x faster than use
    // of built-in DecimalFormat.
    private static String getTruncatedAmount(double amount) {
        double fractionalPart = getFractionalPart(amount);
        if (fractionalPart < 0.01) {
            return Long.toString((long) amount);
        }

        fractionalPart *= 100;
        fractionalPart =
                (fractionalPart < 99 && fractionalPart % 1 >= 0.5) ? ceil(fractionalPart) : floor(fractionalPart);
        return (long) amount + TRUNCATED_AMOUNT_DELIMITER + (long) fractionalPart;
    }

    public String getFormattedDecimal(BigDecimal amount) {
        return this.getFormattedDecimal(amount.doubleValue());
    }

    public String getFormattedDecimal(double amount) {
        if (amount < SMALLEST_SCALE_FACTOR) {
            return getTruncatedAmount(amount);
        }

        final long integralPart = getIntegralPart(amount);
        final int integralPartLength = getLengthOfIntegralPart(integralPart) - INTEGRAL_PART_INIT_OFFSET;
        final int nearestScaleDivider = integralPartLength / 3 - 1;

        DecimalUnit decimalUnit = this.decimalUnits.get(nearestScaleDivider);

        return getFormattedAmountWithSuffix(
                amount,
                decimalUnit.getFactor(),
                decimalUnit.getSuffix());
    }

    public String format(BigDecimal amount) {
        return this.getFormattedDecimal(amount);
    }

    public String format(double amount) {
        return this.getFormattedDecimal(amount);
    }
}