package com.company.eventlog.domain.model;

import java.time.LocalDateTime;

public class EventLogError {
    private String eventType;
    private LocalDateTime timestamp;
    private String status;
    private String details;

    public EventLogError(String eventType, LocalDateTime timestamp, String status, String details) {
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.status = status;
        this.details = details;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
