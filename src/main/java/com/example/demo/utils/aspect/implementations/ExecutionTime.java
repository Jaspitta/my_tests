package com.example.demo.utils.aspect.implementations;

import com.example.demo.utils.aspect.AnnotationLoggedInterface;
import com.example.demo.utils.aspect.annotations.LogExecutionTime;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
@Log4j2
public class ExecutionTime{

    private AnnotationLoggedInterface annotationLoggedInterface = new AnnotationLoggedInterface<LogExecutionTime>() {


        @Override
        public void pointcut(LogExecutionTime annotation) {}

        @Override
        public void adviceBefore(LogExecutionTime annotation) {}

        @Override
        public void adviceAfter(LogExecutionTime annotation) {}

        @Override
        public Object adviceAround(ProceedingJoinPoint point, LogExecutionTime logExecutionTimeMethod, LogExecutionTime logExecutionTimeClass) {
            LogExecutionTime annotation = getDefinitiveAnnotation(logExecutionTimeMethod, logExecutionTimeClass);
            long start = System.currentTimeMillis();

            Object result = proceedSafe(point);

            annotationLoggedInterface.sendMessageToLogFromAnnotationLevel(
                    new StringBuilder(point.getSignature().getName() + "()")
                            .append(" executed in ")
                            .append(System.currentTimeMillis() - start)
                            .append("ms")
                            .toString()
                    , annotation.level()
                    , log
            );
            return result;
        }
    };

    @Around("(@annotation(logExecutionTime)" +
            "&& !staticinitialization(*)" +
            "&& !initialization(* .new(..)))" )
    public Object methodsAdvice(ProceedingJoinPoint point, LogExecutionTime logExecutionTime) {
        return adviceComposed(
                point
                , logExecutionTime
                , null
        );
    }

    public @Around("(@within(com.example.demo.utils.aspect.annotations.LogExecutionTime) " +
            "&& execution(* *(..)) " +
            "&& !staticinitialization(*)" +
            "&& !initialization(* .new(..))" +
            "&& @this(logExecutionTime))")
    Object classesAdvice(ProceedingJoinPoint point, LogExecutionTime logExecutionTime) {
        return adviceComposed(
            point
            , null
            , logExecutionTime
        );
    }

    Object adviceComposed(ProceedingJoinPoint point, LogExecutionTime logMethod, LogExecutionTime logClass){
        return annotationLoggedInterface.adviceAround(point, logMethod, logClass);
    }

}
