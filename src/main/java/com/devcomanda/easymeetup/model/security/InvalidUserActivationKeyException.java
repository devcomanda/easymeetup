package com.devcomanda.easymeetup.model.security;

import com.devcomanda.easymeetup.configs.constants.LocalConstants;

public class InvalidUserActivationKeyException extends RuntimeException {
    public InvalidUserActivationKeyException() {
        super(LocalConstants.INVALID_ACTIVATION_KEY);
    }
}
