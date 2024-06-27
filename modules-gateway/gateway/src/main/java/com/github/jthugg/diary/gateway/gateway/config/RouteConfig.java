package com.github.jthugg.diary.gateway.gateway.config;

import com.github.jthugg.diary.gateway.gateway.route.function.PingRouteFunction;
import com.github.jthugg.diary.gateway.gateway.route.RouteProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Request routing configuration class.
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(RouteProperties.PING.getService(), new PingRouteFunction())
                .build();
    }

}
