package com.example.demo.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintViolation;
import java.util.stream.Collectors;

@Data
@Builder
@Log4j2
public class ValidationAnnotations {

    String objStn;
    Class clazz;

    public void testValidation() throws JsonProcessingException {

        var res = new ObjectMapper().readValue(objStn, clazz);

        var messages = javax.validation.Validation.buildDefaultValidatorFactory()
                .getValidator().validate(res)
                .stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());

        log.info(res);
        log.info(messages);

    }


}
