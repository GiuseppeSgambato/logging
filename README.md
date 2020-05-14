La libreria implementa due tipi di layout custom, JSON e TESTO STRUTTURATO:
I layout sono disponili per i framework: Log4j2 e Logback.

Per design la libreria include le dipendenze a tali framework. L'eventuale esclusione di quest'ultime è a carico del client:

```sh
<dependency>
    <groupId>it.giuseppesgambato</groupId>
    <artifactId>logging-extensions</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
        </exclusion>
        <exclusion>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </exclusion>
        <exclusion>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

# Guida all'utilizzo dei Layouts

## Logback
Dichiarare, nel file di configurazione logback.xml, il layout desiderato:

### Layout Testo Strutturato
```sh
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="it.giuseppesgambato.logging.extensions.layouts.logback.LogbackTextLayout">
            </layout>
        </encoder>
    </appender>
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```
### Layout JSON
```sh
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.core.encoder.LayoutWrappingEncoder">
            <layout class="it.giuseppesgambato.logging.extensions.layouts.logback.LogbackJsonLayout">
            </layout>
        </encoder>
    </appender>
    <root level="INFO">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

I parametri supportati dai Layout, vengono passati attraverso l'apposito Mapped Diagnostic Context di Logback (MDC): 
```sh
    MDC.put(TipologiaAttributoLogEnum.SYSTEM.name(), "SYSTEM");
    MDC.put(TipologiaAttributoLogEnum.RECORDTYPE.name(), TipologiaRecordTypeEnum.INTERNAL.getValue());
    MDC.put(TipologiaAttributoLogEnum.SID.name(), "SID");
    MDC.put(TipologiaAttributoLogEnum.BID.name(), "BID");
    MDC.put(TipologiaAttributoLogEnum.TID.name(), "TID");
    MDC.put(TipologiaAttributoLogEnum.KEY.name(), "KEY");
    MDC.put(TipologiaAttributoLogEnum.PID.name(), "999");
    MDC.put(TipologiaAttributoLogEnum.PRODUCER.name(), "PRODUCER");
    MDC.put(TipologiaAttributoLogEnum.CONSUMER.name(), "CONSUMER");
    MDC.put(TipologiaAttributoLogEnum.RESULTCODE.name(), "999");
    MDC.put(TipologiaAttributoLogEnum.ERRORCODE.name(), "999");
    MDC.put(TipologiaAttributoLogEnum.ERRORMESSAGE.name(), "ERRORMESSAGE");
    MDC.put(TipologiaAttributoLogEnum.TIME.name(), "999");
    MDC.put(TipologiaAttributoLogEnum.EXTRADATA.name(), "\"A\":\"A Value\" , \"B\":\"B Value\"");

```

## Log4j2
Dichiarare, nel file di configurazione log4j2.xml, il layout desiderato:

### Layout testo strutturato
```sh
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <Log4j2TextLayout>
            </Log4j2TextLayout>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

### Layout JSON
```sh
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <Log4j2JsonLayout>
            </Log4j2JsonLayout>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console" />
        </Root>
    </Loggers>
</Configuration>
```

I parametri supportati dai Layout, vengono passati attraverso l'apposito ThreadContext di log4j2: 
```sh
    ThreadContext.put(TipologiaAttributoLogEnum.SYSTEM.name(), "SYSTEM");
    ThreadContext.put(TipologiaAttributoLogEnum.RECORDTYPE.name(),  TipologiaRecordTypeEnum.EXTERNAL.getValue());
    ThreadContext.put(TipologiaAttributoLogEnum.SID.name(), "SID");
    ThreadContext.put(TipologiaAttributoLogEnum.BID.name(), "BID");
    ThreadContext.put(TipologiaAttributoLogEnum.TID.name(), "TID");
    ThreadContext.put(TipologiaAttributoLogEnum.KEY.name(), "KEY");
    ThreadContext.put(TipologiaAttributoLogEnum.PID.name(), "999");
    ThreadContext.put(TipologiaAttributoLogEnum.PRODUCER.name(), "PRODUCER");
    ThreadContext.put(TipologiaAttributoLogEnum.CONSUMER.name(), "CONSUMER");
    ThreadContext.put(TipologiaAttributoLogEnum.RESULTCODE.name(), "999");
    ThreadContext.put(TipologiaAttributoLogEnum.ERRORCODE.name(), "999");
    ThreadContext.put(TipologiaAttributoLogEnum.ERRORMESSAGE.name(), "ERRORMESSAGE");
    ThreadContext.put(TipologiaAttributoLogEnum.TIME.name(), "999");
    ThreadContext.put(TipologiaAttributoLogEnum.EXTRADATA.name(), "\"A\":\"A Value\" , \"B\":\"B Value\"");
```

# Informazioni generiche
L'Enum it.giuseppesgambato.logging.extensions.commons.TipologiaAttributoLogEnum contiene i parametri supportati.
L'Enum it.giuseppesgambato.logging.extensions.commons.TipologiaRecordTypeEnum contiene i paramentrisupportati per il campo RECORDTYPE.
Ogni nuovo Layout custom potrà implementare una delle due interfaccia IJsonLayout o ITextLayout e i relativi metodi toJson o toText.

# Informazioni formato JSON
In caso di Parametri non valorizzati, la libreria prevede il seguente comportamento {nomeAttributo": null}.
Gli attributi PID, error_code, result_code e time sono campi numerici e in quanto tali sono senza apici " " nel JSON. 
Gli attributi PID, error_code, result_code e time verranno settati a null se non valorizzati correttamente.

# Informazioni formato Testo strutturato
In caso di Parametri non valorizzati, la libreria prevede che il campo venga valorizzato con ' - '.

# Gestione StackTrace 
In caso sia presente un oggetto Throwable nel contesto del logger lo StackTrace ad esso correlato verranno gestiti nel campo EXTRADATA


# Es.: Log JSON
```sh
{
  "dateTime": "2020-02-18T11:37:27Z",
  "timeStamp": 1582022247138,
  "logLevel": "INFO",
  "recordType": "RECORDTYPE",
  "sid": "SID",
  "bid": "BID",
  "tid": "TID",
  "key": "KEY",
  "pid": 999,
  "threadName": "threadName",
  "system": "SYSTEM",
  "processName": "methodName",
  "loggerName": "loggerName",
  "producer": "PRODUCER",
  "consumer": "CONSUMER",
  "resultCode": 999,
  "errorCode": 999,
  "errorMessage": "ERRORMESSAGE",
  "time": 999,
  "extraData": "[\"A\":\"A Value\" , \"B\":\"B Value\"]",
  "message": "message"
}
```

# Es.: Log TESTO STRUTTURATO 

2020-02-18T11:33:47Z 1582022027891 INFO RECORDTYPE SID BID TID KEY PID threadName SYSTEM methodName loggerName PRODUCER CONSUMER RESULTCODE ERRORCODE  ERRORMESSAGE TIME ["A":"A Value" , "B":"B Value"] message

# Es.: Log JSON [con attributi non valorizzati]
```sh
{
  "dateTime": "2020-02-18T11:37:27Z",
  "timeStamp": 1582022247138,
  "logLevel": "INFO",
  "recordType": null,
  "sid": "SID",
  "bid": null,
  "tid": null,
  "key": null,
  "pid": null,
  "threadName": "threadName",
  "system": "SYSTEM",
  "processName": "methodName",
  "loggerName": "loggerName",
  "producer": "PRODUCER",
  "consumer": null,
  "resultCode": "RESULTCODE",
  "errorCode": "ERRORCODE",
  "errorMessage": "ERRORMESSAGE",
  "time": "TIME_TEST",
  "extraData": null,
  "message": "message"
}
```

# Es.: Log TESTO STRUTTURATO [Con attributi non valorizzati]

2020-02-18T11:33:47Z 1582022027891 INFO - SID - - - - threadName SYSTEM methodName loggerName PRODUCER - RESULTCODE ERRORCODE ERRORMESSAGE TIME - message


# Es.: Log TESTO STRUTTURATO [Con StackTrace nel campo ExtraData]
2020-02-19T14:38:23Z 1582119503367 ERROR S SID_TEST BID_TEST TID_TEST KEY_TEST PID_TEST main LOGGING-EXTENSIONS_SYSTEM_TEST_LOG4J2_TEXT callMe it.giuseppesgambato.logging.extensions.log4j2.test.Log4j2CallMeService PRODUCER_TEST CONSUMER_TEST RESULTCODE_TEST ERRORCODE_TEST ERRORMESSAGE_TEST TIME_TEST 
["A":"A Value" , "B":"B Value", "stackTrace":" it.giuseppesgambato.logging.extensions.log4j2.test.Log4j2CallMeService.callMe(Log4j2CallMeService.java:17)
 it.giuseppesgambato.logging.extensions.log4j2.test.Log4j2_Test_TEXT.TEST_LOG_COMPLETO(Log4j2_Test_TEXT.java:50)
 sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)..."] An exception

# Es.: Log JSON [Con StackTrace nel campo ExtraData]
```sh
{
  "dateTime": "2020-02-19T14:41:43Z",
  "timeStamp": 1582119703538,
  "logLevel": "ERROR",
  "recordType": "E",
  "sid": "SID",
  "bid": "BID",
  "tid": "TID",
  "key": "KEY",
  "pid": "999",
  "threadName": "main",
  "system": "LOGGING-EXTENSIONS_SYSTEM_TEST_LOG4J2_JSON",
  "processName": "callMe",
  "loggerName": "it.giuseppesgambato.logging.extensions.log4j2.test.Log4j2CallMeService",
  "producer": "PRODUCER",
  "consumer": "CONSUMER",
  "resultCode": 999,
  "errorCode": 999,
  "errorMessage": "ERRORMESSAGE_TEST",
  "time": 999,
  "extraData": "[\"A\":\"A Value\" , \"B\":\"B Value\", \"stackTrace\":\" it.giuseppesgambato.logging.extensions.log4j2.test.Log4j2CallMeService.callMe(Log4j2CallMeService.java:17)\r\n it.giuseppesgambato.logging.extensions.log4j2.test.Log4j2_Test_JSON.TEST_LOG_COMPLETO(Log4j2_Test_JSON.java:50)\r\n..."]",
  "message": "An exception"
}
```

# Guida all'utilizzo delle Annotation
La libreria fornisce un set di annotazioni (a livello di metodo) specifici per libreria di logging.
Ciascuna annotazione e' legata ad un Aspect che

* si prende cura di settare alcuni parametri specifici del MDC (vedi nei paragrafi sotto)
* fa scattare una loggata all'inizio dell'esecuzione del metodo annotato
* fa scattare una loggata di tipo SUMMARY (TipologiaAttributoLogEnum.RECORDTYPE = "S") alla fine dell'esecuzione del metodo annotato includendo il tempo di esecuzione nel campo TipologiaAttributoLogEnum.TIME
* in caso di eccezione non gestita dal metodo annotato, nel MDC verra' impostato TipologiaAttributoLogEnum.ERRORCODE a "1" e TipologiaAttributoLogEnum.ERRORMESSAGE con il getMessage() dell'Exception

## NOTA IMPORTANTE
Per poter utilizzare correttamente le annotazioni e' necessario includere il package it.giuseppesgambato.logging all'interno del Component Scan della propria app Spring

Ad esempio:
```java
@SpringBootApplication(scanBasePackages = {
        "it.giuseppesgambato.myProjectE",
        "it.giuseppesgambato.logging",
})
```

## Log4jExternal & LogbackExternal
Queste annotazioni hanno due argomenti opzionali (se non forniti verranno prodotti come stringa vuota nei log)

* producer - che corrispondera' nel log al campo TipologiaAttributoLogEnum.PRODUCER
* consumer - che corrispondera' nel log al campo TipologiaAttributoLogEnum.CONSUMER

Oltre agli argomenti sopra, le annotazioni imposteranno il tipo delle loggate del metodo annotato 
come tipo EXTERNAL (TipologiaAttributoLogEnum.RECORDTYPE = "E")

Al termine dell'esecuzione del metodo annotato, il MDC viene ripristinato con i valori che aveva prima della chiamata al metodo

Esempio di utilizzo (Sistema Reprografia chiama autenticazione SSO)

```java
    @Log4jExternal(producer = "REPRO", consumer = "SSO")
    public ExternalAuthResponse authenticate(String email, String password)
    {
       ...
    }
```

## Log4jScheduled & LogbackScheduled
Queste annotazioni hanno un argomento opzionale (se non fornito verra' prodotto come stringa vuota nei log)

* SYSTEM - che corrispondera' nel log al campo TipologiaAttributoLogEnum.SYSTEM

Oltre agli argomenti sopra, le annotazioni 

* imposteranno il tipo delle loggate del metodo annotato come tipo INTERNAL (TipologiaAttributoLogEnum.RECORDTYPE = "I")
* imposteranno il campo TipologiaAttributoLogEnum.PID come un alfanumerico random con size 6
* imposteranno il campo TipologiaAttributoLogEnum.TID come un UID random avente prefisso 'sched-'

Al termine dell'esecuzione del metodo annotato, il MDC viene ripulito

Esempio di utilizzo (metodo @Scheduled in un project)

```java
    @Scheduled(cron = "${giuseppesgambato.scheduler.project.cron_expression}")
    @Log4jScheduled(system = "REPRO")
    public void myMethod() {
        ...
    }
```
