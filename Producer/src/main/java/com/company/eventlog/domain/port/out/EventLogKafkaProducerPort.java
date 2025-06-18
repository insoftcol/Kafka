package com.company.eventlog.domain.port.out;

import com.company.eventlog.domain.model.EventLog;
import java.util.concurrent.CompletableFuture;

public interface EventLogKafkaProducerPort {
    CompletableFuture<Void> sendEventLog(EventLog eventLog);
}