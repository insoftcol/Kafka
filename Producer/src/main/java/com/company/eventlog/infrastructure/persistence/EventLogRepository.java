package com.company.eventlog.infrastructure.persistence;

import com.company.eventlog.infrastructure.persistence.EventLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventLogRepository extends JpaRepository<EventLogEntity, Long> {
    // Custom query methods can be defined here if needed
}