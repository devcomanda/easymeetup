package com.devcomanda.easymeetup.service.security.oauth2;

import static com.devcomanda.easymeetup.service.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE;
import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final HttpCookieOAuth2AuthorizationRequestRepository repo;

    @Autowired
    public OAuth2AuthenticationFailureHandler(final HttpCookieOAuth2AuthorizationRequestRepository requestRepository) {
        this.repo = requestRepository;
    }

    @Override
    public void onAuthenticationFailure(
        final HttpServletRequest request,
        final HttpServletResponse response,
        final AuthenticationException exception
    ) throws IOException {
        String targetUrl = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE)
            .map(Cookie::getValue)
            .orElse("/");

        targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
            .queryParam("error", exception.getLocalizedMessage())
            .build().toUriString();

        this.repo.removeAuthorizationRequestCookies(request, response);
        this.getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
