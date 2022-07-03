package ru.kata.spring.boot_security.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PasswordEncoder passwordEncoder;

    private final UserService user;

    @Autowired
    public AdminController(PasswordEncoder passwordEncoder, UserService user) {
        this.passwordEncoder = passwordEncoder;
        this.user = user;
    }

    @GetMapping
    public String showAdminRootPage(Model model) {
        model.addAttribute("users", user.findAll());
        return "admin/users";
    }

    @GetMapping("/users")
    public String showAllUsersPage(Model model) {
        model.addAttribute("users", user.findAll());
        return "admin/users";
    }

    @GetMapping("/{id}")
    public String showUserPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.findById(id));
        return "admin/user";
    }

    @GetMapping("/new")
    public String showNewUserPage(@ModelAttribute("newUser") User newUser) {
        return "admin/newUser";
    }

    @PostMapping("/users")
    public String createNewUser(@ModelAttribute("newUser") User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.save(newUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/edit")
    public String showEditUserPage(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", user.findById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User editedUser, @PathVariable("id") long id) {
        user.updateUser(editedUser, id);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@ModelAttribute("user") User deletedUser, @PathVariable("id") long id) {
        user.deleteById(id);
        return "redirect:/admin/";
    }
}
