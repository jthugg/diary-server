package com.github.jthugg.diary.util.jwt.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class JwtClaims {

    @Getter
    @RequiredArgsConstructor
    public enum Header {

        TYPE("typ"),
        ;

        private final String claim;

    }

    @Getter
    @RequiredArgsConstructor
    public enum Payload {

        USER_ID("sub"),
        USER_ROLE("rol"),
        EXPIRATION("exp"),
        ;

        private final String claim;

    }

}
