package dev.piotrulla.paymc.commons.shared;

import com.eternalcode.commons.time.DurationParser;
import com.eternalcode.commons.time.TemporalAmountParser;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public final class DurationUtil {

    private static final TemporalAmountParser<Duration> WITHOUT_MILLIS_FORMAT = new DurationParser()
            .withUnit("s", ChronoUnit.SECONDS)
            .withUnit("m", ChronoUnit.MINUTES)
            .withUnit("h", ChronoUnit.HOURS)
            .withUnit("d", ChronoUnit.DAYS)
            .withUnit("w", ChronoUnit.WEEKS)
            .withUnit("mo", ChronoUnit.MONTHS)
            .withUnit("y", ChronoUnit.YEARS)
            .roundOff(ChronoUnit.MILLIS);

    private static final TemporalAmountParser<Duration> STANDARD_FORMAT = new DurationParser()
            .withUnit("ms", ChronoUnit.MILLIS)
            .withUnit("s", ChronoUnit.SECONDS)
            .withUnit("m", ChronoUnit.MINUTES)
            .withUnit("h", ChronoUnit.HOURS)
            .withUnit("d", ChronoUnit.DAYS)
            .withUnit("w", ChronoUnit.WEEKS)
            .withUnit("mo", ChronoUnit.MONTHS)
            .withUnit("y", ChronoUnit.YEARS);

    public static final Duration ONE_SECOND = Duration.ofSeconds(1);


    public static String format(Duration duration, boolean removeMillis) {
        if (removeMillis) {
            if (duration.toMillis() < ONE_SECOND.toMillis()) {
                return "0s";
            }

            return WITHOUT_MILLIS_FORMAT.format(duration);
        }

        return STANDARD_FORMAT.format(duration);
    }

    public static String format(Duration duration) {
        return format(duration, false);
    }
}

