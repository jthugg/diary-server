package com.github.jthugg.diary.gateway.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Allocate the identifier(UUID value) to every request for tracing.
 * Header 'X-Request-ID' would be added on every request.
 */
public class RequestTraceFilter implements GlobalFilter, Ordered {

    public static final String REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(mutateRequest(exchange));
    }

    private ServerWebExchange mutateRequest(ServerWebExchange exchange) {
        return exchange.mutate().request(setHeader(exchange.getRequest())).build();
    }

    private ServerHttpRequest setHeader(ServerHttpRequest request) {
        return request.mutate().header(REQUEST_ID_HEADER, UUID.randomUUID().toString()).build();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
