package com.github.jthugg.diary.external.oauth.github.accessor;

import com.github.jthugg.diary.external.oauth.github.model.GitHubAuthRequestData;
import com.github.jthugg.diary.external.oauth.github.model.GitHubAuthenticationResponse;
import org.springframework.web.client.RestTemplate;

public class GithubAuthenticator {

    private final String url;
    private final String clientSecret;
    private final RestTemplate restTemplate;

    public GithubAuthenticator(String url, String clientSecret, RestTemplate restTemplate) {
        this.url = url;
        this.clientSecret = clientSecret;
        this.restTemplate = restTemplate;
    }

    public GitHubAuthenticationResponse getAccessToken(String code, String clientId) {
        GitHubAuthRequestData body = new GitHubAuthRequestData(code, clientId, clientSecret);
        return restTemplate.postForObject(url, body, GitHubAuthenticationResponse.class);
    }

}
