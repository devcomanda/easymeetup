package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.repository.MeetupRepository;
import com.devcomanda.easymeetup.service.MeetupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(MeetupController.class)
public class MeetupControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MeetupService meetupService;
    private MeetupController meetupController;
    @MockBean
    private MeetupRepository meetupRepository;
    private Meetup expectedMeetup;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        meetupController = new MeetupController(meetupService);
        expectedMeetup = new Meetup();
        expectedMeetup.setMeetupName("Java");
    }

    @Test
    public void saveMeetupTest() throws Exception {
        when(meetupRepository.save(expectedMeetup))
                .thenReturn(expectedMeetup);
        Meetup actualMeetup = meetupController.saveMeetup(expectedMeetup);
        assertThat(expectedMeetup).isEqualTo(actualMeetup);
    }
}
