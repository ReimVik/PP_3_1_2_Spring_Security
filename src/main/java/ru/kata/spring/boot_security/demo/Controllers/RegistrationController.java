package ru.kata.spring.boot_security.demo.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class RegistrationController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "auth/login";
    }


    @GetMapping("/logout")
    public String showLogoutPage() {
        return "auth/logout";
    }
}
