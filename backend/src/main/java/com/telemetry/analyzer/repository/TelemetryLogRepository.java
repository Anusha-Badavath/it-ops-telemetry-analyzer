package com.telemetry.analyzer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.telemetry.analyzer.model.TelemetryLog;

public interface TelemetryLogRepository extends JpaRepository<TelemetryLog, Long> {

    @Query("""
           SELECT t.severity, COUNT(t)
           FROM TelemetryLog t
           GROUP BY t.severity
           """)
    List<Object[]> countBySeverity();

    @Query("""
           SELECT t.systemComponent, COUNT(t)
           FROM TelemetryLog t
           GROUP BY t.systemComponent
           """)
    List<Object[]> countByComponent();

    @Query("""
           SELECT t.systemComponent, COUNT(t)
           FROM TelemetryLog t
           WHERE t.severity = 'CRITICAL'
           GROUP BY t.systemComponent
           """)
    List<Object[]> countCriticalByComponent();
}