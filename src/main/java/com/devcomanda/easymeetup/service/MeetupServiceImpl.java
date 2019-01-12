package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.repository.MeetupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetupServiceImpl implements MeetupService {
    private final MeetupRepository meetUpRepository;

    public MeetupServiceImpl(final MeetupRepository meetUpRepository) {
        this.meetUpRepository = meetUpRepository;
    }

    @Override
    public Meetup saveMeetup(Meetup meetUp) {
        return meetUpRepository.save(meetUp);
    }

    @Override
    public List<Meetup> findAllMeetups() {
        return meetUpRepository.findAll();
    }
}
