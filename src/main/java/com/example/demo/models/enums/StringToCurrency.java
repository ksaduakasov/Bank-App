package com.example.demo.enums;


import com.example.demo.enums.Currency;

import static com.example.demo.enums.Currency.*;

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
