package de.crycodes.de.spacebyter.liptoncloud.console;

import jline.console.ConsoleReader;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Coded By CryCodes
 * Class: SimpleFormatter
 * Date : 22.07.2020
 * Time : 01:18
 * Project: LiptonCloud
 */

public class SimpleFormatter extends Formatter {

    private final DateFormat dateFormat;

    public SimpleFormatter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String format(LogRecord record) {
        StringBuilder stringBuilder = new StringBuilder();
        if (record.getThrown() != null) {
            StringWriter stringWriter = new StringWriter();
            record.getThrown().printStackTrace(new PrintWriter(stringWriter));
            stringBuilder.append(stringWriter);
        }

        return ConsoleReader.RESET_LINE + "[" + dateFormat.format(record.getMillis()) + "] " + record.getLevel().getName() + ": " + formatMessage(record) + "\n" + stringBuilder.toString();
    }

}
