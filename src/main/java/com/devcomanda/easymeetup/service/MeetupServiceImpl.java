package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.repository.MeetupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeetupServiceImpl implements MeetupService {
    private final MeetupRepository meetUpRepository;

    public MeetupServiceImpl(final MeetupRepository meetUpRepository) {
        this.meetUpRepository = meetUpRepository;
    }

    @Override
    public Optional<Meetup> findMeetupById(Long id) {
        return meetUpRepository.findById(id);
    }

    @Override
    public Meetup saveMeetup(Meetup meetUp) {
        return meetUpRepository.save(meetUp);
    }

    @Override
    public List<Meetup> findAllMeetups() {
        return meetUpRepository.findAll();
    }

    @Override
    public Meetup updateMeetup(Long id, Meetup meetup) {
        Optional<Meetup> foundMeetup = meetUpRepository.findById(id);

        foundMeetup.get().setMeetupName(meetup.getMeetupName());
        foundMeetup.get().setMeetupAddress(meetup.getMeetupAddress());
        foundMeetup.get().setMeetupDate(meetup.getMeetupDate());
        foundMeetup.get().setMeetupDescription(meetup.getMeetupDescription());
        foundMeetup.get().setMeetupSpeaker(meetup.getMeetupSpeaker());

        return meetUpRepository.save(foundMeetup.get());
    }
}
