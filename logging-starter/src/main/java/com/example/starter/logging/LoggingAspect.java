package com.example.starter.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    private final LoggingProperties properties;

    public LoggingAspect(LoggingProperties properties) {
        this.properties = properties;
    }

    @Around("@annotation(com.example.starter.logging.Logginable)")
    public Object logMethodMetadata(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long last = System.currentTimeMillis();

        log.atLevel(Level.valueOf(properties.getLevel()))
                .setMessage("\nMethod '{}' \nwith arguments {} \nwas executed in {} millis \nand returned '{}'")
                .addArgument(pjp.getSignature().getName())
                .addArgument(Arrays.toString(pjp.getArgs()))
                .addArgument(last - start)
                .addArgument(result)
                .log();

        return result;
    }
}
