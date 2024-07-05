package com.github.jthugg.diary.util.jwt.exception;

public class JwtEncryptException extends JwtException {

    public JwtEncryptException() {
        super("Cannot encrypt JWT token.");
    }

}
