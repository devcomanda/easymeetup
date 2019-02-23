package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import com.devcomanda.easymeetup.model.entity.User;

public interface UserSecurityService {

    User register(NewUserRequest userRequest);
    User activate(String activationKey);
}
