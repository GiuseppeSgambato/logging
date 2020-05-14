package it.giuseppesgambato.logging.extensions.commons;

public enum TipologiaAttributoLogEnum {
    DATETIME("dateTime"),
    TIMESTAMP("timeStamp"),
    LOGLEVEL("logLevel"),
    RECORDTYPE("recordType"), // I - Internal record che descrive un evento interprocesso
                // E - External record che descrive una comunicazione con un sistema/modulo esterno
                // S - Summary record che descrive il risultato di una operazione interna/esterna

    SID("sid"),        // Identificativo sessione
    BID("bid"),        // Identificativo univoco di un intero processo di business
    TID("tid"),        // Identificativo univoco della singola transazione tra un chiamante ed un chiamato
    KEY("key"),        // Chiave di correlazione (può essere ad esempio la login o la mail del cliente, mario.rossi@example.com)
    PID("pid"),        // Identificativo univoco del singolo messaggio inviato su un canale di comunicazione
    THREADNAME("threadName"),
    SYSTEM("system"),     // Applicativo
    PROCESSNAME("processName"), //methodName
    LOGGERNAME("loggerName"),
    PRODUCER("producer"),
    CONSUMER("consumer"),
    RESULTCODE("resultCode"),
    ERRORCODE("errorCode"),
    ERRORMESSAGE("errorMessage"),
    TIME("time"),       // Ms durata comunicazione
    EXTRADATA("extraData"),
    MESSAGE("message");

    String descrizione;
    TipologiaAttributoLogEnum (String descrizione){
        this.descrizione=descrizione;
    }
    public String getDescrizione(){return descrizione;}

}
