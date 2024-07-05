package com.github.jthugg.diary.gateway.gateway.filter;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.UUID;

public class RequestTraceFilter implements GlobalPreFilter {

    public static final String REQUEST_ID_HEADER = "X-Request-ID";

    @Override
    public ServerRequest apply(ServerRequest serverRequest) {
        return ServerRequest.from(serverRequest)
                .header(REQUEST_ID_HEADER, UUID.randomUUID().toString())
                .build();
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
