package com.github.jthugg.diary.external.oauth.github.util;

import com.github.jthugg.diary.external.oauth.github.accessor.GitHubDataAccessor;
import com.github.jthugg.diary.external.oauth.github.accessor.GithubAuthenticator;
import com.github.jthugg.diary.external.oauth.github.client.GitHubOAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class TestConfig implements WebMvcConfigurer {

    private final String authenticationUrl;
    private final String dataAccessUrl;
    private final String clientId;
    private final String clientSecret;

    public TestConfig(
            @Value("${oauth2.github.authorizationServer}") String authenticationUrl,
            @Value("${oauth2.github.apiServer}") String dataAccessUrl,
            @Value("${oauth2.github.clientId}") String clientId,
            @Value("${oauth2.github.clientSecret}") String clientSecret
    ) {
        this.authenticationUrl = authenticationUrl;
        this.dataAccessUrl = dataAccessUrl;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/")
                .setCacheControl(CacheControl.noCache());
    }

    @Bean
    public RestTemplate authenticationRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(List.of(((request, body, execution) -> {
            HttpHeaders headers = request.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.setAcceptCharset(List.of(StandardCharsets.UTF_8));
            return execution.execute(request, body);
        })));
        return template;
    }

    @Bean
    public RestTemplate dataAccessRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.setInterceptors(List.of(((request, body, execution) -> {
            HttpHeaders headers = request.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            headers.setAcceptCharset(List.of(StandardCharsets.UTF_8));
            return execution.execute(request, body);
        })));
        return template;
    }

    @Bean
    public GithubAuthenticator githubAuthenticator() {
        return new GithubAuthenticator(authenticationUrl, clientSecret, authenticationRestTemplate());
    }

    @Bean
    public GitHubDataAccessor githubDataAccessor() {
        return new GitHubDataAccessor(dataAccessUrl, dataAccessRestTemplate());
    }

    @Bean
    public GitHubOAuthClient gitHubOAuthClient() {
        return new GitHubOAuthClient(clientId, githubAuthenticator(), githubDataAccessor());
    }

}
