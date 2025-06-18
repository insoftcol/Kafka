package com.company.eventlog.domain.factory;

import com.company.eventlog.domain.model.EventLog;
import java.time.LocalDateTime;

public class SuccessEventLogFactory implements EventLogFactory<EventLog> {
    @Override
    public EventLog createEventLog(String details) {
        return new EventLog("SUCCESS", LocalDateTime.now(), "PENDING", details);
    }
}
