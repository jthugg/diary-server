package com.github.jthugg.diary.external.oauth;

public interface OAuthClient {

    String getClientId();
    OAuthUserData getUserData(String code);

}
