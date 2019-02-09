package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final MailService mailService;

    public UserSecurityServiceImpl(UserRepository userRepository,
                                   UserService userService,
                                   MailService mailService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
    }

    @Override
    public User register(final NewUserRequest userRequest) {
        final User user = this.userService.saveUser(userRequest, false);
        this.mailService.sendActivationEmail(user);
        return user;
    }
}
