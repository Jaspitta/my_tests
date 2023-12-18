package com.example.demo.utils.customMessageValidator;

import com.example.demo.FooToValidate;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class CustomValidationMessageController {

  @PostMapping(value = "/customMessageValidation")
  ResponseEntity<?> testErrorMessage(@RequestBody FooToValidate foo){
    System.out.println("This is the foo received " + foo);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleError(MethodArgumentNotValidException ex){
    var errors = new ArrayList<>(ex.getAllErrors().size());
    ex.getAllErrors().forEach((err) ->
      errors.add(err.getDefaultMessage())
    );

    return new ResponseEntity<>(String.valueOf(errors), HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(InvalidFormatException.class)
  public ResponseEntity<String> handleErrorInvalidFormat(InvalidFormatException ex){
    return new ResponseEntity<>(ex.getOriginalMessage(), HttpStatus.BAD_REQUEST);
  }


}
