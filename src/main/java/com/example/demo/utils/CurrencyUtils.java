package com.example.demo.utils;


import com.example.demo.enums.Currency;

import static com.example.demo.enums.Currency.*;

public class CurrencyUtils {

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
