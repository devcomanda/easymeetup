package com.devcomanda.easymeetup.controller.exceptions;

import com.devcomanda.easymeetup.EasyMeetupApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.zalando.problem.spring.web.advice.MediaTypes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyMeetupApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ExceptionHandlerIntTest {

    private FakeController controller;

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        controller = new FakeController();

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(exceptionHandler)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    public void shouldReturnAccessDenied() throws Exception {
        mockMvc.perform(
                get("/test/access-denied")
        )
                .andExpect(status().isForbidden())
                .andExpect(content().contentType(MediaTypes.PROBLEM))
                .andExpect(jsonPath("$.title").value("Forbidden"))
                .andExpect(jsonPath("$.detail").value("test access denied!"));
    }

    @Test
    public void shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(
                get("/test/unauthorized")
        )
                .andExpect(status().isUnauthorized())
                .andExpect(content().contentType(MediaTypes.PROBLEM))
                .andExpect(jsonPath("$.title").value("Unauthorized"))
                .andExpect(jsonPath("$.detail").value("test authentication failed!"));
    }

    @Test
    public void shouldReturnMethodNotSupported() throws Exception {
        mockMvc.perform(
                post("/test/access-denied")
        )
                .andExpect(status().isMethodNotAllowed())
                .andExpect(content().contentType(MediaTypes.PROBLEM))
                .andExpect(jsonPath("$.title").value("Method Not Allowed"))
                .andExpect(jsonPath("$.detail").value("Request method 'POST' not supported"));
    }


    @Test
    public void shouldReturnInternalServerError() throws Exception {
        mockMvc.perform(
                get("/test/internal-server-error")
        )
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType(MediaTypes.PROBLEM))
                .andExpect(jsonPath("$.title").value("Internal Server Error"));
    }
}
