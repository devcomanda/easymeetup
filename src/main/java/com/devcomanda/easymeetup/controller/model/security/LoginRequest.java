package com.devcomanda.easymeetup.controller.model.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Getter
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@ToString
public class LoginRequest {

    private String email;

    private String password;
}
