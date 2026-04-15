package com.dummyjson.data.models.users;

import lombok.Data;

@Data
public class Bank {
    private String cardExpire;
    private String cardNumber;
    private String cardType;
    private String currency;
    private String iban;
}