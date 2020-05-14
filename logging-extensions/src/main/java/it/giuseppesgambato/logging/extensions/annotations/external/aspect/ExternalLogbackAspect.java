package it.giuseppesgambato.logging.extensions.annotations.external.aspect;

import it.giuseppesgambato.logging.extensions.annotations.external.LogbackExternal;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExternalLogbackAspect extends ExternalLogger {

    private Logger LOGGER;

    @Around("@annotation(annotation)")
    public Object logExternalCall(ProceedingJoinPoint joinPoint, LogbackExternal annotation) throws Throwable {
        LOGGER = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringTypeName());
        return logExternalCall(joinPoint, annotation.producer(), annotation.consumer());
    }

    @Override
    public void externalCallLogStart(String operationName) {
        LOGGER.info("EXTERNAL CALL {} STARTED", operationName);
    }

    @Override
    public void externalCallLogEnd(String operationName) {
        LOGGER.info("EXTERNAL CALL {} FINISHED", operationName);
    }

}
