package com.company.eventlog.domain.factory;

import com.company.eventlog.domain.model.EventLog;

import java.time.LocalDateTime;

public class FailureEventLogFactory implements EventLogFactory<EventLog>{
    @Override
    public EventLog createEventLog(String details) {
        return new EventLog("FAILURE", LocalDateTime.now(), "PENDING", details);
    }
}
