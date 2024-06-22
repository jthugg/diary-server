package com.github.jthugg.diary.external.oauth.github.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record GitHubAuthenticationResponse(
        String accessToken,
        String tokenType,
        String scope
) {}
