package com.github.jthugg.diary.util.jwt.exception;

public abstract class JwtException extends RuntimeException {

    public JwtException(String message) {
        super(message);
    }

}
