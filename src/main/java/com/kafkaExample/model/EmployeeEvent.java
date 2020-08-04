package com.kafkaExample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class EmployeeEvent {

    public Integer empEventId;

    public Integer getEmpEventId() {
        return empEventId;
    }

    private String empEventName;

    private EmployeeModel employeeModel;
}
