package com.kafkaExample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafkaExample.Producer.EmpEventProducer;
import com.kafkaExample.model.EmployeeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Employee {

    @Autowired
    EmpEventProducer empEventProducer;

    @PostMapping("/kafka/createEmployee")
    public ResponseEntity<EmployeeEvent> createEmployee(@RequestBody EmployeeEvent employeeEvent) throws JsonProcessingException {

        empEventProducer.sendEmployeeEvent(employeeEvent); //Invoke kafka producer
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeEvent);
    }
}
