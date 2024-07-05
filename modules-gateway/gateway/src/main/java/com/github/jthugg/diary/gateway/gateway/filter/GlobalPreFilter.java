package com.github.jthugg.diary.gateway.gateway.filter;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.function.Function;

public interface GlobalPreFilter
        extends Function<ServerRequest, ServerRequest>, Ordered, Comparable<GlobalPreFilter> {

    @Override
    default int compareTo(GlobalPreFilter filter) {
        return Integer.compare(filter.getOrder(), getOrder());
    }

}
