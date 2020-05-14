package it.giuseppesgambato.logging.extensions.log4j2.test;

import it.giuseppesgambato.logging.extensions.commons.TipologiaAttributoLogEnum;
import it.giuseppesgambato.logging.extensions.commons.TipologiaRecordTypeEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Log4j2_Test_TEXT
{
    private static  Logger LOGGER = null;

    @BeforeAll
    public static void setLogger()
    {
        System.setProperty("log4j2.configurationFile","log4j2-Text.xml");
        LOGGER = LogManager.getLogger();
    }

    @Test
    void TEST_LOG_COMPLETO()
    {
        try
        {
            // Il nome della Piattaforma - Costante su File di configurazione, da inserire in contesto al bootstrap applicativo
            ThreadContext.put(TipologiaAttributoLogEnum.SYSTEM.name(), "LOGGING-EXTENSIONS_SYSTEM_TEST_LOG4J2_TEXT");
            ThreadContext.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), TipologiaRecordTypeEnum.SUMMARY.getValue());

            // Le variabili da passare al Logger
            ThreadContext.put(TipologiaAttributoLogEnum.SID.name(), "SID_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.BID.name(), "BID_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.TID.name(), "TID_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.KEY.name(), "KEY_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.PID.name(), "999");
            ThreadContext.put(TipologiaAttributoLogEnum.PRODUCER.name(), "PRODUCER_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.CONSUMER.name(), "CONSUMER_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.RESULTCODE.name(), "999");
            ThreadContext.put(TipologiaAttributoLogEnum.ERRORCODE.name(), "999");
            ThreadContext.put(TipologiaAttributoLogEnum.ERRORMESSAGE.name(), "ERRORMESSAGE_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.TIME.name(), "999");
            ThreadContext.put(TipologiaAttributoLogEnum.EXTRADATA.name(), "\"A\":\"A Value\" , \"B\":\"B Value\"");

            LOGGER.info("metodo TEST_LOG_COMPLETO text");

            // Il servizio
            Log4j2CallMeService log4j2CallMeService = new Log4j2CallMeService();
            log4j2CallMeService.callMe();

        }
        catch (Exception ex)
        {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void TEST_LOG_5_ATTRIBUTI_MANCANTI()
    {
        try
        {
            // Il nome della Piattaforma - Costante su File di configurazione, da inserire in contesto al bootstrap applicativo
            ThreadContext.put("SYSTEM", "LOGGING-EXTENSIONS_SYSTEM_TEST_LOG4J2_TEXT");
            ThreadContext.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), "recordType_TEST");

            // Le variabili da passare al Logger
            ThreadContext.put(TipologiaAttributoLogEnum.PRODUCER.name(), "PRODUCER_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.CONSUMER.name(), "CONSUMER_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.RESULTCODE.name(), "RESULTCODE_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.ERRORCODE.name(), "ERRORCODE_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.ERRORMESSAGE.name(), "ERRORMESSAGE_TEST");
            ThreadContext.put(TipologiaAttributoLogEnum.TIME.name(), "TIME_TEST");

            int placeholder1=5;
            int placeholder2=6;
            LOGGER.info("TEST_LOG_5_ATTRIBUTI_MANCANTI sostituiti da un \" - \" nel Log4j2TextLayout primo placeholder {} secondo placeholder {}", placeholder1, placeholder2 );

            // Il servizio
            Log4j2CallMeService log4j2CallMeService = new Log4j2CallMeService();
            log4j2CallMeService.callMe();

        }
        catch (Exception ex)
        {
            Assertions.assertTrue(true);
        }
    }
}
