package com.github.jthugg.diary.gateway.gateway.filter;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.function.BiFunction;

public interface GlobalPostFilter
        extends BiFunction<ServerRequest, ServerResponse, ServerResponse>, Ordered, Comparable<GlobalPostFilter> {
}
