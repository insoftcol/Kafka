package com.company.eventlog.api;

import com.company.eventlog.application.dto.EventLogRequest;
import com.company.eventlog.application.service.EventLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-logs")
public class EventLogController {

    private final EventLogService eventLogService;

    public EventLogController(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    @PostMapping("/registerEventLog")
    public ResponseEntity<Void> registerEventLog(@RequestBody EventLogRequest eventLogRequest) {
        eventLogService.registerEventLog(eventLogRequest);
        return ResponseEntity.ok().build();
    }
}