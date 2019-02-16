package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.model.entity.exceptions.MeetupNotFoundException;
import com.devcomanda.easymeetup.repository.MeetupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeetupServiceImpl implements MeetupService {
    private final MeetupRepository meetUpRepository;

    public MeetupServiceImpl(final MeetupRepository meetUpRepository) {
        this.meetUpRepository = meetUpRepository;
    }

    @Override
    public Meetup loadMeetup(Long id) {
        return meetUpRepository.findById(id)
                .orElseThrow(MeetupNotFoundException::new);
    }

    @Override
    public List<Meetup> loadMeetups() {
        return meetUpRepository.findAll();
    }

    @Override
    @Transactional
    public Meetup saveMeetup(Meetup meetUp) {
        return meetUpRepository.save(meetUp);
    }


    @Override
    @Transactional
    public Meetup updateMeetup(Meetup changedMeetup) {

        final Meetup meetup = this.meetUpRepository.findById(changedMeetup.getId())
                .orElseThrow(MeetupNotFoundException::new);

        meetup.setName(changedMeetup.getName());
        meetup.setDescription(changedMeetup.getDescription());
        meetup.setAddress(changedMeetup.getAddress());

        meetup.setSpeaker(changedMeetup.getSpeaker());

        meetup.setStartDate(changedMeetup.getStartDate());
        meetup.setEndDate(changedMeetup.getEndDate());

        return meetup;
    }
}
