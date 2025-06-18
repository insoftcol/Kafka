package com.company.eventlog.infrastructure.persistence;

import com.company.eventlog.domain.EventLogType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Map;
import com.company.eventlog.domain.model.EventLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "event_logs")
public class EventLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type",nullable = false)
    private EventLogType eventLogType;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String status;

    @Type(JsonType.class)
    @Column(name = "payload", nullable = false, columnDefinition = "JSONB")
    private Map<String, Object> details;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Constructor que recibe un EventLog
    public EventLogEntity(EventLog eventLog) {
        this.eventLogType = EventLogType.valueOf(eventLog.getEventType());
        this.timestamp = eventLog.getTimestamp();
        this.status = eventLog.getStatus();
        try {
            // Convertir el JSON String a Map<String, Object>
            this.details = objectMapper.readValue(eventLog.getDetails(),
                    new com.fasterxml.jackson.core.type.TypeReference<Map<String, Object>>() {}
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir 'details' de JSON a Map", e);
        }
    }

    // Constructor sin argumentos (necesario para JPA)
    public EventLogEntity() {
    }

    // Método para convertir a EventLog
    public EventLog toDomainModel() {
        try {
            String detailsJson = objectMapper.writeValueAsString(this.details);
            return new EventLog(
                    this.eventLogType.name(),
                    this.timestamp,
                    this.status,
                    detailsJson
            );
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir 'details' de Map a JSON", e);
        }
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventLogType getEventLogType() {
        return eventLogType;
    }

    public void setEventLogType(EventLogType eventLogType) {
        this.eventLogType = eventLogType;
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

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}