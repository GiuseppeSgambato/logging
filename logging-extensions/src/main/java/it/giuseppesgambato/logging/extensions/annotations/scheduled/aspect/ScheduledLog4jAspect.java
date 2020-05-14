package it.giuseppesgambato.logging.extensions.annotations.scheduled.aspect;

import it.giuseppesgambato.logging.extensions.annotations.scheduled.Log4jScheduled;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScheduledLog4jAspect extends ScheduledLogger {

    private Logger LOGGER;

    @Around("@annotation(annotation)")
    public Object logScheduledProcess(ProceedingJoinPoint joinPoint, Log4jScheduled annotation) throws Throwable {
        LOGGER = LogManager.getLogger(joinPoint.getSignature().getDeclaringTypeName());
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
