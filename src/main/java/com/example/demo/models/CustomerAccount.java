package com.example.demo.entity;


import com.example.demo.enums.Currency;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "customer_accounts")
public class CustomerAccount {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_account_id")
    private long id;

    //Lazy -- it fetch when it needed
    //Eager -- fetch immediately
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private  User user;

    //cascade : it transfer operation on one object
    //Reference type is the mappedBy
    @OneToMany(
            mappedBy = "customerAccount",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Withdrawals> withdrawals;

    @OneToMany(
            mappedBy = "customerAccount",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Deposits> deposits;

    @Column(name = "customer_account_balance", nullable = false)
    private Double customerAccountBalance;

    //it is attribute for enum mapping in hibernate
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    public CustomerAccount() {

    }

    public CustomerAccount(User user, Double customerAccountBalance, Currency currency) {
        this.user = user;
        this.customerAccountBalance = customerAccountBalance;
        this.currency = currency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Withdrawals> getWithdrawals() {
        return withdrawals;
    }

    public void setWithdrawals(List<Withdrawals> withdrawals) {
        this.withdrawals = withdrawals;
    }

    public List<Deposits> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<Deposits> deposits) {
        this.deposits = deposits;
    }

    public Double getCustomerAccountBalance() {
        return customerAccountBalance;
    }

    public void setCustomerAccountBalance(Double customerAccountBalance) {
        this.customerAccountBalance = customerAccountBalance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
