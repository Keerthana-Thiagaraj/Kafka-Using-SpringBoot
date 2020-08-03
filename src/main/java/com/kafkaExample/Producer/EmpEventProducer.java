package com.kafkaExample.Producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafkaExample.model.EmployeeEvent;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@Component
@Slf4j
@Log
@Log4j
public class EmpEventProducer {

    @Autowired
    KafkaTemplate<Integer, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper; //Convert java object to json

    public void sendEmployeeEvent(EmployeeEvent employeeEvent) throws JsonProcessingException {

        Integer key = employeeEvent.getEmpEventId();
        String value = objectMapper.writeValueAsString(employeeEvent);
        ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate.sendDefault(key, value); // sendDefault is of type Listenable future
        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() { // Call back is of success and failure types
            @Override
            public void onFailure(Throwable e) {
                handleFailure(key, value, e);
            }

            @Override
            public void onSuccess(SendResult<Integer, String> result) { // Published message is successful
                handleSuccess(key, value, result);
            }
        });

    }

    private void handleFailure(Integer key, String value, Throwable e) {
        log.error("error while sending the message", e.getMessage());
        try {
            throw e;
        } catch (Throwable throwable) {
            log.error("error in failure", throwable.getMessage());
        }
    }

    private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
        log.info("message sent for the key,"+key+" value"+value+"partion is"+result.getRecordMetadata().partition());
    }

}
