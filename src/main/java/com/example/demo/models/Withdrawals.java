package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name= "withdrawals")

public class Withdrawals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "withdrawal_id")
    private long id;


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_account_id", nullable = false)
    private CustomerAccount customerAccount;

    @Column(name = "iban", nullable = false )
    private String iban;

    @Column(name = "total")
    private Double totalBalanceSum;

    @Column(name = "purpose", nullable = false )
    private String purpose;

    public Withdrawals() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getTotalBalanceSum() {
        return totalBalanceSum;
    }

    public void setTotalBalanceSum(Double totalBalanceSum) {
        this.totalBalanceSum = totalBalanceSum;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Withdrawals(CustomerAccount customerAccount, String iban, Double totalBalanceSum, String purpose) {
        this.customerAccount = customerAccount;
        this.iban = iban;
        this.totalBalanceSum = totalBalanceSum;
        this.purpose = purpose;
    }
}
