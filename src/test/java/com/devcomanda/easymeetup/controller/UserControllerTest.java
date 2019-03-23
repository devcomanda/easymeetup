package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.factories.MeetupsFactory;
import com.devcomanda.easymeetup.factories.UsersFactory;
<<<<<<< HEAD
import com.devcomanda.easymeetup.service.UserSecurityService;
=======
import com.devcomanda.easymeetup.model.entity.Meetup;
import com.devcomanda.easymeetup.service.MeetupService;
>>>>>>> 4aa03264dfce2e5811a511b6fc43e094a0fa089a
import com.devcomanda.easymeetup.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aleksandr Beryozkin
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private MeetupService meetupService;

    @Test
    public void findUserByIdTest() throws Exception {
        given(userService.findUserById(1L)).willReturn(UsersFactory.firstUser());

        mockMvc.perform(get("/api/accounts/{1}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.email").value("email@email.com"));
    }

    @Test
    @WithMockUser
    public void loadUserMeetupsHistory() throws Exception{
        List<Meetup> meetups = new ArrayList<>();
        meetups.add(MeetupsFactory.firstMeetup());

        given(meetupService.loadUserMeetupHistory())
                .willReturn(meetups);

        mockMvc.perform(get("/api/accounts/meetups")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isFound());

    }
}
