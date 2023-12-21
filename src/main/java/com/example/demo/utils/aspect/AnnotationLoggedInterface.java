package com.example.demo.utils.aspect;

import com.example.demo.utils.FunctionalExtensions;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.spi.StandardLevel;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * @author Scarpitta Stefano
 *
 * @Param <A extends Annotation> annotation class to log for
 * The given annotation must have a level(); field and must be
 * annotated with @Loggable and the proper conf
 *
 */

public interface AnnotationLoggedInterface <A extends Annotation>{

    int bufferLimit = 2048;

    public void pointcut(A annotation);

    void adviceBefore(A annotation);

    void adviceAfter(A annotation);

    public Object adviceAround(ProceedingJoinPoint point, A annotationMethods, A annotationClasses);

    default A getDefinitiveAnnotation(A annotationMethods, A annotationClasses){
        return annotationMethods != null ? annotationMethods : annotationClasses;
    }

    /**
     * Utility method to send message on a given level,
     * the level should be taken from the annotation*/
    default void sendMessageToLogFromAnnotationLevel(String message, int level, Logger log){

        message = message.length() > bufferLimit
                ? new StringBuilder(message.substring(0, bufferLimit))
                    .append("--- TRUNCATED")
                    .toString()
                : message;

        log.atLevel(
                Level.getLevel(
                        StandardLevel.getStandardLevel(level).name()
                )
        ).log(message);
    }

    default Object proceedSafe(ProceedingJoinPoint point){
        return FunctionalExtensions.runSafeAnyway(
                p -> p.proceed()
                , point
                , () -> Optional.ofNullable(new Object())
                , true
                , new StringBuilder("Unexpected Exception in method: ")
                        .append(point.getSignature())
                        .append(" : \n")
                        .toString()
        );
    }

}
