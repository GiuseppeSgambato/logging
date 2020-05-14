package it.giuseppesgambato.logging.extensions.annotations.external.aspect;

import it.giuseppesgambato.logging.extensions.commons.TipologiaAttributoLogEnum;
import it.giuseppesgambato.logging.extensions.commons.TipologiaRecordTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public abstract class ExternalLogger {

    public Object logExternalCall(ProceedingJoinPoint joinPoint, String producer, String consumer) throws Throwable {

        Map<String, String> contextMapCopy = MDC.getCopyOfContextMap();

        if(Objects.isNull(contextMapCopy) ) contextMapCopy = new HashMap<>();

        MDC.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), TipologiaRecordTypeEnum.EXTERNAL.getValue());
        MDC.put(TipologiaAttributoLogEnum.PRODUCER.name(), producer);
        MDC.put(TipologiaAttributoLogEnum.CONSUMER.name(), consumer);

        externalCallLogStart(joinPoint.getSignature().getName());

        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        }
        catch (Exception ex){
            MDC.put(TipologiaAttributoLogEnum.ERRORCODE.name(), "1");
            MDC.put(TipologiaAttributoLogEnum.ERRORMESSAGE.name(), ex.getMessage());
            throw ex;
        }
        finally {
            long end = System.currentTimeMillis();
            MDC.put(TipologiaAttributoLogEnum.TIME.name(), String.valueOf(end - start));
            MDC.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), TipologiaRecordTypeEnum.SUMMARY.getValue());
            externalCallLogEnd(joinPoint.getSignature().getName());
            //restore map values
            MDC.setContextMap(contextMapCopy);
        }

    }

    public abstract void externalCallLogStart(String operationName);
    public abstract void externalCallLogEnd(String operationName);

}
