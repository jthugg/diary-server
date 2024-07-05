package com.github.jthugg.diary.gateway.gateway.config;

import com.github.jthugg.diary.gateway.gateway.filter.GlobalPostFilter;
import com.github.jthugg.diary.gateway.gateway.filter.GlobalPreFilter;
import com.github.jthugg.diary.gateway.gateway.route.RouteProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class RouteConfig {

    private final List<GlobalPreFilter> globalPreFilters;
    private final List<GlobalPostFilter> globalPostFilters;

    @Bean
    public RouterFunction<ServerResponse> pingService() {
        return buildRoute(RouteProperties.PING);
    }

    // add more beans to route...

    private RouterFunction<ServerResponse> buildRoute(RouteProperties routeProperties) {
        return applyGlobalFilter(GatewayRouterFunctions
                .route(routeProperties.getRouteId())
                .route(RequestPredicates.path(routeProperties.getPathPattern()), HandlerFunctions.https())
                .filter(LoadBalancerFilterFunctions.lb(routeProperties.getServiceId())))
                .build();
    }

    private RouterFunctions.Builder applyGlobalFilter(RouterFunctions.Builder builder) {
        globalPreFilters.forEach(builder::before);
        globalPostFilters.forEach(builder::after);
        return builder;
    }

}
