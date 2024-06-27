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
            "lb://PING-SERVICE"
    ),
    ;

    private final String service;
    private final String path;
    private final String regexp;
    private final String replacement;
    private final String target;

}
