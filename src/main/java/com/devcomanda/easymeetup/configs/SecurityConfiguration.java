package com.devcomanda.easymeetup.configs;

import com.devcomanda.easymeetup.service.security.jwt.JwtConfigurer;
import com.devcomanda.easymeetup.service.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@EnableWebSecurity
@Import(SecurityProblemSupport.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TokenProvider tokenProvider;
    private final AuthorizationRequestRepository<OAuth2AuthorizationRequest> cookieAuthorizationRequestRepository;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> domainOAuth2UserService;
    private final AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    private final SecurityProblemSupport problemSupport;

    @Autowired
    public SecurityConfiguration(
            @Qualifier("domainUserDetails") final UserDetailsService userDetailsService,
            final TokenProvider tokenProvider,
            final AuthorizationRequestRepository<OAuth2AuthorizationRequest> cookieAuthorizationRequestRepository,
            final OAuth2UserService<OAuth2UserRequest, OAuth2User> domainOAuth2UserService,
            final AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
            final AuthenticationFailureHandler oAuth2AuthenticationFailureHandler,
            final SecurityProblemSupport problemSupport
    ) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
        this.cookieAuthorizationRequestRepository = cookieAuthorizationRequestRepository;
        this.domainOAuth2UserService = domainOAuth2UserService;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
        this.problemSupport = problemSupport;
    }

    @Bean
    //TODO change to the strongest level
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .userDetailsService(this.userDetailsService)
            .exceptionHandling()
                .authenticationEntryPoint(this.problemSupport)
                .accessDeniedHandler(this.problemSupport)
            .and()
            .csrf()
                .disable()
            .headers()
                .frameOptions()
                    .disable()
            .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers("/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            .and()
            .oauth2Login()
                .authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(this.cookieAuthorizationRequestRepository)
            .and()
                .redirectionEndpoint()
                    .baseUri("/oauth2/callback/*")
            .and()
                .userInfoEndpoint()
                    .userService(this.domainOAuth2UserService)
            .and()
                .successHandler(this.oAuth2AuthenticationSuccessHandler)
                .failureHandler(this.oAuth2AuthenticationFailureHandler)
            .and()
                .apply(this.securityConfigurerAdapter());
    }

    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(this.tokenProvider);
    }

    @Bean(name = "apiAuthManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
