package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.service.UserSecurityService;
import com.devcomanda.easymeetup.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aleksandr Beryozkin
 */

@RestController
@RequestMapping(path="/api/accounts")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping(path="/{id}")
    public ResponseEntity<User> findUserById(@PathVariable ("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
}
