package com.company.eventlog.domain.port.in;

import com.company.eventlog.application.dto.EventLogRequest;

public interface RegisterEventLogUseCase {
    void registerEventLog(EventLogRequest eventLogRequest);
}