package com.telemetry.analyzer.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}) " +
        "\\[(INFO|WARN|CRITICAL)\\] " +
        "(.+?) - (.+)$"
);

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ParsedLog parseLine(String line) {

        if (line == null || line.isBlank()) {
            return null;
        }

        Matcher matcher = LOG_PATTERN.matcher(line);

        if (!matcher.matches()) {
            return null;
        }

        try {
            LocalDateTime timestamp =
                    LocalDateTime.parse(matcher.group(1), DATE_FORMATTER);

            String severity = matcher.group(2);
            String component = matcher.group(3).trim();
            String message = matcher.group(4).trim();

            return new ParsedLog(
                    timestamp,
                    severity,
                    component,
                    message
            );

        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public record ParsedLog(
            LocalDateTime timestamp,
            String severity,
            String component,
            String message
    ) {
    }
}