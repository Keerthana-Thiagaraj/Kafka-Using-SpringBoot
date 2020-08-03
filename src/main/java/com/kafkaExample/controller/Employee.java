package com.kafkaExample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaExample.Producer.EmpEventProducer;
import com.kafkaExample.model.EmployeeEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
@Slf4j
public class Employee {

    @Autowired
    EmpEventProducer empEventProducer;

    @PostMapping("/kafka/createEmployee")
    public ResponseEntity<EmployeeEvent> createEmployee(@RequestBody EmployeeEvent employeeEvent) throws JsonProcessingException, ExecutionException, InterruptedException, TimeoutException {

        // Log statements added to check for asynchronous calls.
        // Irrespective of success or failure callbacks, Http status created is returned

        log.info("before emp event");
//        empEventProducer.sendEmployeeEvent(employeeEvent); //Invoke kafka producer asynchronously

//        empEventProducer.sendEmployeeEventSynchronous(employeeEvent); //Invoke kafka producer synchronously - message to be published
        // successfully and then return the response

        empEventProducer.sendEmployeeEventUsingProducerRecord(employeeEvent); // Invoke kafka by using kafka producer
        log.info("after emp event");

        return ResponseEntity.status(HttpStatus.CREATED).body(employeeEvent);
    }
}
