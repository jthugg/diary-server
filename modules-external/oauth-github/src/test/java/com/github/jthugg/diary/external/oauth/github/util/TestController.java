package com.github.jthugg.diary.external.oauth.github.util;

import com.github.jthugg.diary.external.oauth.OAuthUserData;
import com.github.jthugg.diary.external.oauth.github.client.GitHubOAuthClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final GitHubOAuthClient gitHubOAuthClient;

    @GetMapping("/oauth/github/client-id")
    public ResponseEntity<TestResponse.ClientId> clientId() {
        return ResponseEntity.ok(new TestResponse.ClientId(gitHubOAuthClient.getClientId()));
    }

    @PostMapping("/oauth/{provider}")
    public ResponseEntity<OAuthUserData> processOAuth(@PathVariable String provider, @RequestBody TestRequest request) {
        OAuthUserData response = gitHubOAuthClient.getUserData(request.code());
        log.info("provider: {}", provider);
        log.info("request: {}", request);
        log.info("response: {}", response);
        return ResponseEntity.ok(response);
    }

}
