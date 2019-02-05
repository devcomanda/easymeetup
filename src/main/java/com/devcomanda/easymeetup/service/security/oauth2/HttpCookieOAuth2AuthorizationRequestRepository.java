package com.devcomanda.easymeetup.service.security.oauth2;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
@Component
public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {

    public static final String AUTHORIZATION_REQUEST_COOKIE = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE = "redirect_uri";

    private static final int cookieExpireSeconds = 180;

    @Override
    public OAuth2AuthorizationRequest loadAuthorizationRequest(final HttpServletRequest request) {
        return CookieUtils.getCookie(
            request,
            HttpCookieOAuth2AuthorizationRequestRepository.AUTHORIZATION_REQUEST_COOKIE
        )
            .map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
            .orElse(null);
    }

    @Override
    public void saveAuthorizationRequest(
        final OAuth2AuthorizationRequest authorizationRequest,
        final HttpServletRequest request,
        final HttpServletResponse response
    ) {

        if (authorizationRequest == null) {
            CookieUtils.deleteCookie(request, response, HttpCookieOAuth2AuthorizationRequestRepository.AUTHORIZATION_REQUEST_COOKIE);
            CookieUtils.deleteCookie(request, response, HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE);
            return;
        }

        CookieUtils.addCookie(
            response,
            HttpCookieOAuth2AuthorizationRequestRepository.AUTHORIZATION_REQUEST_COOKIE,
            CookieUtils.serialize(authorizationRequest),
            HttpCookieOAuth2AuthorizationRequestRepository.cookieExpireSeconds
        );

        final String redirectUriAfterLogin = request
            .getParameter(
                HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE
            );

        if (StringUtils.isNotBlank(redirectUriAfterLogin)) {

            CookieUtils.addCookie(
                response,
                HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE,
                redirectUriAfterLogin, HttpCookieOAuth2AuthorizationRequestRepository.cookieExpireSeconds
            );
        }
    }

    @Override
    public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
        return this.loadAuthorizationRequest(request);
    }

    public void removeAuthorizationRequestCookies(
        final HttpServletRequest request,
        final HttpServletResponse response
    ) {

        CookieUtils.deleteCookie(
            request,
            response,
            HttpCookieOAuth2AuthorizationRequestRepository.AUTHORIZATION_REQUEST_COOKIE
        );

        CookieUtils.deleteCookie(
            request,
            response,
            HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE
        );
    }
}
