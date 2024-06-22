package com.github.jthugg.diary.external.oauth.github.client;

import com.github.jthugg.diary.external.oauth.OAuthClient;
import com.github.jthugg.diary.external.oauth.OAuthUserData;
import com.github.jthugg.diary.external.oauth.github.accessor.GitHubDataAccessor;
import com.github.jthugg.diary.external.oauth.github.accessor.GithubAuthenticator;
import com.github.jthugg.diary.external.oauth.github.model.GitHubAuthenticationResponse;
import com.github.jthugg.diary.external.oauth.github.model.GitHubUserDataResponse;

public class GitHubOAuthClient implements OAuthClient {

    public static final String PROVIDER = "GITHUB";

    private final String clientId;
    private final GithubAuthenticator authenticator;
    private final GitHubDataAccessor dataAccessor;

    public GitHubOAuthClient(String clientId, GithubAuthenticator authenticator, GitHubDataAccessor dataAccessor) {
        this.clientId = clientId;
        this.authenticator = authenticator;
        this.dataAccessor = dataAccessor;
    }

    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public OAuthUserData getUserData(String code) {
        GitHubAuthenticationResponse authResult = authenticator.getAccessToken(code, clientId);
        GitHubUserDataResponse userData = dataAccessor.getUserData(authResult.accessToken());
        return new OAuthUserData(PROVIDER, userData.id(), authResult.accessToken());
    }

}
