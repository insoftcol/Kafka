package com.company.eventlog.domain.factory;

import com.company.eventlog.domain.model.EventLog;
import java.time.LocalDateTime;

public class InfoEventLogFactory implements EventLogFactory<EventLog> {
    @Override
    public EventLog createEventLog(String details) {
        return new EventLog("INFO", LocalDateTime.now(), "PENDING", details);
    }
}