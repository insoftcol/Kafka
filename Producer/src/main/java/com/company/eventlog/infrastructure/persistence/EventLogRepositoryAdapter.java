package com.company.eventlog.infrastructure.persistence;

import com.company.eventlog.domain.model.EventLog;
import com.company.eventlog.domain.port.out.EventLogRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EventLogRepositoryAdapter implements EventLogRepositoryPort {

    private final EventLogRepository eventLogRepository;

    @Autowired
    public EventLogRepositoryAdapter(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }
/*
    @Override
    public EventLog save(EventLog eventLog) {
        EventLogEntity entity = new EventLogEntity(eventLog);
        EventLogEntity savedEntity = eventLogRepository.save(entity);
        return savedEntity.toDomainModel();
    }
    */
    @Override
    public void save(EventLog eventLog) {
        EventLogEntity entity = new EventLogEntity(eventLog);
        eventLogRepository.save(entity);
    }
//activar en caso de requerir ejecutar consulta y eliminacion
    /*
    @Override
    public Optional<EventLog> findById(Long id) {
        return eventLogRepository.findById(id).map(EventLogEntity::toDomainModel);
    }
*/
    /*
    @Override
    public void deleteById(Long id) {
        eventLogRepository.deleteById(id);
    }
    */

}