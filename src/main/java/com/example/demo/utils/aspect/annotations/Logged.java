package com.example.demo.utils.aspect.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Base annotation for logging with AOP
 * This annotation should be used only when ALL the information wants to be logged
 *
 * If you want to log only some create another annotation with level field,
 * as a best practice the child annotation should be annotated with the corresponding
 * @Logged configuration kinda like a child class (inheritance is not possible with annotations)
 */
@Target({ ElementType.METHOD, ElementType.TYPE, ElementType.CONSTRUCTOR })
@Retention(RetentionPolicy.RUNTIME)
public @interface Logged {

    int level() default 400; // info

    boolean executionTime() default true;

    boolean inputArgs() default true;

    boolean outputArgs() default true;

    boolean exception() default true;

    boolean begin() default true;

    boolean end() default true;

}