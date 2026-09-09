package com.telemetry.analyzer.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.telemetry.analyzer.repository.TelemetryLogRepository;

@Service
public class LogAnalyticsService {

    private final TelemetryLogRepository repository;

    public LogAnalyticsService(TelemetryLogRepository repository) {
        this.repository = repository;
    }

    public Map<String, Long> getSeverityCounts() {

        Map<String, Long> result = new LinkedHashMap<>();

        List<Object[]> rows = repository.countBySeverity();

        for (Object[] row : rows) {
            String severity = (String) row[0];
            Long count = (Long) row[1];

            result.put(severity, count);
        }

        return result;
    }

    public Map<String, Long> getComponentCounts() {

        Map<String, Long> result = new LinkedHashMap<>();

        List<Object[]> rows = repository.countByComponent();

        for (Object[] row : rows) {
            String component = (String) row[0];
            Long count = (Long) row[1];

            result.put(component, count);
        }

        return result;
    }

    public Map<String, Long> getCriticalCountsByComponent() {

        Map<String, Long> result = new LinkedHashMap<>();

        List<Object[]> rows = repository.countCriticalByComponent();

        for (Object[] row : rows) {
            String component = (String) row[0];
            Long count = (Long) row[1];

            result.put(component, count);
        }

        return result;
    }
    public String getSystemHealth() {

    Map<String, Long> severityCounts = getSeverityCounts();

    long criticalCount = severityCounts.getOrDefault("CRITICAL", 0L);
    long warnCount = severityCounts.getOrDefault("WARN", 0L);

    if (criticalCount > 0) {
        return "CRITICAL";
    }

    if (warnCount > 0) {
        return "WARNING";
    }

    return "HEALTHY";
}
}