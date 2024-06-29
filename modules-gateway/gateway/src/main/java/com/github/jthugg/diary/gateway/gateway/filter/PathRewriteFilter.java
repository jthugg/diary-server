package com.github.jthugg.diary.gateway.gateway.filter;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.function.ServerRequest;

public class PathRewriteFilter implements GlobalPreFilter {

    private static final String REGEXP = "^/[^/]+(?<segment>)";
    private static final String REPLACEMENT = "/${segment}";

    @Override
    public ServerRequest apply(ServerRequest request) {
        return BeforeFilterFunctions.rewritePath(REGEXP, REPLACEMENT).apply(request);
    }

    @Override
    public int compareTo(GlobalPreFilter filter) {
        return Integer.compare(filter.getOrder(), getOrder());
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
