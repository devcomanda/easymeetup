package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.repository.MeetupRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class MeetupServiceImplTest {

    @Mock
    private MeetupRepository meetupRepository;

    private MeetupService meetupService;

    private Meetup expectedMeetup;

    @Before
    public void setup(){
        meetupService = new MeetupServiceImpl(meetupRepository);
        expectedMeetup = new Meetup();
        expectedMeetup.setMeetupName("DNA programming with java");
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
}
