package com.scaler.blogapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    private final Algorithm algorithm = Algorithm.HMAC256("SECRET SIGNING KEY (should be in env or config)");

    public String createJWT(Integer userId) {
        return createJWT(userId, new Date(), new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000));
    }

    protected String createJWT(Integer userId, Date iat, Date exp) {
        String token = JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(iat)
                .withExpiresAt(exp) // 7 days
                .sign(algorithm);
        return token;
    }

    public Integer getUserIdFromJWT(String jwt) {
        var verifier = JWT.require(algorithm).build();
        var decodedJWT = verifier.verify(jwt);
        var subject = decodedJWT.getSubject();
        return Integer.parseInt(subject);
    }
}
