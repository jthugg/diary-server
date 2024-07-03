package com.github.jthugg.diary.util.jwt.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JwtExceptions {

    JWT_AES_KEY_EXCEPTION(new JwtAesKeyException()),
    JWT_DECRYPT_EXCEPTION(new JwtDecryptException()),
    JWT_ENCRYPT_EXCEPTION(new JwtEncryptException()),
    JWT_GENERATION_EXCEPTION(new JwtGenerationException()),
    JWT_IV_KEY_EXCEPTION_EXCEPTION(new JwtIvKeyException()),
    JWT_VERIFYING_EXCEPTION(new JwtVerifyingException()),
    ;

    private final JwtException jwtException;

}
