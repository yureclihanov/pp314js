package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.servise.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService service;

    @Autowired
    public AdminController(UserService servise) {
        this.service = servise;
    }


    @GetMapping
    public String all(Model model) {
        model.addAttribute("people", service.allUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", service.findUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") User user) {
        return "/new";
    }

    @PostMapping
    public String createNewUser(@ModelAttribute("person") User user) {
        service.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/update")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", service.findUserById(id));
        return "update";
    }

    @PatchMapping("/{id}")
    public String updateuser(@ModelAttribute("person") User user, @PathVariable("id") int id) {
        service.update(id ,user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        service.deleteById(id);
        return "redirect:/admin";
    }

}
