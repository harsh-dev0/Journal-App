package xyz.znet.journalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.znet.journalapp.entity.User;
import xyz.znet.journalapp.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }
    @PostMapping
    public void createUser(@RequestBody User user) {
         userService.saveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
        User byUsername = userService.findByUsername(userName);
        if (byUsername != null) {
            byUsername.setUserName(user.getUserName());
            byUsername.setPassword(user.getPassword());
            userService.saveEntry(byUsername);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
