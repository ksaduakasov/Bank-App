package com.example.demo.controller;


import com.example.demo.service.authorisation.CustomUserDetails;
import com.example.demo.models.CustomerAccount;
import com.example.demo.models.Deposits;
import com.example.demo.models.enums.Currency;
import com.example.demo.repository.CustomerAccountRepository;
import com.example.demo.repository.DepositRepository;
import com.example.demo.components.DepositForm;
import com.example.demo.service.UserService;
import com.example.demo.models.enums.StringToCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DepositController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerAccountRepository customerAccountRepository;


    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private DepositForm depositForm;


    @ModelAttribute("currentCustomerAccounts")
    public List<CustomerAccount> getCurrentCustomerAccounts(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return userService.findCustomerAccountById(customUserDetails.getId());
    }


    @ModelAttribute("addDepositForm")
    public DepositForm getDepositForm() {
        return depositForm;
    }

    @GetMapping("/deposit/transactions")
    public String getDepositTransactions(){
        return "deposit-transactions";
    }


    @GetMapping("/deposits/account/{id}")
    public String getDepositAccountIdTransactions(@PathVariable("id") long accountId, Model model) {
        List<Deposits>  depositsList = depositRepository.findTransactionsByCustomerAccountId(accountId);
        model.addAttribute("depositsList", depositsList);
        depositForm.setCustomerAccountId(accountId);
        return "customer-deposit-transactions";

    }

    @PostMapping("/deposit")
    public String processDeposit(@ModelAttribute("addDepositForm") DepositForm depositForm,
                                 Model model) {

        var customerAccountId = depositForm.getCustomerAccountId();
        var amount = depositForm.getAmount();
        Currency currency = StringToCurrency.convertStringToCurrency(depositForm.getCurrency());

        CustomerAccount customerAccount =  customerAccountRepository.findCustomerAccountById(customerAccountId);

        if(customerAccount !=null && customerAccount.getCustomerAccountBalance() > 0) {
            if(customerAccount.getCurrency().equals(Currency.TENGE)) {
                switch (depositForm.getCurrency()) {
                    case "tenge":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + amount);
                        break;
                    case "ruble":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + (amount * 5.66));
                        break;
                    case "dollar":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + (amount * 417));
                        break;
                    default:
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + amount);
                }
            } else if (customerAccount.getCurrency().equals(Currency.RUBLE)) {
                switch (depositForm.getCurrency()) {
                    case "tenge":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + (amount * 0.18));
                        break;
                    case "ruble":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + amount);
                        break;
                    case "dollar":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + (amount * 73.57));
                        break;
                    default:
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + amount);
                }
            } else if (customerAccount.getCurrency().equals(Currency.DOLLAR)) {
                switch (depositForm.getCurrency()) {
                    case "tenge":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + (amount * 0.0024));
                        break;
                    case "ruble":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + (amount * 0.014));
                        break;
                    case "dollar":
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + amount);
                        break;
                    default:
                        customerAccount.setCustomerAccountBalance(customerAccount.getCustomerAccountBalance() + amount);
                }
            }


            Deposits deposits = new Deposits(customerAccount, currency, amount);
            customerAccountRepository.save(customerAccount);
            depositRepository.save(deposits);

            model.addAttribute("successfulTransaction","successfulTransaction");
        } else {

            model.addAttribute("it reach the max deposit limit", "maxDepositLimitError");
        }

        return "deposit-transactions";

    }
}
