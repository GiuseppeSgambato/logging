package it.giuseppesgambato.logging.extensions.layouts;

public interface ITextLayout {

    /**
     * Genera una Stringa in formato Test Strutturato a partire dai dati di Input.
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
     * @return La stringa in formato Test Strutturato.
     */
    String toText(long timeStamp,
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
                  String eccezione);

}
