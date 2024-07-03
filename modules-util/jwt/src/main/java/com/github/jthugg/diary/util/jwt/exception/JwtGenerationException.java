package com.github.jthugg.diary.util.jwt.exception;

public class JwtGenerationException extends JwtException {

    public JwtGenerationException() {
        super("JWT creation failed.");
    }

}
