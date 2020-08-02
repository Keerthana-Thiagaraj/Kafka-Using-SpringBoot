package com.kafkaExample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Employee {

    @PostMapping("/kafka/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
}
