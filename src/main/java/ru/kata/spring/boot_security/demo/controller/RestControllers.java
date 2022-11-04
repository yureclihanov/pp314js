package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.Exception.UserNotCreate;
import ru.kata.spring.boot_security.demo.Exception.UserNotUpdate;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/api/users")
public class RestControllers {
    private final UserService userService;

    @Autowired
    public RestControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.allUsers(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getOneUser(@PathVariable("id") int id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
            userService.add(user);
            return new ResponseEntity<>(HttpStatus.OK);
     //   } catch (UserNotCreate e) {
     //       throw new UserNotCreate("The user is not created");
     //   }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

  //  @GetMapping("/user")
  //  public ResponseEntity<User> getUserByName(Principal principal) {
  //      User user = userService.findUserByName(principal.getName());
  //      return new ResponseEntity<>(user, HttpStatus.OK);
  //  }

    @PutMapping("{id}")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user, @PathVariable("id") int id) {
      //  try {
      //      String userPassword = userService.findUserById(id).getPassword();
      //      if (userPassword.equals(user.getPassword())) {
                userService.update(id, user);
     //       } else {
     //           userService.add(user);
     //       }
            return new ResponseEntity<>(HttpStatus.OK);
     //   }catch (UserNotUpdate e) {
     //       throw new UserNotUpdate("The user is not updated");
     //   }

    }

}
