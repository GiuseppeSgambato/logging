package it.giuseppesgambato.logging.extensions.layouts.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import it.giuseppesgambato.logging.extensions.commons.LogGenerator;
import it.giuseppesgambato.logging.extensions.commons.TipologiaAttributoLogEnum;
import it.giuseppesgambato.logging.extensions.layouts.ITextLayout;
import org.slf4j.MDC;



public class LogbackTextLayout extends LayoutBase<ILoggingEvent> implements ITextLayout {

    @Override
    public String doLayout(ILoggingEvent logEvent)
    {
        String log = toText(logEvent.getTimeStamp(),
                            logEvent.getLevel().toString(),
                            MDC.get(TipologiaAttributoLogEnum.RECORDTYPE.name()),
                            MDC.get(TipologiaAttributoLogEnum.SID.name()),
                            MDC.get(TipologiaAttributoLogEnum.BID.name()),
                            MDC.get(TipologiaAttributoLogEnum.TID.name()),
                            MDC.get(TipologiaAttributoLogEnum.KEY.name()),
                            MDC.get(TipologiaAttributoLogEnum.PID.name()),
                            logEvent.getThreadName(),
                            MDC.get(TipologiaAttributoLogEnum.SYSTEM.name()),
                            LogGenerator.getCallerMethodNameForLogback(logEvent),
                            logEvent.getLoggerName(),
                            MDC.get(TipologiaAttributoLogEnum.PRODUCER.name()),
                            MDC.get(TipologiaAttributoLogEnum.CONSUMER.name()),
                            MDC.get(TipologiaAttributoLogEnum.RESULTCODE.name()),
                            MDC.get(TipologiaAttributoLogEnum.ERRORCODE.name()),
                            MDC.get(TipologiaAttributoLogEnum.ERRORMESSAGE.name()),
                            MDC.get(TipologiaAttributoLogEnum.TIME.name()),
                            MDC.get(TipologiaAttributoLogEnum.EXTRADATA.name()),
                            logEvent.getFormattedMessage(),
                            LogGenerator.getStackTraceForLogcback(logEvent));
        return log;
    }

    @Override
    public String toText(long timeStamp,
                         String level,
                         String recordType,
                         String sid,
                         String bid,
                         String tid,
                         String key,
                         String pid,
                         String threadName,
                         String system,
                         String methodName,
                         String loggerName,
                         String producer,
                         String consumer,
                         String resultCode,
                         String errorCode,
                         String errorMessage,
                         String time,
                         String extraData,
                         String message,
                         String eccezione)
    {
        String log = LogGenerator.toText(timeStamp,
                                        level,
                                        recordType,
                                        sid,
                                        bid,
                                        tid,
                                        key,
                                        pid,
                                        threadName,
                                        system,
                                        methodName,
                                        loggerName,
                                        producer,
                                        consumer,
                                        resultCode,
                                        errorCode,
                                        errorMessage,
                                        time,
                                        extraData,
                                        message,
                                        eccezione);
        return log;
    }

}
