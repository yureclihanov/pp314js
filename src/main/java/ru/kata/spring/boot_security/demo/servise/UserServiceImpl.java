package ru.kata.spring.boot_security.demo.servise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository dao ;

    @Autowired
    public UserServiceImpl(UserRepository dao) {
        this.dao = dao;
    }

    @Override
    public List<User> allUsers() {
        return dao.findAll();
    }

    @Override
    public User findUserById(int id) {
        Optional<User> user = dao.findById(id);
        return user.orElse(null);
    }

    @Override
    public void add(User user) {
        dao.save(user);
    }

    @Override
    public void update(int id, User user) {
        user.setId(id);
        dao.save(user);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public User findUserByName(String name) {
        return dao.findUserByName(name);
    }
}
