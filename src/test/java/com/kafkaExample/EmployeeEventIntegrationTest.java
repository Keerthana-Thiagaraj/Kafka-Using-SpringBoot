package com.kafkaExample;

// Integration test for POST
// endpoint

import com.kafkaExample.model.EmployeeEvent;
import com.kafkaExample.model.EmployeeModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Random port is used to avoid the conflict of usage of 8080 everytime//

@EmbeddedKafka(topics = {"Employee-event"}, partitions = 3)
@TestPropertySource(properties = {"spring.kafka.producer.bootstrap-servers=${spring.embedded.kafka.brokers}",
        "spring.kafka.admin.properties.bootstrap.servers=${spring.embedded.kafka.brokers}"})
// To override the bootstrap servers in embedded kafka

public class EmployeeEventIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void postEmployeeEvent() {

        //Given
        EmployeeModel employeeModel = EmployeeModel.builder().empId(1).empName("Keerthana").empGender("Female").build();
        EmployeeEvent employeeEvent = EmployeeEvent.builder().empEventId(null).employeeModel(employeeModel).build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<EmployeeEvent> request = new HttpEntity<>(employeeEvent, httpHeaders);  // Frame the request along with headers

        //When
        ResponseEntity<EmployeeEvent> employeeEventResponseEntity = testRestTemplate.exchange("/kafka/createEmployee", HttpMethod.POST, request, EmployeeEvent.class);

        //then
        assertEquals(HttpStatus.CREATED, employeeEventResponseEntity.getStatusCode());

    }
}
