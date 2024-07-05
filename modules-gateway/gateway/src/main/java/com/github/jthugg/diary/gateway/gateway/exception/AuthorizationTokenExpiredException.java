package com.github.jthugg.diary.gateway.gateway.exception;

public class AuthorizationTokenExpiredException extends GatewayAuthorizationException {

    public AuthorizationTokenExpiredException() {
        super("Authorization token has been expired.");
    }

}
