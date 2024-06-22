package com.github.jthugg.diary.external.oauth.github.accessor;

import com.github.jthugg.diary.external.oauth.github.model.GitHubUserDataResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class GitHubDataAccessor {

    private final String url;
    private final RestTemplate restTemplate;

    public GitHubDataAccessor(String url, RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    public GitHubUserDataResponse getUserData(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, GitHubUserDataResponse.class).getBody();
    }

}
