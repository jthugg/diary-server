package com.github.jthugg.diary.data.token.storage.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    private String token;
    private TokenType type;
    private boolean revoked;

    private Token(String token, TokenType type) {
        this.token = token;
        this.type = type;
    }

    public static Token newAccessToken(String token) {
        return new Token(token, TokenType.ACCESS_TOKEN);
    }

    public static Token newRefreshToken(String token) {
        return new Token(token, TokenType.REFRESH_TOKEN);
    }

}
