package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.entity.Meetup;
import com.devcomanda.easymeetup.service.MeetupService;
import com.devcomanda.easymeetup.service.security.jwt.TokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MeetupController.class)
@WithMockUser
@MockBeans(value = {
        @MockBean(UserDetailsService.class),
        @MockBean(TokenProvider.class)
})
public class MeetupControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeetupService meetupService;

    private Meetup expectedMeetup;
    private Meetup nextMeetup;
    private List<Meetup> meetups = new ArrayList<>();

    @Before
    public void setup() {
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
    public void findAllMeetupsTest() throws Exception {
        given(meetupService.findAllMeetups()).willReturn(meetups);
        mockMvc.perform(get("/api/meetups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].meetupName", is("Java approach in Chemistry")));
    }

    @Test
    public void returnNoContentStatus_whenFindAllMeetups() throws Exception {
        mockMvc.perform(get("/api/meetups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateMeetupTest() throws Exception {
        given(meetupService.findMeetuById(expectedMeetup.getId())).willReturn(expectedMeetup);
        when(meetupService.saveMeetup(expectedMeetup)).thenReturn(expectedMeetup);
        mockMvc.perform(put("/api/meetups/{1}", 1)
                .content(asJsonString(expectedMeetup))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.meetupName").value("Java for sceptics"));
    }

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}