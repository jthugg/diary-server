package com.github.jthugg.diary.util.jwt.exception;

public class JwtVerifyingException extends JwtException {

    public JwtVerifyingException() {
        super("JWT verification failed.");
    }

}
