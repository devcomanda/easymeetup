package com.devcomanda.easymeetup.service.security;

import com.devcomanda.easymeetup.entity.Authority;
import com.devcomanda.easymeetup.entity.User;
import com.devcomanda.easymeetup.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Service
@Slf4j
public class DomainUserDetailsService implements UserDetailsService {

    final private UserRepository userRepository;

    @Autowired
    public DomainUserDetailsService(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        DomainUserDetailsService.log.debug("Authenticating {}", email);

        final String lowercaseEmail = email.toLowerCase(Locale.ENGLISH);

        return this.userRepository
                .findByEmail(lowercaseEmail)
                .map(user -> {
                    final List<GrantedAuthority> grantedAuthorities = buildGrantedAuthorities(user);
                    return buildUserDetails(lowercaseEmail, user.getPassword(), grantedAuthorities);
                })
                .orElseThrow(
                        () -> {
                            final String msg = "User with " + lowercaseEmail + " was not found in the database";
                            DomainUserDetailsService.log.warn(msg);
                            return new UsernameNotFoundException(msg);
                        }
                );
    }

    private List<GrantedAuthority> buildGrantedAuthorities(final User user) {
        return user
                .authorities()
                .stream()
                .map(Authority::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private org.springframework.security.core.userdetails.User buildUserDetails(
            final String lowercaseEmail,
            final String password,
            final List<GrantedAuthority> grantedAuthorities
    ) {
        return new org.springframework.security.core.userdetails.User(
                lowercaseEmail,
                password,
                grantedAuthorities
        );
    }
}
