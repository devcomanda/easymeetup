package com.devcomanda.easymeetup.controller.security;

import com.devcomanda.easymeetup.controller.model.security.JwtTokenResponse;
import com.devcomanda.easymeetup.controller.model.security.LoginRequest;
import com.devcomanda.easymeetup.service.security.jwt.JwtConfigurer;
import com.devcomanda.easymeetup.service.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@RestController
@RequestMapping("/api/auth")
public class UserJwtResource {

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserJwtResource(
            final TokenProvider tokenProvider,
            @Qualifier("apiAuthManager") final AuthenticationManager authenticationManager
    ) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> authorize(final @RequestBody LoginRequest login) {

        final UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(
                        login.getEmail(),
                        login.getPassword()
                );

        final Authentication authentication = this.authenticationManager.authenticate(authToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = this.tokenProvider.createToken(authentication);
        final HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add(JwtConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JwtTokenResponse(jwt), httpHeaders, HttpStatus.OK);
    }
}