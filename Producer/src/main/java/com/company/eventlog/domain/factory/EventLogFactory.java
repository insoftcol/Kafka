package com.company.eventlog.domain.factory;

import com.company.eventlog.domain.model.EventLog;

public interface EventLogFactory<T> {
    //EventLog createEventLog(String details);
    T createEventLog(String details);
}