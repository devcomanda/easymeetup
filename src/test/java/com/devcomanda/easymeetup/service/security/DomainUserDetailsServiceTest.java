package com.devcomanda.easymeetup.service.security;

import com.devcomanda.easymeetup.factories.UsersFactory;
import com.devcomanda.easymeetup.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class DomainUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserDetailsService detailsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.detailsService = new DomainUserDetailsService(this.userRepository);
    }

    @Test
    public void shouldReturnUserDetailsByEmail() {

        when(this.userRepository.findByEmail(eq(UsersFactory.FIRST_USER_EMAIL)))
                .thenReturn(Optional.of(UsersFactory.firstUser()));

        UserDetails userDetails = this.detailsService.loadUserByUsername(UsersFactory.FIRST_USER_EMAIL);

        assertThat(userDetails.getUsername())
                .isEqualTo(UsersFactory.FIRST_USER_EMAIL);

        assertThat(userDetails.getAuthorities())
                .hasSize(1);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowExceptionIfUserNotFound() {
        when(this.userRepository.findByEmail(eq(UsersFactory.FIRST_USER_EMAIL)))
                .thenReturn(Optional.empty());

        this.detailsService.loadUserByUsername(UsersFactory.FIRST_USER_EMAIL);
    }

}
