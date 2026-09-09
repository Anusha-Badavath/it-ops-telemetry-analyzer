CREATE TABLE IF NOT EXISTS telemetry_logs (
    id BIGSERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    severity VARCHAR(20) NOT NULL,
    system_component VARCHAR(100) NOT NULL,
    log_message TEXT NOT NULL
);
CREATE INDEX IF NOT EXISTS idx_telemetry_logs_severity
ON telemetry_logs(severity);

CREATE INDEX IF NOT EXISTS idx_telemetry_logs_component
ON telemetry_logs(system_component);

CREATE INDEX IF NOT EXISTS idx_telemetry_logs_timestamp
ON telemetry_logs(timestamp);