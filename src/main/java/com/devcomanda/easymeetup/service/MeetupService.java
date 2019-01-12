package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.entity.Meetup;

import java.util.List;

public interface MeetupService {

    Meetup saveMeetup(Meetup meetUp);

    List<Meetup> findAllMeetups();
}
