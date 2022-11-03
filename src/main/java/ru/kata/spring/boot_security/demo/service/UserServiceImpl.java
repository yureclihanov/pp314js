package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Exception.UserNotFoundException;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository dao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository dao, PasswordEncoder passwordEncoder) {
        this.dao = dao;
        this.passwordEncoder = passwordEncoder;
    }

    public User passwordEncode(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    @Override
    public List<User> allUsers() {
        return dao.findAll();
    }

    @Override
    public User findUserById(int id) {
        Optional<User> user = dao.findById(id);
        return user.orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void add(User user) {
        dao.save(passwordEncode(user));
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        User userToUpdate = dao.getById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setRoles(user.getRoles());
        userToUpdate.setPassword(user.getPassword());
        dao.save(userToUpdate);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public User findUserByName(String name) {
        return dao.findUserByName(name);
    }

    @Override
    public User findByEmail(String email) {
        return dao.findByEmail(email);
    }

}
