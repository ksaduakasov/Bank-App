package com.example.demo.service;



import com.example.demo.models.CustomerAccount;
import com.example.demo.models.Deposits;
import com.example.demo.models.User;
import com.example.demo.models.Withdrawals;
import com.example.demo.components.UserCreateForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    List<CustomerAccount> findCustomerAccountById(long id);
    List<Withdrawals> findTransactionsByCustomerAccountId(long id);
    User registerUser(UserCreateForm userCreateForm);
    boolean hasValidPassword(User user, String pwd);
    List<Deposits> findTransactionsByAccountId(long id);
}
