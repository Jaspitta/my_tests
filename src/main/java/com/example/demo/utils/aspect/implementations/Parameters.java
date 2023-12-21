package com.example.demo.utils.aspect.implementations;

import com.example.demo.utils.aspect.AnnotationLoggedInterface;
import com.example.demo.utils.aspect.annotations.LogParameters;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Log4j2
public class Parameters {

    private AnnotationLoggedInterface annotationLoggedInterface = new AnnotationLoggedInterface<LogParameters>() {

        @Override
        public void pointcut(LogParameters annotation) {}

        @Override
        public void adviceBefore(LogParameters annotation) {}

        @Override
        public void adviceAfter(LogParameters annotation) {}

        @Override
        public Object adviceAround(ProceedingJoinPoint point, LogParameters annotationMethods, LogParameters annotationClasses) {
            LogParameters annotation = getDefinitiveAnnotation(annotationMethods, annotationClasses);

            sendMessageToLogFromAnnotationLevel(
                new StringBuilder("Method ")
                    .append(point.getSignature().getName())
                    .append(" reached with inputs: ")
                    .append(
                        point != null || point.getArgs() != null || point.getArgs().length > 0
                            ? Arrays.stream(point.getArgs()).parallel().map(arg -> String.valueOf(arg)).collect(Collectors.toList())
                            : "No args detected"
                    )
                    .toString()
                , annotation.level()
                , log
            );

            Object result = proceedSafe(point);

            sendMessageToLogFromAnnotationLevel(
                new StringBuilder("Method ")
                    .append(point.getSignature().getName())
                    .append(" exited with output: ")
                    .append(result)
                    .toString()
                , annotation.level()
                , log
            );

            return result;
        }
    };

    @Around("(@annotation(logParameters)" +
            "&& !staticinitialization(*)" +
            "&& !initialization(* .new(..)))" )
    public Object methodsAdvice(ProceedingJoinPoint point, LogParameters logParameters) {
        return adviceComposed(
                point
                , logParameters
                , null
        );
    }

    public @Around("(@within(com.example.demo.utils.aspect.annotations.LogParameters) " +
            "&& execution(* *(..)) " +
            "&& !staticinitialization(*)" +
            "&& !initialization(* .new(..))" +
            "&& @this(logParameters))")
    Object classesAdvice(ProceedingJoinPoint point, LogParameters logParameters) {
        return adviceComposed(
                point
                , null
                , logParameters
        );
    }

    Object adviceComposed(ProceedingJoinPoint point, LogParameters logMethod, LogParameters logClass){
        return annotationLoggedInterface.adviceAround(point, logMethod, logClass);
    }

}
