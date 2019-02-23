package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import com.devcomanda.easymeetup.factories.AuthoritiesFactory;
import com.devcomanda.easymeetup.factories.UsersFactory;
import com.devcomanda.easymeetup.repository.security.AuthorityRepository;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.model.entity.exceptions.UserNotFoundException;
import com.devcomanda.easymeetup.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


public class UserServiceTest {
    @Mock
    private AuthorityRepository authorityRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        this.userService = new UserServiceImpl(this.userRepository,
                this.passwordEncoder,
                this.authorityRepository);
    }

    @Test
    public void ReturnUserAfter_saveUserTest(){
        final NewUserRequest newUserRequest = UsersFactory.createUserRequest();
        final String encodedPassword = "user";

        Mockito.when(passwordEncoder.encode(any())).thenReturn(encodedPassword);

        Mockito.when(authorityRepository.findById("ROLE_USER"))
                .thenReturn(Optional.of(AuthoritiesFactory.userAuthority()));

        Mockito.doAnswer(AdditionalAnswers.returnsFirstArg()).when(this.userRepository)
                .save(any(User.class));

        final User user = userService.saveUser(newUserRequest, true);
        user.setPassword(UsersFactory.FIRST_USER_PLAIN_PASSWORD);

        assertThat(user.getEmail()).isEqualTo(UsersFactory.FIRST_USER_EMAIL);
        assertThat(user.getPassword()).isEqualTo(UsersFactory.FIRST_USER_PLAIN_PASSWORD);

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
