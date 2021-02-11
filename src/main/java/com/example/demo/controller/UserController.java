package com.example.demo.controller;


import com.example.demo.components.UserCreateForm;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserCreatorFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    public static UserCreateForm userCreateForm;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCreatorFormValidator userCreatorFormValidator;

    @InitBinder("registerForm")
    public void registerFormInitBinder(WebDataBinder binder) {
        binder.addValidators(userCreatorFormValidator);

    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String processLogin(){
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("registerForm") UserCreateForm userCreateForm) {
        return "register";

    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("registrationForm") UserCreateForm userCreateForm,
                                      BindingResult bindingResult) {

        UserController.userCreateForm = userCreateForm;
        if(bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerUser(userCreateForm);
        return "redirect:/login";
    }


}
