package com.github.jthugg.diary.gateway.gateway.exception;

public abstract class GatewayException extends RuntimeException {

    public GatewayException(String message) {
        super(message);
    }

}
