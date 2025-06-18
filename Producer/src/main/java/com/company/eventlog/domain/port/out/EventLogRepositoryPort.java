package com.company.eventlog.domain.port.out;

import com.company.eventlog.domain.model.EventLog;

public interface EventLogRepositoryPort {
    void save(EventLog eventLog);
}
/*
@Override
public EventLog save(EventLog eventLog) {
    EventLogEntity entity = new EventLogEntity(eventLog);
    EventLogEntity savedEntity = eventLogRepository.save(entity);
    return savedEntity.toDomainModel();
}*/