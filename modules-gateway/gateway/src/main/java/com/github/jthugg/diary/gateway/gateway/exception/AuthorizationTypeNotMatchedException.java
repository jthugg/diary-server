package com.github.jthugg.diary.gateway.gateway.exception;

public class AuthorizationTypeNotMatchedException extends GatewayAuthorizationException {

    public AuthorizationTypeNotMatchedException() {
        super("Authorization type not matched.");
    }

}
