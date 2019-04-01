package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.model.entity.enums.Status;

import java.util.List;

public interface MeetupService {

    Meetup loadMeetup(Long id);

    Meetup saveMeetup(Meetup meetUp);

    List<Meetup> loadMeetups();

    List<Meetup> loadMeetupsByStatus(List<Meetup> meetups, Status status);

    Meetup updateMeetup(Meetup changedMeetup);

    List<Meetup> loadUserMeetupHistory();

    void registerUserToMeetup(Long id);
}
