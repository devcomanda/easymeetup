package com.devcomanda.easymeetup.service.security.oauth2.exceptions;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(final String message) {
        super(message);
    }
}
