package it.giuseppesgambato.logging.extensions.annotations.external.aspect;

import it.giuseppesgambato.logging.extensions.annotations.external.Log4jExternal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExternalLog4jAspect extends ExternalLogger {

    private Logger LOGGER;

    @Around("@annotation(annotation)")
    public Object logExternalCall(ProceedingJoinPoint joinPoint, Log4jExternal annotation) throws Throwable {
        LOGGER = LogManager.getLogger(joinPoint.getSignature().getDeclaringTypeName());
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
