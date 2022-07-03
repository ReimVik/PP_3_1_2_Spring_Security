package ru.kata.spring.boot_security.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService user;

    @Autowired
    public UserController(UserService user) {
        this.user = user;
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.findById(id));
        return "user/user";
    }
}
