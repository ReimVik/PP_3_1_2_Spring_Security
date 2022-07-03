package ru.kata.spring.boot_security.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ru.kata.spring.boot_security.demo.models.User;


public interface UserService {
    public User findById(long id);
    public List<User> findAll();
    public User save(User user);
    public void deleteById(long id);
    public void updateUser(User user,long id);
    public String findByName(String name);
}
