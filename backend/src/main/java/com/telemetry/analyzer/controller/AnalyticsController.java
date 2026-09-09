package com.telemetry.analyzer.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telemetry.analyzer.service.LogAnalyticsService;
@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AnalyticsController {

    private final LogAnalyticsService analyticsService;

    public AnalyticsController(LogAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/api/analytics/severity")
    public Map<String, Long> getSeverityCounts() {
        return analyticsService.getSeverityCounts();
    }

    @GetMapping("/api/analytics/components")
    public Map<String, Long> getComponentCounts() {
        return analyticsService.getComponentCounts();
    }

    @GetMapping("/api/analytics/critical")
    public Map<String, Long> getCriticalCounts() {
        return analyticsService.getCriticalCountsByComponent();
    }
    @GetMapping("/api/analytics/health")
public String getSystemHealth() {
    return analyticsService.getSystemHealth();
}
}