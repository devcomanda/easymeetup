package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.model.entity.Meetup;

import java.util.List;

public interface MeetupService {

    Meetup loadMeetup(Long id);

    Meetup saveMeetup(Meetup meetUp);

    List<Meetup> loadMeetups();

    Meetup updateMeetup(Meetup changedMeetup);
}
