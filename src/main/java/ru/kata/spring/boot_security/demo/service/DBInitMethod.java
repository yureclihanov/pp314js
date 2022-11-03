package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class DBInitMethod {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public DBInitMethod(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @PostConstruct
    public void myInitMethod() {
        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");

        roleService.save(userRole);
        roleService.save(adminRole);

        Set<Role> adminSet = new HashSet<>();
        Set<Role> userSet =new HashSet<>();

        adminSet.add(adminRole);
        adminSet.add(userRole);
        userSet.add(userRole);



        User adminUser = new User("admin", "admin", 20, "admin@mail.ru",
                "test",adminSet);
        User newUser = new User("user", "user", 20, "user@mail.ru",
                "test", userSet);

        userService.add(adminUser);
        userService.add(newUser);

    }
}
