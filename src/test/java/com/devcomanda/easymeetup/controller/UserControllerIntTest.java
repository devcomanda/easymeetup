package com.devcomanda.easymeetup.controller;

import com.devcomanda.easymeetup.factories.UsersFactory;
import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.repository.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Alexander Beryozkin
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@TestPropertySource(properties = "classpath:/datasets/application.yml")
public class UserControllerIntTest {

    @Autowired
    private MockMvc mockMvc;

//    not working at the time due to current app configuration
    @Test
    @Ignore
    @Sql("classpath:/datasets/users/firstUser.sql")
    public void findUserByIdInt() throws Exception{

        mockMvc.perform(get("/api/accounts/{1}", 1))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$.email").value(UsersFactory.FIRST_USER_EMAIL));
    }
}
