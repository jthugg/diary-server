package com.github.jthugg.diary.util.jwt;

import com.github.jthugg.diary.util.jwt.exception.JwtExceptions;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class JwtEncryptor {

    private final String SECRET_KEY_ALGORITHM = "AES";
    private final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    private final SecretKey secretKey;
    private final IvParameterSpec ivParameterSpec;
    private final Base64.Encoder encoder;
    private final Base64.Decoder decoder;

    public JwtEncryptor(String aesKeyValue, String ivKeyValue) {
        assertKeys(aesKeyValue, ivKeyValue);
        this.secretKey = new SecretKeySpec(aesKeyValue.trim().getBytes(StandardCharsets.UTF_8), SECRET_KEY_ALGORITHM);
        this.ivParameterSpec = new IvParameterSpec(ivKeyValue.trim().getBytes(StandardCharsets.UTF_8));
        this.encoder = Base64.getEncoder();
        this.decoder = Base64.getDecoder();
    }

    private static void assertKeys(String aesKey, String ivKey) {
        if (aesKey.getBytes(StandardCharsets.UTF_8).length != 32) {
            throw JwtExceptions.JWT_AES_KEY_EXCEPTION.getJwtException();
        }
        if (ivKey.getBytes(StandardCharsets.UTF_8).length != 16) {
            throw JwtExceptions.JWT_IV_KEY_EXCEPTION_EXCEPTION.getJwtException();
        }
    }

    public String encrypt(String plainToken) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            return encoder.encodeToString(cipher.doFinal(plainToken.getBytes(StandardCharsets.UTF_8)));
        } catch (GeneralSecurityException exception) {
            throw JwtExceptions.JWT_ENCRYPT_EXCEPTION.getJwtException();
        }
    }

    public String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            byte[] plainText = cipher.doFinal(decoder.decode(encryptedText));
            return new String(plainText, StandardCharsets.UTF_8);
        } catch (GeneralSecurityException exception) {
            throw JwtExceptions.JWT_DECRYPT_EXCEPTION.getJwtException();
        }
    }

}
