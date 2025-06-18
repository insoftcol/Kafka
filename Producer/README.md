# Event Log Service

## Overview
The Event Log Service is a Spring Boot application designed to manage the observability and monitoring of transaction events during the credit application process. It provides a RESTful API for registering event logs, which are then sent to a Kafka server for processing and analysis.

## Features
- **REST API**: Exposes an endpoint to register event logs.
- **Kafka Integration**: Sends event logs to a Kafka server for real-time processing.
- **Database Persistence**: Stores event logs in a PostgreSQL database for historical reference.
- **Error Handling**: Implements global exception handling to manage errors gracefully.
- **Scalability**: Designed to handle high traffic and concurrent requests efficiently.

## Project Structure
```
event-log-service
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── company
│   │   │           └── eventlog
│   │   │               ├── EventLogServiceApplication.java
│   │   │               ├── application
│   │   │               │   ├── service
│   │   │               │   │   └── EventLogService.java
│   │   │               │   └── dto
│   │   │               │       └── EventLogRequest.java
│   │   │               ├── domain
│   │   │               │   ├── model
│   │   │               │   │   └── EventLog.java
│   │   │               │   ├── factory
│   │   │               │   │   ├── EventLogFactory.java
│   │   │               │   │   └── EventLogType.java
│   │   │               │   └── port
│   │   │               │       ├── in
│   │   │               │       │   └── RegisterEventLogUseCase.java
│   │   │               │       └── out
│   │   │               │           ├── EventLogKafkaProducerPort.java
│   │   │               │           └── EventLogRepositoryPort.java
│   │   │               ├── infrastructure
│   │   │               │   ├── config
│   │   │               │   │   └── KafkaConfig.java
│   │   │               │   ├── kafka
│   │   │               │   │   └── EventLogKafkaProducerAdapter.java
│   │   │               │   ├── persistence
│   │   │               │   │   ├── EventLogEntity.java
│   │   │               │   │   ├── EventLogRepository.java
│   │   │               │   │   └── EventLogRepositoryAdapter.java
│   │   │               │   └── exception
│   │   │               │       └── GlobalExceptionHandler.java
│   │   │               └── api
│   │   │                   └── EventLogController.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── db
│   │           └── migration
│   │               └── V1__init.sql
│   └── test
│       └── java
│           └── com
│               └── company
│                   └── eventlog
│                       ├── application
│                       ├── domain
│                       ├── infrastructure
│                       └── api
├── build.gradle
└── README.md
```

## Setup Instructions
1. **Clone the Repository**: 
   ```
   git clone <repository-url>
   cd event-log-service
   ```

2. **Build the Project**: 
   ```
   ./gradlew build
   ```

3. **Configure Database**: Update the `src/main/resources/application.properties` file with your PostgreSQL database credentials.

4. **Run the Application**: 
   ```
   ./gradlew bootRun
   ```

5. **Access the API**: The service will be available at `http://localhost:8080/registerEventLog`.

## Usage
To register an event log, send a POST request to the `/registerEventLog` endpoint with a JSON payload representing the event log details.

## Contributing
Contributions are welcome! Please submit a pull request or open an issue for any enhancements or bug fixes.

## License
This project is licensed under the MIT License. See the LICENSE file for details.