package com.example.demo.utils.aspect.implementations;

import com.example.demo.utils.aspect.AnnotationLoggedInterface;
import com.example.demo.utils.aspect.annotations.LogBeginEnd;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;


@Aspect
@Log4j2
public class BeginEnd {

    AnnotationLoggedInterface annotationLoggedInterface = new AnnotationLoggedInterface<LogBeginEnd>() {
        @Override
        public void pointcut(LogBeginEnd annotation) {}

        @Override
        public void adviceBefore(LogBeginEnd annotation) {}

        @Override
        public void adviceAfter(LogBeginEnd annotation) {}

        @Override
        public Object adviceAround(ProceedingJoinPoint point, LogBeginEnd annotationMethods, LogBeginEnd annotationClasses) {
            LogBeginEnd annotation = getDefinitiveAnnotation(annotationMethods, annotationClasses);

            sendMessageToLogFromAnnotationLevel(
                new StringBuilder("STARTED Method: ")
                    .append(point.getSignature().getName())
                    .toString()
                , annotation.level()
                , log
            );

            Object result = proceedSafe(point);

            sendMessageToLogFromAnnotationLevel(
                new StringBuilder("END Method: ")
                    .append(point.getSignature().getName())
                    .toString()
                , annotation.level()
                , log
            );

            return result;
        }
    };

    @Around("(@annotation(logBeginEnd)" +
            "&& !staticinitialization(*)" +
            "&& !initialization(* .new(..)))" )
    public Object methodsAdvice(ProceedingJoinPoint point, LogBeginEnd logBeginEnd) {
        return adviceComposed(
                point
                , logBeginEnd
                , null
        );
    }

    public @Around("(@within(com.example.demo.utils.aspect.annotations.LogBeginEnd) " +
            "&& execution(* *(..)) " +
            "&& !staticinitialization(*)" +
            "&& !initialization(* .new(..))" +
            "&& @this(logBeginEnd))")
    Object classesAdvice(ProceedingJoinPoint point, LogBeginEnd logBeginEnd) {
        return adviceComposed(
                point
                , null
                , logBeginEnd
        );
    }

    Object adviceComposed(ProceedingJoinPoint point, LogBeginEnd logMethod, LogBeginEnd logClass){
        return annotationLoggedInterface.adviceAround(point, logMethod, logClass);
    }

}
