package com.devcomanda.easymeetup.service.security.oauth2;

import com.devcomanda.easymeetup.model.entity.User;
import com.devcomanda.easymeetup.model.entity.enums.AuthProvider;
import com.devcomanda.easymeetup.model.security.UserPrincipal;
import com.devcomanda.easymeetup.model.security.oauth2.GithubOAuth2UserInfo;
import com.devcomanda.easymeetup.model.security.oauth2.OAuth2UserInfo;
import com.devcomanda.easymeetup.repository.UserRepository;
import com.devcomanda.easymeetup.service.security.oauth2.exceptions.OAuth2AuthenticationProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Service
public class DomainOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Autowired
    public DomainOAuth2UserService(final UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(final OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        final OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return this.processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (final AuthenticationException ex) {
            throw ex;
        } catch (final RuntimeException ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {

        // TODO we need more providers, that means we need a factory or some like that
        final OAuth2UserInfo oAuth2UserInfo = new GithubOAuth2UserInfo(
            oAuth2User.getAttributes()
        );
        // TODO in some cases github doesn't return email
        // for example if user doesn't set public email,
        // we should send additional getting email request  if it doesn't get through first api call
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        // TODO add a logic that checks equality between the current provider and already saved in the user entity
        // TODO need a method that updates existing user after successful login
        final User user = this.userRepository
            .findByEmail(oAuth2UserInfo.getEmail())
            .orElseGet(() -> this.registerNewUser(oAuth2UserRequest, oAuth2UserInfo));

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(
        final OAuth2UserRequest oAuth2UserRequest,
        final OAuth2UserInfo oAuth2UserInfo
    ) {
        final User user = User.builder()
            .email(oAuth2UserInfo.getEmail())
            .providerId(oAuth2UserInfo.getId())
            // TODO we have to use case insensitive method for looking for provider constant
            .authProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
            .build();

        return this.userRepository.save(user);
    }
}
