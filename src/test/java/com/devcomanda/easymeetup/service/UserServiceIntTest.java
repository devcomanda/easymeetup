package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.factories.UsersFactory;
import com.devcomanda.easymeetup.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntTest {

    @Autowired
    private UserService userService;

    @Test
    @Sql("classpath:/datasets/users/firstUser.sql")
    public void shouldFindUserByIdInt() {

        User given = UsersFactory.firstUser();

        User found = userService.findUserById(1L);

        assertThat(found.getEmail()).isEqualTo(given.getEmail());
    }
}
