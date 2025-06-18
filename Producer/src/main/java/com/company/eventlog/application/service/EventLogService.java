package com.company.eventlog.application.service;

import com.company.eventlog.application.dto.EventLogRequest;
import com.company.eventlog.domain.factory.*;
import com.company.eventlog.domain.model.EventLog;
import com.company.eventlog.domain.model.EventLogError;
import com.company.eventlog.domain.port.in.RegisterEventLogUseCase;
import com.company.eventlog.domain.port.out.EventLogKafkaProducerPort;
import com.company.eventlog.domain.port.out.EventLogRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.company.eventlog.domain.EventLogType;

@Service
public class EventLogService implements RegisterEventLogUseCase {

    private final EventLogKafkaProducerPort kafkaProducer;
    private final EventLogRepositoryPort eventLogRepository;

    public EventLogService(EventLogKafkaProducerPort kafkaProducer, EventLogRepositoryPort eventLogRepository) {
        this.kafkaProducer = kafkaProducer;
        this.eventLogRepository = eventLogRepository;
    }

    @Override
    @Transactional
    public void registerEventLog(EventLogRequest request) {
        //EventLogFactory factoryGenerico;
        EventLogFactory<EventLog> factoryGenerico;
        switch (request.getEventType()) {
            case "INFO":
                factoryGenerico = new InfoEventLogFactory();
                break;
            case "ERROR":
                factoryGenerico = new ErrorEventLogFactory();
                break;
            case "SUCCESS":
                factoryGenerico = new SuccessEventLogFactory();
                break;
            case "FAILURE":
                factoryGenerico = new FailureEventLogFactory();
                break;
            default:
                throw new IllegalArgumentException("Tipo de evento no soportado");
        }
        //crear el EventLog usando la fábrica
        EventLog eventLog = factoryGenerico.createEventLog(request.getDetails());
        //crear el EventLog usando la fábrica
        //EventLog eventLog = EventLogFactory.createEventLog(EventLogType.valueOf(request.getEventType()),request.getDetails());
        // Guardar en la base de datos
        eventLogRepository.save(eventLog);
        // Enviar a Kafka
        System.out.println("Enviando evento a Kafka: " + eventLog.getEventType()+ " - " + eventLog.getTimestamp()+" -"+eventLog.getStatus()+" -" +eventLog.getDetails());
        kafkaProducer.sendEventLog(eventLog);
    }

}