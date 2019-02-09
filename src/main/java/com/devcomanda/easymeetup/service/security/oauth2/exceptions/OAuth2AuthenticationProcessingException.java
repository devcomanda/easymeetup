package com.devcomanda.easymeetup.service.security.oauth2.exceptions;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class OAuth2AuthenticationProcessingException extends RuntimeException {
    public OAuth2AuthenticationProcessingException(final String message) {
        super(message);
    }
}
