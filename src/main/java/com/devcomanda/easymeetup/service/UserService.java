package com.devcomanda.easymeetup.service;

import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import com.devcomanda.easymeetup.model.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UserService {
    User saveUser(NewUserRequest userReq, boolean isActive);

    User findUserById(Long id);

    static User getAuthenticatedUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}
