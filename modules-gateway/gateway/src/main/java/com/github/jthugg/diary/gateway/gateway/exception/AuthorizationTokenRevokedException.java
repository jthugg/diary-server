package com.github.jthugg.diary.gateway.gateway.exception;

public class AuthorizationTokenRevokedException extends GatewayAuthorizationException {

    public AuthorizationTokenRevokedException() {
        super("Authorization token has been revoked.");
    }

}
