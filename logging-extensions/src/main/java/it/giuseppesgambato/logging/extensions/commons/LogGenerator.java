package it.giuseppesgambato.logging.extensions.commons;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.LogEvent;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.sql.Timestamp;


@SuppressWarnings("ALL")
public class LogGenerator {

    public static String toText(long timeStamp,
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
                                String eccezione){

        String nowAsISO8601DateTime = DateTime.now().toString(DateTimeFormat.forPattern(Constants.DATE_TIME_ISO8601_FORMAT));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(nowAsISO8601DateTime); //ISO8601 YYYY-MM-dd'T'HH:mm:ss.SSSZ
        Timestamp timestamp = new Timestamp(timeStamp);
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + timestamp.getTime()/1000 + "." +timestamp.getNanos()); // UNIX epoch ms
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(level, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(recordType, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(sid, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(bid, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(tid, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(key, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(pid, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(threadName, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(system, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(methodName, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(loggerName, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(producer, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(consumer, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(resultCode, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(errorCode, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(errorMessage, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(time, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));

        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(elaborazioneExtradata(extraData,eccezione), Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(Constants.SPACE_BETWEEN_ATTRIBUTES + (StringUtils.defaultIfBlank(message, Constants.DASH_IF_ATTRIBUTE_IS_EMPTY)));
        stringBuilder.append(System.lineSeparator());

        return stringBuilder.toString();
    }

    public static String toJson(Long timeStamp,
                                String level,
                                String recordType,
                                String sid,
                                String bid,
                                String tid,
                                String key,
                                Long pid,
                                String threadName,
                                String system,
                                String methodName,
                                String loggerName,
                                String producer,
                                String consumer,
                                Long resultCode,
                                Long errorCode,
                                String errorMessage,
                                Long time,
                                String extraData,
                                String message,
                                String eccezione){

        JsonObject jsonLog = new JsonObject();
        Gson gsonLog = new GsonBuilder().serializeNulls().create();
        String nowAsISO8601DateTime = DateTime.now().toString(DateTimeFormat.forPattern(Constants.DATE_TIME_ISO8601_FORMAT));
        Timestamp timestamp = new Timestamp(timeStamp);
        jsonLog.addProperty(TipologiaAttributoLogEnum.DATETIME.getDescrizione(), nowAsISO8601DateTime );
        jsonLog.addProperty(TipologiaAttributoLogEnum.TIMESTAMP.getDescrizione(),timestamp.getTime()/1000 + "." +timestamp.getNanos());
        jsonLog.addProperty(TipologiaAttributoLogEnum.LOGLEVEL.getDescrizione(), (StringUtils.defaultIfBlank(level, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.RECORDTYPE.getDescrizione(), (StringUtils.defaultIfBlank(recordType, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.SID.getDescrizione(),  (StringUtils.defaultIfBlank(sid, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.BID.getDescrizione(),  (StringUtils.defaultIfBlank(bid, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.TID.getDescrizione(),  (StringUtils.defaultIfBlank(tid, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.KEY.getDescrizione(),  (StringUtils.defaultIfBlank(key, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.PID.getDescrizione(), pid);
        jsonLog.addProperty(TipologiaAttributoLogEnum.THREADNAME.getDescrizione(), (StringUtils.defaultIfBlank(threadName, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.SYSTEM.getDescrizione(), (StringUtils.defaultIfBlank(system, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.PROCESSNAME.getDescrizione(), (StringUtils.defaultIfBlank(methodName, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.LOGGERNAME.getDescrizione(), (StringUtils.defaultIfBlank(loggerName, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.PRODUCER.getDescrizione(), (StringUtils.defaultIfBlank(producer, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.CONSUMER.getDescrizione(), (StringUtils.defaultIfBlank(consumer, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.RESULTCODE.getDescrizione(), resultCode);
        jsonLog.addProperty(TipologiaAttributoLogEnum.ERRORCODE.getDescrizione(), errorCode);
        jsonLog.addProperty(TipologiaAttributoLogEnum.ERRORMESSAGE.getDescrizione(), (StringUtils.defaultIfBlank(errorMessage, null)));
        jsonLog.addProperty(TipologiaAttributoLogEnum.TIME.getDescrizione(), time);

        jsonLog.addProperty(TipologiaAttributoLogEnum.EXTRADATA.getDescrizione(), StringUtils.defaultIfBlank(elaborazioneExtradata(extraData,eccezione), null));
        jsonLog.addProperty(TipologiaAttributoLogEnum.MESSAGE.getDescrizione(), (StringUtils.defaultIfBlank(message, null)));

        return gsonLog.toJson(jsonLog) + System.lineSeparator();
    }

    private static String elaborazioneExtradata(String extraData,String stackTrace){

        StringBuilder stringBuilder = new StringBuilder();
        if (!StringUtils.isNotBlank(stackTrace)) {
            if (StringUtils.isNotBlank(extraData)) {
                extraData.replace("\\\"", "\\\'");
                stringBuilder.append("[");
                stringBuilder.append(extraData);
                stringBuilder.append("]");
            }
        }else
        {
            if (StringUtils.isNotBlank(extraData)) {
                stringBuilder.append("[");
                stringBuilder.append(extraData);
                stringBuilder.append(", \"stackTrace\":\"");
                stringBuilder.append(stackTrace + "\"]");
            }else {
                stringBuilder.append("[");
                stringBuilder.append("\"stackTrace\":\"");
                stringBuilder.append(stackTrace + "\"]");
            }
        }
        return stringBuilder.toString();
    }


    public static String getCallerMethodNameForLogback(ILoggingEvent logEvent)
    {
        String result = Constants.DASH_IF_ATTRIBUTE_IS_EMPTY;
        StackTraceElement[] stackTraceElements = logEvent.getCallerData();
        if (stackTraceElements != null && stackTraceElements.length > 0) {
            result = stackTraceElements[0].getMethodName();
        }
        return result;
    }

    public static String getStackTraceForLogcback(ILoggingEvent logEvent)
    {
        StringBuilder result = new StringBuilder();
        if(logEvent.getThrowableProxy() != null) {
            result.append(logEvent.getThrowableProxy().getClassName() + Constants.SPACE_BETWEEN_ATTRIBUTES );
            StackTraceElementProxy[] eccezioneMessage = logEvent.getThrowableProxy().getStackTraceElementProxyArray();
            for (StackTraceElementProxy ex: eccezioneMessage){
                result.append(ex.toString() + Constants.SPACE_BETWEEN_ATTRIBUTES);
            }
        }
        return result.toString();
    }

    public static String getStackTraceForLog4j2(LogEvent logEvent)
    {
        StringBuilder result = new StringBuilder();
        if(logEvent.getThrown()!= null){
            result.append(logEvent.getThrown().getClass().getCanonicalName() + Constants.SPACE_BETWEEN_ATTRIBUTES);
            StackTraceElement[] eccezioneMessage = logEvent.getThrown().getStackTrace();
            for (StackTraceElement ex: eccezioneMessage){
                result.append(ex.toString() + Constants.SPACE_BETWEEN_ATTRIBUTES);
            }
        }
        return result.toString();
    }

    public static Long converterStringInLong(String attributo) {

        if (StringUtils.isNotBlank(attributo) && StringUtils.isNumeric(attributo.trim())) {
            return StringUtils.isNotBlank(attributo.trim()) ?
                    Long.valueOf(attributo.trim()) : null;
        }
        else{
            return null;
        }
    }
}