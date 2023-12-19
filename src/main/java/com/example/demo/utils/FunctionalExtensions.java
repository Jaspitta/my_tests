package com.example.demo.utils;

import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Log4j2
public abstract class FunctionalExtensions {
  public static <T> Optional<T> supplySafe(Callable<T> supplier) {
    try {
      return Optional.ofNullable(supplier.call());
    } catch (Exception e) {
      //log.warn("supply safe consumed an error", e.getCause());
      return Optional.empty();
    }
  }

  public static void runSafe(Runnable runnable, String what) {
    try {
      runnable.run();
    } catch (Exception e) {
      //log.warn("{} failed due to {}", what, e.getMessage());
    }
  }

  /**
   *
   * Use this method if you want to execute a function that should return a valid response
   * even if an exception is thrown, regardless from the exception type
   *
   * @param caller method to execute
   * @param input input of caller method
   * @param supplier method to provide response in case of any kind of exception
   * @param logEnabled if you want to log the exception
   * @param <T> input type
   * @param <R> output type
   * @return
   */
  public static <T, R> R runSafeAnyway(FunctionThrowable<T, R> caller, T input, Supplier<R> supplier, boolean logEnabled, String errorMessage){
    try {
      return caller.apply(input);
    } catch (Throwable ex){
      if(logEnabled){
        log.error(errorMessage, ex);
      }
      return supplier.get();
    }
  }

  public static <T> void runSafeAnyway(Consumer<T> caller, T input, boolean logEnabled, String errorMessage){
    try {
      caller.accept(input);
    } catch (Throwable ex){
      if(logEnabled){
        log.error(errorMessage, ex);
      }
    }
  }

  /**
   *
   * 	Use this method if you want to execute a function that should return a valid response
   * 	even if an exception is thrown, based on the exception type handler
   *
   * @param caller method to execute
   * @param input input of caller method
   * @param errorSafeHandler method that provide different response base on exception type
   * @param logEnabled if you want to log the exception
   * @param <T> input type
   * @param <R> output type
   * @return
   */
  public static <T, R> R runSafeWithErrorHandler(FunctionThrowable<T, R> caller, T input, Function<Throwable, R> errorSafeHandler, boolean logEnabled){
    try {
      return caller.apply(input);
    } catch (Throwable ex){
      if(logEnabled){
        log.error(ex);
      }
      return errorSafeHandler.apply(ex);
    }
  }

  /**
   *
   * @param keyExtractor method to extract value to recognize the identity between two items
   * @return
   * @param <T>
   */
  public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }
}
