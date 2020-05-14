package it.giuseppesgambato.logging.extensions.layouts.log4j;

import it.giuseppesgambato.logging.extensions.commons.LogGenerator;
import it.giuseppesgambato.logging.extensions.commons.TipologiaAttributoLogEnum;
import it.giuseppesgambato.logging.extensions.layouts.ITextLayout;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;

import java.nio.charset.Charset;



@Plugin(name = "Log4j2TextLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE)
public class Log4j2TextLayout extends AbstractStringLayout implements ITextLayout {
    private final String newFieldName;

    public Log4j2TextLayout(Configuration config, Charset aCharset, String newFieldName) {
        super(config, aCharset, null, null);
        this.newFieldName = newFieldName;
    }

    @PluginFactory
    public static Log4j2TextLayout createLayout(@PluginConfiguration final Configuration config,
                                                @PluginAttribute(value = "charset", defaultString = "US-ASCII") final Charset charset,
                                                @PluginAttribute(value = "newFieldName") final String newFieldName) {
        return new Log4j2TextLayout(config, charset, newFieldName);
    }

    @Override
    public String toSerializable(LogEvent logEvent)
    {
        // Genero il log in formato Testo Strutturato
        String log = toText(logEvent.getTimeMillis(),
                            logEvent.getLevel().toString(),
                            ThreadContext.get(TipologiaAttributoLogEnum.RECORDTYPE.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.SID.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.BID.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.TID.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.KEY.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.PID.name()),
                            logEvent.getThreadName(),
                            ThreadContext.get(TipologiaAttributoLogEnum.SYSTEM.name()),
                            logEvent.getSource().getMethodName(),
                            logEvent.getLoggerName(),
                            ThreadContext.get(TipologiaAttributoLogEnum.PRODUCER.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.CONSUMER.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.RESULTCODE.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.ERRORCODE.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.ERRORMESSAGE.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.TIME.name()),
                            ThreadContext.get(TipologiaAttributoLogEnum.EXTRADATA.name()),
                            logEvent.getMessage().getFormattedMessage(),
                            LogGenerator.getStackTraceForLog4j2(logEvent));
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
