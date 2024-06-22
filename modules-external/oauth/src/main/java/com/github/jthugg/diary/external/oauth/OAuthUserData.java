package com.github.jthugg.diary.external.oauth;

public record OAuthUserData(
        String provider,
        String oauthIdentifier,
        String accessToken,
        String refreshToken
) {

    public OAuthUserData(String provider, String oauthIdentifier, String accessToken) {
        this(provider, oauthIdentifier, accessToken, null);
    }

}
