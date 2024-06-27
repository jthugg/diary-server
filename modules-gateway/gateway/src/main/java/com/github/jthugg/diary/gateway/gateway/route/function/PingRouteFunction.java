package com.github.jthugg.diary.gateway.gateway.route.function;

import com.github.jthugg.diary.gateway.gateway.route.RouteProperties;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

public class PingRouteFunction implements RouteFunction {

    @Override
    public Buildable<Route> apply(PredicateSpec predicateSpec) {
        return predicateSpec.path(RouteProperties.PING.getPath())
                .filters(filter -> filter.rewritePath(
                        RouteProperties.PING.getRegexp(),
                        RouteProperties.PING.getReplacement()
                ))
                .uri(RouteProperties.PING.getTarget());
    }

}
