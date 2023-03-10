package org.common.utilities.Aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    
    @Around("execution(* org.kafka.producer.Controller*.*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Entering method: {}() with arguments: {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        logger.info("Exiting method: {}() with result: {}", joinPoint.getSignature().getName(), result);
        return result;
    }
}