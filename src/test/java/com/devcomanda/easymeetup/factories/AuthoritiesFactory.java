package com.devcomanda.easymeetup.factories;

import com.devcomanda.easymeetup.model.entity.Authority;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public final class AuthoritiesFactory {
    private AuthoritiesFactory(){}

    public static final String USER_AUTHORITY_NAME = "ROLE_USER";

    public static final Authority userAuthority(){
        return new Authority(USER_AUTHORITY_NAME);
    }
}
