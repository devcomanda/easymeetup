package com.devcomanda.easymeetup.factories;

import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import com.devcomanda.easymeetup.model.entity.User;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public final class UsersFactory {
    private UsersFactory() {}

    public static final Long FIRST_USER_ID = 1L;
    public static final String FIRST_USER_EMAIL = "email@email.com";
    public static final String FIRST_USER_PLAIN_PASSWORD = "user";
    public static final Boolean FIRST_USER_ACTIVATED_STATUS = true;

    public static User firstUser() {
        User user = new User(FIRST_USER_EMAIL, FIRST_USER_PLAIN_PASSWORD);
        user.addAuthorities(AuthoritiesFactory.userAuthority());
        return user;
    }

    public static NewUserRequest createUserRequest(){

        final NewUserRequest userRequest = new NewUserRequest();
        userRequest.setEmail(UsersFactory.FIRST_USER_EMAIL);
        userRequest.setPassword(UsersFactory.FIRST_USER_PLAIN_PASSWORD);

        return userRequest;
    }
}
