package com.example.demo.tests;

import com.example.demo.utils.aspect.annotations.LogParameters;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Builder
@Log4j2
public class InternalLoggingAnnotations {

    @LogParameters
    public String test(String message) {
        log.info("I am inside the test method with the message: {}", message);
        return message;
    }
}
