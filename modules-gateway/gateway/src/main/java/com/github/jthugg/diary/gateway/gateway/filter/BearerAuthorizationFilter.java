package com.github.jthugg.diary.gateway.gateway.filter;

import com.github.jthugg.diary.data.token.storage.exception.TokenStorageException;
import com.github.jthugg.diary.data.token.storage.repository.TokenRepository;
import com.github.jthugg.diary.gateway.gateway.exception.GatewayException;
import com.github.jthugg.diary.gateway.gateway.exception.GatewayExceptions;
import com.github.jthugg.diary.util.jwt.JwtVerifier;
import com.github.jthugg.diary.util.jwt.exception.JwtException;
import com.github.jthugg.diary.util.jwt.model.ResolvedJwt;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.function.ServerRequest;

public class BearerAuthorizationFilter extends AuthorizationFilter {

    private static final String AUTH_TYPE_PREFIX = "Bearer ";
    private static final String USER_ID = "X-Request-User-Id";
    private static final String ROLE = "X-Request-User-Role";

    private final TokenRepository tokenRepository;
    private final JwtVerifier jwtVerifier;

    public BearerAuthorizationFilter(TokenRepository tokenRepository, JwtVerifier jwtVerifier) {
        this.tokenRepository = tokenRepository;
        this.jwtVerifier = jwtVerifier;
    }

    @Override
    public ServerRequest apply(ServerRequest serverRequest) {
        // TODO: Execute query to token storage as an async task.
        try {
            String authHeaderValue = getAuthHeaderValue(serverRequest);
            String tokenValue = getTokenValue(authHeaderValue);
            ResolvedJwt resolvedJwt = jwtVerifier.verify(tokenValue);
            tokenRepository.findAccessToken(tokenValue);
            return mutateRequest(serverRequest, resolvedJwt.getUserId(), resolvedJwt.getUserRole());
        } catch (JwtException
                | GatewayException
                | TokenStorageException exception) {
            return serverRequest;
        }
    }

    private String getAuthHeaderValue(ServerRequest serverRequest) {
        String headerValue = serverRequest.headers().firstHeader(HttpHeaders.AUTHORIZATION);
        if (headerValue == null) {
            throw GatewayExceptions.AUTHORIZATION_HEADER_NOT_FOUND.getException();
        }
        return headerValue;
    }

    private String getTokenValue(String headerValue) {
        if (!headerValue.startsWith(AUTH_TYPE_PREFIX)) {
            throw GatewayExceptions.AUTHORIZATION_TYPE_NOT_MATCHED.getException();
        }
        String tokenValue = headerValue.substring(AUTH_TYPE_PREFIX.length());
        if (tokenValue.isBlank()) {
            throw GatewayExceptions.AUTHORIZATION_HEADER_NOT_FOUND.getException();
        }
        return tokenValue;
    }

    private ServerRequest mutateRequest(ServerRequest request, String userId, String userRole) {
        return ServerRequest.from(request)
                .header(USER_ID, userId)
                .header(ROLE, userRole)
                .build();
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
