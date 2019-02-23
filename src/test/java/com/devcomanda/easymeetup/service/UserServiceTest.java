package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.factories.UsersFactory;
import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.model.entity.exceptions.UserNotFoundException;
import com.devcomanda.easymeetup.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Alexander Beryozkin
 */

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldReturnUserById() {

        User firstUser = UsersFactory.firstUser();

        when(userRepository.findById(UsersFactory.FIRST_USER_ID)).thenReturn(Optional.of(firstUser));

        User user = userService.findUserById(UsersFactory.FIRST_USER_ID);

        assertThat(user).isEqualTo(firstUser).isEqualToComparingFieldByField(firstUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowExceptionIfUserNotFound() {

        when(userRepository.findById(UsersFactory.FIRST_USER_ID)).thenReturn(Optional.empty());

        userService.findUserById(UsersFactory.FIRST_USER_ID);
    }
}
