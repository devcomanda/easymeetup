package com.devcomanda.easymeetup.model.security.oauth2;

import java.util.Collections;
import java.util.Map;

/**
 * @author Danil Kuznetsov (kuznetsov.danil.v@gmail.com)
 */
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;

    public OAuth2UserInfo(final Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
