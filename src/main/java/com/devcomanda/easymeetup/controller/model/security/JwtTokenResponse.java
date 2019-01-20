package com.devcomanda.easymeetup.controller.model.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Getter
@AllArgsConstructor
@ToString
public class JwtTokenResponse {
    private String token;
}
