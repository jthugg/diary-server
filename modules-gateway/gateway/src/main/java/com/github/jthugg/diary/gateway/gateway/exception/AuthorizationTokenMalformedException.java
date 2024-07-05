package com.github.jthugg.diary.gateway.gateway.exception;

public class AuthorizationTokenMalformedException extends GatewayAuthorizationException {

    public AuthorizationTokenMalformedException() {
        super("Malformed authorization token.");
    }

}
