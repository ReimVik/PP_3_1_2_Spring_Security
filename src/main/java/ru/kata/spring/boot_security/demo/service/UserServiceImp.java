package ru.kata.spring.boot_security.demo.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


@Service
@Transactional
public class UserServiceImp implements UserService {
    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public User findById(long id) {
        Optional<User> userFromDb = userRepository.findById(id);
        return userFromDb.orElse(new User());
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        User userFromDB = userRepository.findByNameIgnoreCase(user.getUsername());

        if (userFromDB != null) {
            throw new RuntimeException("Такой пользователь существует");
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        return userRepository.save(user);
    }

    public void updateUser(User user,long id) {
        User updatedUser = findById(id);
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setAge(user.getAge());
        updatedUser.setEmail(user.getEmail());
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public String findByName(String name) {
        return userRepository.findByNameIgnoreCase(name).getName();
    }
}
