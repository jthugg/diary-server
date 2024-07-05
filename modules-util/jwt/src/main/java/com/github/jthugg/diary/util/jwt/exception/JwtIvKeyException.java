package com.github.jthugg.diary.util.jwt.exception;

public class JwtIvKeyException extends JwtException {

    public JwtIvKeyException() {
        super("The jwt iv key must be 16 bytes long.");
    }

}
