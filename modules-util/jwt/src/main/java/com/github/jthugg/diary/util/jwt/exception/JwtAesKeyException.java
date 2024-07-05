package com.github.jthugg.diary.util.jwt.exception;

public class JwtAesKeyException extends JwtException {

    public JwtAesKeyException() {
        super("The Jwt aes key must be 32 bytes long");
    }

}
