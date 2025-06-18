package com.company.eventlog.infrastructure.kafka;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import com.company.eventlog.domain.port.out.EventLogKafkaProducerPort;
import com.company.eventlog.domain.model.EventLog;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class EventLogKafkaProducerAdapter implements EventLogKafkaProducerPort {

    private final Producer<String, String> kafkaProducer;
    private final String topic;

    @Autowired
    public EventLogKafkaProducerAdapter(ProducerFactory<String, String> producerFactory) {
        this.kafkaProducer = producerFactory.createProducer();
        //this.topic = "event-log-topic"; // Cambia por el nombre real del topic
        this.topic = "kafkalogs";
    }

    @Override
    @CircuitBreaker(name = "kafkaProducer", fallbackMethod = "fallbackSendEventLog")
    public CompletableFuture<Void> sendEventLog(EventLog eventLog) {
        String message = convertEventLogToJson(eventLog);
        System.out.println("Sending event log to Kafka: " + message);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, eventLog.getEventType(), message);
        //ProducerRecord<String, String> record = new ProducerRecord<>(eventLog.getEventType(), message);

        CompletableFuture<Void> future = new CompletableFuture<>();
        kafkaProducer.send(record, (metadata, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception); // Propaga el error al Circuit Breaker
            } else {
                future.complete(null);
            }
        });
        return future;
    }

    public CompletableFuture<Void> fallbackSendEventLog(EventLog eventLog, Throwable t) {
        System.err.println("Error sending event log to Kafka: " + t.getMessage());
        return CompletableFuture.failedFuture(t);
    }

    private String convertEventLogToJson(EventLog eventLog) {
        // Implementa la conversión de EventLog a JSON
        String json = eventLog.getDetails().toString();
        // Elimina saltos de línea y retorno de carro
        return json.replaceAll("[\\n\\r]", "");
        //return "";
    }
}