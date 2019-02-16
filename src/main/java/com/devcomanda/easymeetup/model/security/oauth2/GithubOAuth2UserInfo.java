package com.devcomanda.easymeetup.model.security.oauth2;

import java.util.Map;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public class GithubOAuth2UserInfo extends OAuth2UserInfo {

    public GithubOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return ((Integer) this.attributes.get("id")).toString();
    }

    @Override
    public String getName() {
        return (String) this.attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) this.attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) this.attributes.get("avatar_url");
    }
}
