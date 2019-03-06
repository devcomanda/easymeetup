package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.model.security.InvalidUserActivationKeyException;
import com.devcomanda.easymeetup.service.UserSecurityService;
import com.devcomanda.easymeetup.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Aleksandr Beryozkin
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class ActivationUserTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserSecurityService userSecurityService;

    @MockBean
    private UserService userService;

    @Test
    public void shouldActivateNewUser() throws Exception{
        mvc.perform(get("/api/auth/verification")
                .param("code", "code"))
                .andExpect(status().is3xxRedirection());
    }

    @Test(expected = InvalidUserActivationKeyException.class)
    public void shouldReturnError() throws Exception{
        given(userSecurityService.activate("code")).willThrow(InvalidUserActivationKeyException.class);

        verify(userSecurityService.activate("code")).setActivationKey("code");

        mvc.perform(get("/api/auth/verification")
                .param("code", "code"));
    }
}
