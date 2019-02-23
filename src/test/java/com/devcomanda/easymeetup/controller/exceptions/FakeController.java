package com.devcomanda.easymeetup.controller.exceptions;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RestController
public class FakeController {

    @GetMapping("/test/access-denied")
    public void accessdenied() {
        throw new AccessDeniedException("test access denied!");
    }

    @GetMapping("/test/unauthorized")
    public void unauthorized() {
        throw new BadCredentialsException("test authentication failed!");
    }

    @GetMapping("/test/internal-server-error")
    public void internalServerError() {
        throw new RuntimeException();
    }
}

