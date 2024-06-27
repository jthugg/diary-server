package com.github.jthugg.diary.gateway.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
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
        String requestId = UUID.randomUUID().toString();
        return chain.filter(mutate(exchange, requestId));
    }

    private ServerWebExchange mutate(ServerWebExchange exchange, String requestId) {
        ServerWebExchange.Builder builder = exchange.mutate();
        builder.request(setRequest(exchange.getRequest(), requestId));
        setResponse(exchange.getResponse(), requestId);
        return builder.build();
    }

    private ServerHttpRequest setRequest(ServerHttpRequest request, String requestId) {
        return request.mutate().header(REQUEST_ID_HEADER, requestId).build();
    }

    private void setResponse(ServerHttpResponse response, String requestId) {
        response.getHeaders().add(REQUEST_ID_HEADER, requestId);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
