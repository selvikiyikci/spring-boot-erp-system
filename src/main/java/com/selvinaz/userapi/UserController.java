package com.selvinaz.userapi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        return List.of(
                new User(1L, "Ali"),
                new User(2L, "Ayşe"),
                new User(3L, "Mehmet")
        );
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
}