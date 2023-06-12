package com.scaler.blogapi.security.jwt;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JWTServiceTest {
    private final JWTService jwtService = new JWTService();

    @Test
    void canCreateJWTFromUserId() {
        var userId = 1122;
        var jwt = jwtService.createJWT(userId, new Date(1677687), new Date(1677082));
        assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjc3LCJpYXQiOjE2Nzd9.rIGL_NK9fp5r5CtNPq9AKSIy5E5xFntTlYq-a6ZqTPo", jwt);
    }

    @Test
    void canVerifyJWT() {
        var jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMTIyIiwiZXhwIjoxNjg3MjA1NTIwLCJpYXQiOjE2ODY2MDA3MjB9.c--IzAHeimlNYhQeKzWdSydH36zjU57DbYfTGmLrW1Q";
        var userId = jwtService.getUserIdFromJWT(jwt);
        assertEquals(1122, userId);
    }
}