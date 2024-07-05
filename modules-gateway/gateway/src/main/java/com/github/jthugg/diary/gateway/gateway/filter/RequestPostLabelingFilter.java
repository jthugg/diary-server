package com.github.jthugg.diary.gateway.gateway.filter;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

public class RequestPostLabelingFilter implements GlobalPostFilter {

    @Override
    public ServerResponse apply(ServerRequest serverRequest, ServerResponse serverResponse) {
        serverResponse.headers().add(
                RequestTraceFilter.REQUEST_ID_HEADER,
                serverRequest.headers().firstHeader(RequestTraceFilter.REQUEST_ID_HEADER)
        );
        return serverResponse;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
