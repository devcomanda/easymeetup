package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.service.MeetupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MeetupController.class)
public class MeetupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeetupService meetupService;

    private Meetup expectedMeetup;
    private Meetup nextMeetup;
    private List<Meetup> meetups = new ArrayList<>();

    @Before
    public void setup(){
        expectedMeetup = new Meetup();
        expectedMeetup.setId(1L);
        expectedMeetup.setMeetupName("Java for sceptics");

        nextMeetup = new Meetup();
        nextMeetup.setId(2L);
        nextMeetup.setMeetupName("Java approach in Chemistry");

        meetups.add(expectedMeetup);
        meetups.add(nextMeetup);
    }

    @Test
    public void saveMeetupTest() throws Exception {
        mockMvc.perform(post("/api/meetups")
                .content(asJsonString(expectedMeetup))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void findAllMeetupsTest() throws Exception{
        given(meetupService.findAllMeetups()).willReturn(meetups);
        mockMvc.perform(get("/api/meetups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isFound());
    }

    @Test
    public void returnNoContentStatus_whenFindAllMeetups() throws Exception{
        mockMvc.perform(get("/api/meetups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}