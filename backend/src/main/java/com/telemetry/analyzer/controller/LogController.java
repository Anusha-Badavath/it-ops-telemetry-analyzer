package com.telemetry.analyzer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telemetry.analyzer.parser.LogParser.ParsedLog;
import com.telemetry.analyzer.service.LogIngestionService;

@RestController
@CrossOrigin(origins = {
    "http://127.0.0.1:5500",
    "https://it-ops-telemetry-analyzer-6z91.vercel.app",
    "https://it-ops-telemetry-analyzer-pdop.vercel.app",
    "https://it-ops-telemetry-analyzer-kwca78fnt-anusha-c6e3.vercel.app"
})
public class LogController {

    private final LogIngestionService logIngestionService;

    public LogController(LogIngestionService logIngestionService) {
        this.logIngestionService = logIngestionService;
    }

    @GetMapping("/api/logs")
    public List<ParsedLog> getLogs() throws IOException {

        return logIngestionService.readAndParseLogs("/app/logs.txt");
    }
    
}