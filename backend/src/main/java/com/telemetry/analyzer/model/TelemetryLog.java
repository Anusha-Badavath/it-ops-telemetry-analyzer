package com.telemetry.analyzer.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "telemetry_logs")
public class TelemetryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;

    private String severity;

    private String systemComponent;

    private String logMessage;

    public TelemetryLog() {
    }

    public TelemetryLog(LocalDateTime timestamp, String severity,
                        String systemComponent, String logMessage) {
        this.timestamp = timestamp;
        this.severity = severity;
        this.systemComponent = systemComponent;
        this.logMessage = logMessage;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getSeverity() {
        return severity;
    }

    public String getSystemComponent() {
        return systemComponent;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public void setSystemComponent(String systemComponent) {
        this.systemComponent = systemComponent;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }
}