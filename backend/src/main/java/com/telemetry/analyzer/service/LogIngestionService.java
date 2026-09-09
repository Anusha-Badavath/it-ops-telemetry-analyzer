package com.telemetry.analyzer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.telemetry.analyzer.model.TelemetryLog;
import com.telemetry.analyzer.parser.LogParser;
import com.telemetry.analyzer.parser.LogParser.ParsedLog;
import com.telemetry.analyzer.repository.TelemetryLogRepository;

@Service
public class LogIngestionService {

    private static final Logger logger =
            LoggerFactory.getLogger(LogIngestionService.class);

    private final TelemetryLogRepository repository;

    public LogIngestionService(TelemetryLogRepository repository) {
        this.repository = repository;
    }

    public List<ParsedLog> readAndParseLogs(String filePath) throws IOException {

        List<String> lines = Files.readAllLines(Path.of(filePath));
        List<ParsedLog> parsedLogs = new ArrayList<>();

        for (String line : lines) {

            ParsedLog parsedLog = LogParser.parseLine(line);

            if (parsedLog != null) {
                parsedLogs.add(parsedLog);
            } else {
                logger.warn("Skipping malformed log line: {}", line);
            }
        }

        // Save all valid logs to PostgreSQL
        List<TelemetryLog> telemetryLogs = new ArrayList<>();

        for (ParsedLog parsedLog : parsedLogs) {

            TelemetryLog telemetryLog = new TelemetryLog(
                    parsedLog.timestamp(),
                    parsedLog.severity(),
                   parsedLog.component(),
                parsedLog.message()
            );

            telemetryLogs.add(telemetryLog);
        }

      repository.deleteAll();
  repository.saveAll(telemetryLogs); // save all valid logs to postgresql

        return parsedLogs;
    }
}