package com.example.demo.components;



import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Component
public class CustomerAccountForm {

    @NotEmpty
    @DecimalMin("0.00")
    @DecimalMax("99999999999.00")
    private double customerAccountBalance;

    @NotEmpty
    private String currency;

    public double getCustomerAccountBalance() {
        return customerAccountBalance;
    }

    public void setCustomerAccountBalance(double customerAccountBalance) {
        this.customerAccountBalance = customerAccountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
