package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import com.devcomanda.easymeetup.model.entity.User;

public interface UserService {
    User saveUser(NewUserRequest userReq, boolean isActive);

    User findUserById(Long id);
}
