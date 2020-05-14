package it.giuseppesgambato.logging.extensions.layouts;

public interface IJsonLayout
{
    /**
     * Genera una Stringa in formato JSON a partire dai dati di Input.
     *
     * @param timeStamp
     * @param level
     * @param recordType
     * @param sid
     * @param bid
     * @param tid
     * @param key
     * @param pid
     * @param threadName
     * @param system
     * @param methodName
     * @param loggerName
     * @param producer
     * @param consumer
     * @param resultCode
     * @param errorCode
     * @param errorMessage
     * @param time
     * @param extraData
     * @param message
     * @param eccezione
     * @return la Stringa in formato JSON
     */
    String toJson(Long timeStamp,
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
                  String eccezione);
}
