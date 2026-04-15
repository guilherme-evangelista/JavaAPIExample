package com.dummyjson.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor()
public enum ValidZipCode {

    AC("Acre", "69905590"),
    AL("Alagoas", "57020050"),
    AP("Amapá", "68901025"),
    AM("Amazonas", "69005010"),
    BA("Bahia", "41342123"),
    CE("Ceará", "60060000"),
    DF("Distrito Federal", "70050000"),
    ES("Espírito Santo", "29010640"),
    GO("Goiás", "74025010"),
    MA("Maranhão", "65020250"),
    MT("Mato Grosso", "78020190"),
    MS("Mato Grosso do Sul", "79002210"),
    MG("Minas Gerais", "30130110"),
    PA("Pará", "66010000"),
    PB("Paraíba", "58013080"),
    PR("Paraná", "80020000"),
    PE("Pernambuco", "50050440"),
    PI("Piauí", "64000070"),
    RJ("Rio de Janeiro", "20010010"),
    RN("Rio Grande do Norte", "59064240"),
    RS("Rio Grande do Sul", "90010280"),
    RO("Rondônia", "76801119"),
    RR("Roraima", "69306705"),
    SC("Santa Catarina", "88015100"),
    SP("São Paulo", "01001001");

    private final String state;
    private final String zipCode;
}
