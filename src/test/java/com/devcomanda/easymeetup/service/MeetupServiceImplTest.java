package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.repository.MeetupRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MeetupServiceImplTest {

    @Mock
    private MeetupRepository meetupRepository;

    private MeetupService meetupService;

    private Meetup expectedMeetup;
    private Meetup nextMeetup;

    private List<Meetup> meetups = new ArrayList<>();

    @Before
    public void setup(){
        meetupService = new MeetupServiceImpl(meetupRepository);
        expectedMeetup = new Meetup();
        expectedMeetup.setId(1L);
        expectedMeetup.setMeetupName("DNA programming with java");
        nextMeetup = new Meetup();
        nextMeetup.setMeetupName("Space crafts java programming");

        meetups.add(expectedMeetup);
        meetups.add(nextMeetup);

    }

    @Test
    public void saveMeetupTest(){
        Mockito.when(meetupRepository.save(expectedMeetup))
                .thenReturn(expectedMeetup);

       Meetup actualMeetup = meetupService.saveMeetup(expectedMeetup);

       assertThat(actualMeetup).isNotNull()
                .isEqualTo(expectedMeetup)
                .isEqualToComparingFieldByField(expectedMeetup);
    }

    @Test
    public void findAllMeetupsTest(){
        Mockito.when(meetupRepository.findAll())
                .thenReturn(meetups);

        List<Meetup> actualMeetups = meetupRepository.findAll();

        assertThat(actualMeetups).isNotNull()
                .isEqualTo(meetups)
                .containsSequence(expectedMeetup, nextMeetup);
    }

    @Test
    public void findMeetupById(){
        Mockito.when(meetupRepository.findById(1l)).thenReturn(Optional.ofNullable(expectedMeetup));

        Optional<Meetup> actualMeetup = meetupService.findMeetupById(1L);

        assertThat(actualMeetup.get()).isEqualTo(expectedMeetup);
    }

    @Test
    public void updateMeetupTest(){
        Mockito.when(meetupRepository.save(expectedMeetup)).thenReturn(expectedMeetup);

        expectedMeetup.setMeetupName("Java for");

        Meetup updatedMeetup = meetupRepository.save(expectedMeetup);

        assertThat(updatedMeetup.getMeetupName()).isEqualTo(expectedMeetup.getMeetupName());
        assertThat(updatedMeetup.getId()).isEqualTo(1L);

    }
}
