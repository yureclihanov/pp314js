package ru.kata.spring.boot_security.demo.servise;



import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();
    User findUserById(int id);
    void add(User user);
    void update(int id ,User user);
    void deleteById(int id);
    User findUserByName(String name);
    User findByEmail(String email);
 //   void setInitData();

}
