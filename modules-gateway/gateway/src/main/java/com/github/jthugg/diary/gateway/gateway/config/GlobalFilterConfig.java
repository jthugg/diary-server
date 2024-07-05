package com.github.jthugg.diary.gateway.gateway.config;

import com.github.jthugg.diary.gateway.gateway.filter.PathRewriteFilter;
import com.github.jthugg.diary.gateway.gateway.filter.RequestPostLabelingFilter;
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
    public RequestPostLabelingFilter requestPostLabelingFilter() {
        return new RequestPostLabelingFilter();
    }

    @Bean
    public PathRewriteFilter pathRewriteFilter() {
        return new PathRewriteFilter();
    }

}
