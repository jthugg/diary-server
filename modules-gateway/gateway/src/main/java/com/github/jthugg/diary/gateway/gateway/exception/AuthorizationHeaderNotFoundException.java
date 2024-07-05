package com.github.jthugg.diary.gateway.gateway.exception;

public class AuthorizationHeaderNotFoundException extends GatewayAuthorizationException {

    public AuthorizationHeaderNotFoundException() {
        super("Authorization header not found.");
    }

}
