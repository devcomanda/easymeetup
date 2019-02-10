package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.factories.MeetupsFactory;
import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.service.MeetupService;
import com.devcomanda.easymeetup.service.security.jwt.TokenProvider;
import com.devcomanda.easymeetup.utils.TestUtils;
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

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = MeetupController.class, secure = false)
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

    @Test
    public void saveMeetupTest() throws Exception {
        mockMvc.perform(
                post("/api/meetups")
                        .content(TestUtils.convertObjectToJsonBytes(MeetupsFactory.firstMeetup()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void findAllMeetupsTest() throws Exception {

        given(meetupService.loadMeetups()).willReturn(Arrays.asList(
                MeetupsFactory.firstMeetup(),
                MeetupsFactory.secondMeetup()
        ));

        mockMvc.perform(get("/api/meetups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].name", is("Java approach in Chemistry")));
    }

    @Test
    public void returnNoContentStatus_whenFindAllMeetups() throws Exception {
        mockMvc.perform(
                get("/api/meetups")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateMeetupTest() throws Exception {

        Meetup meetup = MeetupsFactory.firstMeetup();

        when(meetupService.updateMeetup(eq(meetup)))
                .thenReturn(meetup);

        mockMvc.perform(
                put("/api/meetups/{1}", 1)
                        .content(TestUtils.convertObjectToJsonBytes(meetup))
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Java for sceptics"));
    }

    @Test
    public void loadMeetupByIdTest() throws Exception{
        given(meetupService.loadMeetup(1L)).willReturn(MeetupsFactory.firstMeetup());

        mockMvc.perform(get("/api/meetups/{1}", 1)
                .content(TestUtils.convertObjectToJsonBytes(MeetupsFactory.firstMeetup()))
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.name").value("Java for sceptics"));
    }
}