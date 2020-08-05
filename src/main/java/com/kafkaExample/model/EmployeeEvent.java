package com.kafkaExample.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class EmployeeEvent {

    public Integer empEventId;
    private String empEventName;

    @NotNull
    @Valid
    private EmployeeModel employeeModel;

    public Integer getEmpEventId() {
        return empEventId;
    }
}
