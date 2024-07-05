package com.github.jthugg.diary.gateway.gateway.route;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RouteProperties {

    PING(
            "PING-SERVICE",
            "/ping/**",
            "PING-SERVICE"
    ),
    ;

    private final String routeId;
    private final String pathPattern;
    private final String serviceId;

}
