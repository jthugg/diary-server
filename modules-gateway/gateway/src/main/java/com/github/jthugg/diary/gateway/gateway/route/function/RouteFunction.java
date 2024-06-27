package com.github.jthugg.diary.gateway.gateway.route.function;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

import java.util.function.Function;

public interface RouteFunction extends Function<PredicateSpec, Buildable<Route>> {
}
