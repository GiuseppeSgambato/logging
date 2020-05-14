package it.giuseppesgambato.logging.extensions.commons;

public enum TipologiaRecordTypeEnum {
    INTERNAL ("I"),  // I - Internal record che descrive un evento interprocesso
    EXTERNAL ("E"),  // E - External record che descrive una comunicazione con un sistema/modulo esterno
    SUMMARY ("S") ;  // S - Summary record che descrive il risultato di una operazione interna/esterna

    private String value;

    public String getValue() { return value; }

    TipologiaRecordTypeEnum(String value){
        this.value=value;
    }


}
