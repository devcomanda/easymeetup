package com.devcomanda.easymeetup.service.security;

import com.devcomanda.easymeetup.service.security.jwt.JwtConfigurer;
import com.devcomanda.easymeetup.service.security.jwt.JwtFilter;
import com.devcomanda.easymeetup.service.security.jwt.TokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.util.ReflectionTestUtils;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class JwtFilterTest {

    private TokenProvider tokenProvider;

    private JwtFilter jwtFilter;

    @Before
    public void setUp() {
        this.tokenProvider = new TokenProvider();
        ReflectionTestUtils.setField(this.tokenProvider, "secretKey", "test secret");
        ReflectionTestUtils.setField(this.tokenProvider, "tokenValidityInMilliseconds", 60000);

        this.jwtFilter = new JwtFilter(this.tokenProvider);

        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    public void shouldJWTFilter() throws Exception {
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                "test-user",
                "test-password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))
        );

        String jwt = this.tokenProvider.createToken(authentication);

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(JwtConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        request.setRequestURI("/api/test");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockFilterChain filterChain = new MockFilterChain();

        this.jwtFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(SecurityContextHolder.getContext().getAuthentication().getName()).isEqualTo("test-user");
        assertThat(SecurityContextHolder.getContext().getAuthentication().getCredentials().toString()).isEqualTo(jwt);
    }

    @Test
    public void shouldJWTFilterInvalidToken() throws Exception {
        final String jwt = "wrong_jwt";

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(JwtConfigurer.AUTHORIZATION_HEADER, "Bearer " + jwt);
        request.setRequestURI("/api/test");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockFilterChain filterChain = new MockFilterChain();

        this.jwtFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    public void shouldJWTFilterMissingAuthorization() throws Exception {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("/api/test");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockFilterChain filterChain = new MockFilterChain();

        this.jwtFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    public void shouldJWTFilterMissingToken() throws Exception {

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(JwtConfigurer.AUTHORIZATION_HEADER, "Bearer ");
        request.setRequestURI("/api/test");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockFilterChain filterChain = new MockFilterChain();

        this.jwtFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    public void shouldJWTFilterWrongScheme() throws IOException, ServletException {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                "test-user",
                "test-password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))
        );

        final String jwt = this.tokenProvider.createToken(authentication);

        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(JwtConfigurer.AUTHORIZATION_HEADER, "Basic " + jwt);
        request.setRequestURI("/api/test");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        final MockFilterChain filterChain = new MockFilterChain();

        this.jwtFilter.doFilter(request, response, filterChain);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }
}
