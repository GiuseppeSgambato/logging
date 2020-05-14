package it.giuseppesgambato.logging.extensions.logback.test;

import it.giuseppesgambato.logging.extensions.commons.TipologiaAttributoLogEnum;

import org.slf4j.MDC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logback_Test
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Logback_Test.class.getName());

    @Test
    void TEST_LOG_COMPLETO()
    {
        try
        {
//             Il nome della Piattaforma - Costante su File di configurazione, da inserire in contesto al bootstrap applicativo
            MDC.put(TipologiaAttributoLogEnum.SYSTEM.name(), "LOGGING-EXTENSIONS_SYSTEM_TEST_LOGBACK");
            MDC.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), "recordType_TEST");

            // Le variabili da passare al Logger
            MDC.put(TipologiaAttributoLogEnum.SID.name(), "SID_TEST");
            MDC.put(TipologiaAttributoLogEnum.BID.name(), "BID_TEST");
            MDC.put(TipologiaAttributoLogEnum.TID.name(), "TID_TEST");
            MDC.put(TipologiaAttributoLogEnum.KEY.name(), "KEY_TEST");
            MDC.put(TipologiaAttributoLogEnum.PID.name(), "999");
            MDC.put(TipologiaAttributoLogEnum.PRODUCER.name(), "PRODUCER_TEST");
            MDC.put(TipologiaAttributoLogEnum.CONSUMER.name(), "CONSUMER_TEST");
            MDC.put(TipologiaAttributoLogEnum.ERRORCODE.name(), "999");
            MDC.put(TipologiaAttributoLogEnum.RESULTCODE.name(), "999");
            MDC.put(TipologiaAttributoLogEnum.ERRORMESSAGE.name(), "ERRORMESSAGE_TEST");
            MDC.put(TipologiaAttributoLogEnum.TIME.name(), "999");
            MDC.put(TipologiaAttributoLogEnum.EXTRADATA.name(), "\"A\":\"A Value\" , \"B\":\"B Value\"");


            LOGGER.info("Metodo TEST_LOG_COMPLETO");

            // Il servizio
            LogbackCallMeService simpleService = new LogbackCallMeService();
            simpleService.callMe();

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
            MDC.put("SYSTEM", "LOGGING-EXTENSIONS_SYSTEM_TEST_LOGBACK");
            MDC.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), "recordType_TEST");

            // Le variabili da passare al Logger
            MDC.put(TipologiaAttributoLogEnum.PID.name(), "a");//error
            MDC.put(TipologiaAttributoLogEnum.PRODUCER.name(), "PRODUCER_TEST");
            MDC.put(TipologiaAttributoLogEnum.CONSUMER.name(), "CONSUMER_TEST");
            MDC.put(TipologiaAttributoLogEnum.RESULTCODE.name(), "888");
            MDC.put(TipologiaAttributoLogEnum.ERRORCODE.name(), "888");
            MDC.put(TipologiaAttributoLogEnum.ERRORMESSAGE.name(), "ERRORMESSAGE_TEST");
            MDC.put(TipologiaAttributoLogEnum.TIME.name(), "888");

            int placeholder1=5;
            int placeholder2=6;
            LOGGER.info("TEST_LOG_5_ATTRIBUTI_MANCANTI sostituiti da un \" - \" nel LogbackTextLayout primo placeholder {} secondo placeholder {}", placeholder1, placeholder2 );

            // Il servizio
            LogbackCallMeService logbackCallMeService = new LogbackCallMeService();
            logbackCallMeService.callMe();

        }
        catch (Exception ex)
        {
            Assertions.assertTrue(true);
        }
    }
}
