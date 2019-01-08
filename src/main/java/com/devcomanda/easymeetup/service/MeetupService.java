package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.entity.Meetup;

public interface MeetupService {
    Meetup saveOrUpdateMeetup(Meetup meetUp);
}
