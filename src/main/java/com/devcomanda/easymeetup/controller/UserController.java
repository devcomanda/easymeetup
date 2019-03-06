package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.model.security.InvalidUserActivationKeyException;
import com.devcomanda.easymeetup.service.UserSecurityService;
import com.devcomanda.easymeetup.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.net.URI;

/**
 * @author Aleksandr Beryozkin
 */

@RestController
@RequestMapping(path="/api")
public class UserController {

    private final UserService userService;
    private final UserSecurityService userSecurityService;

    public UserController(UserService userService, UserSecurityService userSecurityService) {
        this.userService = userService;
        this.userSecurityService = userSecurityService;
    }
    
    @GetMapping(path="/accounts/{id}")
    public ResponseEntity<User> findUserById(@PathVariable ("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping(path = "/auth/verification")
    public ResponseEntity activateUser(@PathParam("code") String activationKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));

        try {
            userSecurityService.activate(activationKey);
            return new ResponseEntity<>(headers, HttpStatus.PERMANENT_REDIRECT);
        }catch (InvalidUserActivationKeyException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
