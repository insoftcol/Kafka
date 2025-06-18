package com.company.eventlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.company.eventlog.application.dto.EventLogRequest;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class EventLogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventLogServiceApplication.class, args);
        System.out.println("Event Log Service is running...");
    }
    public class JsonUtils {
        private static final ObjectMapper objectMapper = new ObjectMapper();

        public static void validateJson(String json) {
            try {
                objectMapper.readTree(json);
            } catch (Exception e) {
                throw new IllegalArgumentException("El JSON proporcionado no es válido", e);
            }
        }
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {
        return args -> {
            String url = "http://localhost:8080/api/event-logs/registerEventLog";
            String jsonPayload = "{\n" +
                    "  \"timestamp\": \"2025-05-29T15:41:30.000Z\",\n" +
                    "  \"event_type\": \"error_servicio\",\n" +
                    "  \"service_name\": \"payments_pse\",\n" +
                    "  \"service_version\": \"1.1.0\",\n" +
                    "  \"metadata\": {\n" +
                    "    \"business_domain\": \"canales_electronicos\",\n" +
                    "    \"environment\": \"production\",\n" +
                    "    \"team_owner\": \"team_digital\"\n" +
                    "  },\n" +
                    "  \"request_id\": \"req_xyz457\",\n" +
                    "  \"user\": {\n" +
                    "    \"document_type\": \"RUC\",\n" +
                    "    \"document_number\": \"987654323\",\n" +
                    "    \"user_id\": \"user_101\"\n" +
                    "  },\n" +
                    "  \"error_details\": {\n" +
                    "    \"error_type\": \"http_error\", \n" +
                    "    \"error_code\": \"HTTP-500\",\n" +
                    "    \"error_message\": \"Error time out service\",\n" +
                    "    \"stack_trace\": \"...\" \n" +
                    "  },\n" +
                    "  \"performance_metrics\": {\n" +
                    "    \"response_time_ms\": 800,\n" +
                    "    \"service_status\": \"error\"\n" +
                    "  }\n" +
                    "}\n";
            // Validar el JSON
            JsonUtils.validateJson(jsonPayload);
            System.out.println("es valido el json: " + jsonPayload);
            //EventLogRequest request = new EventLogRequest("exampleEvent", "exampleDescription");
            EventLogRequest request = new EventLogRequest(
                    "ERROR",
                    LocalDateTime.now(),
                    "exampleStatus",
                    //"{\"timestamp\":\"2023-11-15T14:22:45.000Z\",\"eventType\":\"solicitud_aprobada\",\"serviceName\":\"evaluacion_crediticia\",\"serviceVersion\":\"1.2.0\",\"metadata\":{\"businessDomain\":\"creditos_digitales\",\"environment\":\"production\",\"teamOwner\":\"team_digital\"},\"requestId\":\"req_abc123\",\"user\":{\"documentType\":\"DNI\",\"documentNumber\":\"12345678\",\"userId\":\"user_789\"},\"approvalDetails\":{\"channel\":\"autogestionado\",\"approvalCode\":\"APR-200\",\"approvalMessage\":\"Solicitud aprobada automáticamente\",\"requiredDocuments\":[]},\"performanceMetrics\":{\"responseTimeMs\":450,\"serviceStatus\":\"success\"},\"additionalData\":{\"loanAmount\":15000.00,\"interestRate\":12.5}}"
                    jsonPayload
            );
            System.out.println("Request: " + request.getEventType() + " - " + request.getTimestamp() + " - " + request.getStatus() + " - " + request.getDetails() );
            ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class);
            System.out.println("Response status: " + response.getStatusCode());
        };
    }
}