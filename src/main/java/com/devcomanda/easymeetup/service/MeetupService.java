package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.entity.Meetup;

import java.util.List;
import java.util.Optional;

public interface MeetupService {

    Optional<Meetup> findMeetupById(Long id);

    Meetup saveMeetup(Meetup meetUp);

    List<Meetup> findAllMeetups();

    Meetup updateMeetup(Long id, Meetup meetup);
}
