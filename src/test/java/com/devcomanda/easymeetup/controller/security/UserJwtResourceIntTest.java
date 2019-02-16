package com.devcomanda.easymeetup.controller.security;

import com.devcomanda.easymeetup.controller.model.security.LoginRequest;
import com.devcomanda.easymeetup.utils.TestUtils;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:/datasets/users/firstUser.sql")
public class UserJwtResourceIntTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnTokenAfterSuccessfulAuthorize() throws Exception {
        final LoginRequest login = new LoginRequest("email@email.com", "user");

        this.mvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(login))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(header().string("Authorization", not(nullValue())))
                .andExpect(header().string("Authorization", not(isEmptyString())));
    }

    @Test
    @Ignore
    // TODO we should reconfigure security configuration in the future tasks
    // After that, we enable it again
    public void shouldReturnUnathorizatedStatusIfPasswordWrong() throws Exception {

        final LoginRequest login = new LoginRequest("email@email.com", "invalid");

        this.mvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(login))
        )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.token").doesNotExist())
                .andExpect(header().doesNotExist("Authorization"));
    }

    @Test
    @Ignore
    // TODO we should reconfigure security configuration in the future tasks
    // After that, we enable it again
    public void shouldReturnUnathorizatedStatusIfEmailNotFound() throws Exception {

        final LoginRequest login = new LoginRequest("invalid@email.com", "user");

        this.mvc.perform(
                post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.convertObjectToJsonBytes(login))
        )
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.token").doesNotExist())
                .andExpect(header().doesNotExist("Authorization"));
    }

}
