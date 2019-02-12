package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.factories.UsersFactory;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aleksandr Beryozkin
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
@WithMockUser
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void findUserByIdTest() throws Exception {
        given(userService.findUserById(1L)).willReturn(UsersFactory.firstUser());

        mockMvc.perform(get("/api/accounts/{1}", 1)
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
        )
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.email").value("email@email.com"));
    }
}
