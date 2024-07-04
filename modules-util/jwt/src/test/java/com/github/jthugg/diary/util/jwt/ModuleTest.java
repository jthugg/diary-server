package com.github.jthugg.diary.util.jwt;

import com.github.jthugg.diary.util.jwt.model.JwtSet;
import com.github.jthugg.diary.util.jwt.model.JwtType;
import com.github.jthugg.diary.util.jwt.model.ResolvedJwt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

public class ModuleTest {

    long accessTokenLifeTime;
    long refreshTokenLifeTime;
    JwtGenerator jwtGenerator;
    JwtVerifier jwtVerifier;

    ModuleTest() {
        String tokenSignatureSecret = "testSecret";
        while (tokenSignatureSecret.length() < 32) {
            tokenSignatureSecret = tokenSignatureSecret.concat(tokenSignatureSecret);
        }
        accessTokenLifeTime = 3;
        refreshTokenLifeTime = 5;
        JwtProperties properties = new JwtProperties(
                tokenSignatureSecret,
                accessTokenLifeTime,
                refreshTokenLifeTime
        );
        String aesKey = "testAesKeyTestAesKeyTestAesKey!!";
        String ivKey = "testIvKeyValue!!";
        JwtEncryptor encryptor = new JwtEncryptor(aesKey, ivKey);
        this.jwtGenerator = new JwtGenerator(properties, encryptor);
        this.jwtVerifier = new JwtVerifier(properties, encryptor);
    }

    @Test
    void test() throws InterruptedException {
        String testUserId = "testUserId";
        String testUserRole = "testUserRole";
        JwtSet tokenSet = jwtGenerator.generateToken(testUserId, testUserRole);

        ResolvedJwt accessToken = jwtVerifier.verify(tokenSet.accessToken());
        ResolvedJwt refreshToken = jwtVerifier.verify(tokenSet.refreshToken());

        Assertions.assertEquals(JwtType.ACCESS_TOKEN, accessToken.getType());
        Assertions.assertEquals(testUserId, accessToken.getUserId());
        Assertions.assertTrue(accessToken.getExpiration().isAfter(Instant.now()));
        Assertions.assertFalse(accessToken.isExpired());

        Assertions.assertEquals(JwtType.REFRESH_TOKEN, refreshToken.getType());
        Assertions.assertEquals(testUserId, refreshToken.getUserId());
        Assertions.assertTrue(refreshToken.getExpiration().isAfter(Instant.now()));
        Assertions.assertFalse(refreshToken.isExpired());

        long waitTime = accessTokenLifeTime * 1_000;
        Thread.sleep(waitTime);
        accessToken = jwtVerifier.verify(tokenSet.accessToken());

        Assertions.assertTrue(accessToken.isExpired());

        waitTime = (refreshTokenLifeTime - accessTokenLifeTime) * 1_000;
        Thread.sleep(waitTime);
        refreshToken = jwtVerifier.verify(tokenSet.refreshToken());

        Assertions.assertTrue(refreshToken.isExpired());
    }

}
