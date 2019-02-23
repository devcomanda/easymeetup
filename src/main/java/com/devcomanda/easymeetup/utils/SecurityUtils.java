package com.devcomanda.easymeetup.utils;


import com.devcomanda.easymeetup.controller.model.security.NewUserRequest;
import org.springframework.util.StringUtils;

public final class SecurityUtils {
    public SecurityUtils() {
    }

    public static boolean checkPasswordLength(final String password){
        return !StringUtils.isEmpty(password) &&
                password.length() >= NewUserRequest.PASSWORD_WITH_MIN_LENGTH &&
                password.length() <= NewUserRequest.PASSWORD_WITH_MAX_LENGTH;
    }
}
