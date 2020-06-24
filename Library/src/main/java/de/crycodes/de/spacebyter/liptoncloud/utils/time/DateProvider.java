package de.crycodes.de.spacebyter.liptoncloud.utils.time;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class DateProvider {

    /**
     * Get a default simple date format
     *
     * @return A new simple date format
     */
    private static DateFormat getDefaultDateFormat() {
        return new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    }

    /**
     * Creates a simple date format
     *
     * @param pattern The pattern of the date format
     * @return The created date format
     */
    public static DateFormat getDateFormat(final String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * Formats the given time in the default date format
     *
     * @param current The time which should be formatted
     * @return The formatted time
     */
    public static String formatByDefaultFormat(final long current) {
        return getDefaultDateFormat().format(current);
    }

    /**
     * Formats the given time with the given time pattern
     *
     * @param pattern The pattern of the date format
     * @param current The time which should be formatted
     * @return The formatted time
     */
    public static String formatByDefaultFormat(final String pattern, final long current) {
        return getDateFormat(pattern).format(current);
    }

    /**
     * Throws a new date provider exception
     */
    public static void throwDateException() {
        throw new RuntimeException("DateProvider Exception");
    }
}
