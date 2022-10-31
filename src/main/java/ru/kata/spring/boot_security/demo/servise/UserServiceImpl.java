package ru.kata.spring.boot_security.demo.servise;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.HashSet;
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
        User userToUpdate = dao.getById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setRoles(user.getRoles());
        dao.save(userToUpdate);
    }

    @Override
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

  //  public void setInitData() {
  //      Role userRole = new Role("ROLE_USER");
  //      Role adminRole = new Role("ROLE_ADMIN");
  //      dao.save(new User("user", "user", 30, "user@mail.ru", "123", new HashSet<Role>() {
  //          {
  //          add(userRole);}
  //      }));
  //      dao.save(new User("admin", "admin", 35, "admin@mail.ru", "456", new HashSet<Role>() {{
  //          add(userRole);
  //          add(adminRole);
  //      }}));
  //  }
}
