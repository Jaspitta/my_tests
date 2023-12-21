package com.example.demo.utils.aspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
@Logged(
        executionTime = false,
        inputArgs = true,
        outputArgs = true,
        exception = false,
        begin = false,
        end = false
)
public @interface LogParameters {

    int level() default 400; // info

}
