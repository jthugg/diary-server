package com.github.jthugg.diary.gateway.gateway.config;

import com.github.jthugg.diary.gateway.gateway.filter.RequestAuthorizationFilter;
import com.github.jthugg.diary.gateway.gateway.filter.RequestTraceFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalFilterConfig {

    @Bean
    public RequestTraceFilter requestTraceFilter() {
        return new RequestTraceFilter();
    }

    @Bean
    public RequestAuthorizationFilter requestAuthorizationFilter() {
        return new RequestAuthorizationFilter();
    }

}
