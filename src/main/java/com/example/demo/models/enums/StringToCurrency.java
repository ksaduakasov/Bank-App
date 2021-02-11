package com.example.demo.models.enums;


import static com.example.demo.models.enums.Currency.*;

public class StringToCurrency {

    public static Currency convertStringToCurrency (String currency) {
        switch (currency.toLowerCase()) {
            case "tenge":
                return TENGE;
            case "ruble":
                return RUBLE;
            case "dollar":
                return DOLLAR;
            default:
                return TENGE;

        }
    }
}
