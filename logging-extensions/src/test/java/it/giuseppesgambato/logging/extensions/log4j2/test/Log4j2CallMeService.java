package it.giuseppesgambato.logging.extensions.log4j2.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2CallMeService
{
    private static final Logger LOGGER = LogManager.getLogger(Log4j2CallMeService.class.getName());

    public void callMe() throws Exception
    {
        try
        {
            LOGGER.info("Prova TEST ");
            LOGGER.info("A sample message");
            if (true) {
                throw new IllegalAccessException("An exception");
            }
        }
        catch (Exception ex)
        {
            LOGGER.error(ex.getMessage(),ex);
            throw ex;
        }
    }
}
