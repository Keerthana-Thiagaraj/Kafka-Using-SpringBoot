package com.kafkaExample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeEvent {

    private Integer empEventId;

    private String empEventName;

    private EmployeeModel employeeModel;
}
