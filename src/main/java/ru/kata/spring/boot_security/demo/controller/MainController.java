package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class MainController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService,
                          RoleService roleService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String allUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.findUserById(id));
        return "admin";
    }

    @GetMapping()
    public String showAllUsers(Model model, Principal principal) {
        List<User> users = userService.allUsers();
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("users", users);
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("userRep", userService.findByEmail(principal.getName()));
        return "admin";
    }
}
