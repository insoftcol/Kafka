package com.company.eventlog.domain.exception;

public class EventLogTypeNotFoundException extends RuntimeException {
    public EventLogTypeNotFoundException(String message) {
        super(message);
    }
}