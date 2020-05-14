package it.giuseppesgambato.logging.extensions.annotations.scheduled.aspect;

import it.giuseppesgambato.logging.extensions.commons.TipologiaAttributoLogEnum;
import it.giuseppesgambato.logging.extensions.commons.TipologiaRecordTypeEnum;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.MDC;

import java.util.UUID;


public abstract class ScheduledLogger {

    public Object logScheduledProcess(ProceedingJoinPoint joinPoint, String system) throws Throwable {

        MDC.put(TipologiaAttributoLogEnum.SYSTEM.name(), system);
        MDC.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), TipologiaRecordTypeEnum.INTERNAL.getValue());

        MDC.put(TipologiaAttributoLogEnum.PID.name(), RandomStringUtils.randomNumeric(6));
        MDC.put(TipologiaAttributoLogEnum.TID.name(), "sched-" + UUID.randomUUID().toString());

        scheduledLogStart(joinPoint.getSignature().getName());

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
            scheduledLogEnd(joinPoint.getSignature().getName());
            MDC.clear();
        }

    }

    public abstract void scheduledLogStart(String operationName);
    public abstract void scheduledLogEnd(String operationName);

}
