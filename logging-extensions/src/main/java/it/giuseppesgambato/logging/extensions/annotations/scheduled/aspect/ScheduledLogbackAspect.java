package it.giuseppesgambato.logging.extensions.annotations.scheduled.aspect;

import it.giuseppesgambato.logging.extensions.annotations.scheduled.LogbackScheduled;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScheduledLogbackAspect extends ScheduledLogger {

    private Logger LOGGER;

    @Around("@annotation(annotation)")
    public Object logScheduledProcess(ProceedingJoinPoint joinPoint, LogbackScheduled annotation) throws Throwable {
        LOGGER = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        return logScheduledProcess(joinPoint, annotation.system());
    }

    @Override
    public void scheduledLogStart(String operationName) {
        LOGGER.info("SCHEDULED PROCESS {} STARTED", operationName);
    }

    @Override
    public void scheduledLogEnd(String operationName) {
        LOGGER.info("SCHEDULED PROCESS {} FINISHED", operationName);
    }

}
