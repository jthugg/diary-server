package com.github.jthugg.diary.util.jwt.exception;

public class JwtDecryptException extends JwtException {

    public JwtDecryptException() {
        super("Cannot decrypt JWT token.");
    }

}
