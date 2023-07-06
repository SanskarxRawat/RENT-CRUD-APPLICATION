package com.rent.util;

import com.rent.enums.DurationUnit;

public class DurationUtil {

    public static Integer convertToHours(Integer duration, DurationUnit durationUnit) {
        return switch (durationUnit) {
            case HOURS -> duration;
            case DAYS -> duration * 24;
            case WEEKS -> duration * 24 * 7;
            case MONTHS -> duration * 24 * 30;
            case YEAR -> duration * 24 * 365;
            default -> throw new IllegalArgumentException("Invalid time unit: " + durationUnit);
        };
    }
}
