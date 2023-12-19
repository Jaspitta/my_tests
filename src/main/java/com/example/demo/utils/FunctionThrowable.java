package com.example.demo.utils;

@FunctionalInterface
public interface FunctionThrowable<T, R> {

    R apply(T t) throws Throwable;
}
