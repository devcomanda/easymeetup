package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.service.MeetupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class MeetupController {

    private final MeetupService meetUpService;

    public MeetupController(final MeetupService meetUpService) {
        this.meetUpService = meetUpService;
    }

    @GetMapping(path = "/meetups/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Meetup> getMeetupById(@PathVariable ("id") Long id){
        Optional<Meetup> meetup = meetUpService.findMeetupById(id);
        return new ResponseEntity<>(meetup.get(), HttpStatus.FOUND);
    }

    @PostMapping(path = "/meetups", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Meetup> saveMeetup(@Valid @RequestBody Meetup meetup){
        meetUpService.saveMeetup(meetup);
        return new ResponseEntity<>(meetup, HttpStatus.CREATED);
    }

    @GetMapping(path = "/meetups", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Meetup>> getAllMeetups(){
        List<Meetup> meetups = meetUpService.findAllMeetups();
        if(meetups.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(meetups, HttpStatus.FOUND);
    }

    @PutMapping(path = "/meetups/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Meetup> updateMeetup(@PathVariable ("id") Long id,
                                               @Valid @RequestBody Meetup meetup){
        meetUpService.updateMeetup(id, meetup);

        return new ResponseEntity<>(meetup, HttpStatus.OK);
    }
}
