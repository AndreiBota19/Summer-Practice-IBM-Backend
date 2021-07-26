package com.example.checkin.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return all users
     */
    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * @return user with userId
     */
    @GetMapping(path = "find/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId){
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "find/guests")
    public ResponseEntity<List<User>> getAllGuests () {
        List<User> users = userService.findAllGuests();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "find/students")
    public ResponseEntity<List<User>> getAllStudents () {
        List<User> users = userService.findAllStudents();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "find/teachers")
    public ResponseEntity<List<User>> getAllTeachers () {
        List<User> users = userService.findAllTeachers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "find/admins")
    public ResponseEntity<List<User>> getAllAdmins () {
        List<User> users = userService.findAllAdmins();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
