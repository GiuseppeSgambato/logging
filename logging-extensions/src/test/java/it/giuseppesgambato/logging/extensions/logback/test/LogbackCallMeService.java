package it.giuseppesgambato.logging.extensions.logback.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackCallMeService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackCallMeService.class.getName());

    public void callMe() throws Exception
    {
        try
        {
            LOGGER.info("Prova TEST ");
            LOGGER.info("A sample message");
            if (true) {
                throw new Exception("An exception");
            }
        }
        catch (Exception ex)
        {
            LOGGER.error(ex.getMessage(),ex);
            throw ex;
        }
    }
}
