package ru.kata.spring.boot_security.demo.servise;

import jdk.jfr.Category;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(int id);

    List<Role> getAllRoles();

    Role getRoleByName(String roleName);

    void save(Role role);
}
