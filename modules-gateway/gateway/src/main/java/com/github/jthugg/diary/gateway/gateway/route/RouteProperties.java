package com.github.jthugg.diary.gateway.gateway.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RouteProperties {

    PING(
            "PING-SERVICE",
            "/ping/**",
            "/ping(?<segment>/?.*?)/*$",
            "/${segment}",
            "PING-SERVICE"
    ),
    ;

    private final String routeId;
    private final String pathPattern;
    private final String rewriteRegexp;
    private final String rewriteReplacement;
    private final String serviceId;

}
