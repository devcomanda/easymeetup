package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.service.MeetupService;
import com.devcomanda.easymeetup.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aleksandr Beryozkin
 */

@RestController
@RequestMapping(path="/api/accounts")
public class UserController {

    private final UserService userService;

    private MeetupService meetupService;

    public UserController(UserService userService, MeetupService meetupService) {
        this.userService = userService;
        this.meetupService = meetupService;
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<User> findUserById(@PathVariable ("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @GetMapping(path = "/meetups", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Meetup>> loadUserHistoryMeetups(){
        List<Meetup> meetupsHistory = meetupService.loadUserMeetupHistory();

        return new ResponseEntity<>(meetupsHistory, HttpStatus.FOUND);
    }

}
