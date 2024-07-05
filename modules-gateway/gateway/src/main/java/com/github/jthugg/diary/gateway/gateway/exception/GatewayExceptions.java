package com.github.jthugg.diary.gateway.gateway.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GatewayExceptions {

    AUTHORIZATION_HEADER_NOT_FOUND(new AuthorizationHeaderNotFoundException()),
    AUTHORIZATION_TOKEN_EXPIRED(new AuthorizationTokenExpiredException()),
    AUTHORIZATION_TOKEN_MALFORMED(new AuthorizationTokenMalformedException()),
    AUTHORIZATION_TOKEN_REVOKED(new AuthorizationTokenRevokedException()),
    AUTHORIZATION_TYPE_NOT_MATCHED(new AuthorizationTypeNotMatchedException()),
    ;

    private final GatewayException exception;

}
