package com.example.demo.resources;


import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

@Component
public class WithdrawalForm {

    @NotEmpty
    @Length(min = 10, max = 22)
    private String iban;

    @NotEmpty
    @DecimalMin("0.00")
    @DecimalMax("99999999999.00")
    private double customerAccountBalance;

    @NotEmpty
    @Length(min = 6, max=200)
    private String purpose;

    private long customerAccountId;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getCustomerAccountBalance() {
        return customerAccountBalance;
    }

    public void setCustomerAccountBalance(double customerAccountBalance) {
        this.customerAccountBalance = customerAccountBalance;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(long customerAccountId) {
        this.customerAccountId = customerAccountId;
    }
}
