package com.moordash.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * Generate JWT token
     * Use Hs256 algorithm, the private key uses a fixed key
     *
     * @param secretKey jwt secret key
     * @param ttlMillis jwt expiration time (ms)
     * @param claims    jwt body (info to be stored)
     * @return
     */
    public static String createJWT(String secretKey, long ttlMillis, Map<String, Object> claims) {
        // header part: specify the signature algorithm used
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // time of token creation
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // set up JWT body
        JwtBuilder builder = Jwts.builder()
                // if there are private claims, set them first.
                // this is to assign the private claims created by yourself to the builder,
                // once written after the standard claims, it will overwrite those standard claims
                .setClaims(claims)
                // set the signature algorithm and secret key used for signature
                .signWith(signatureAlgorithm, secretKey.getBytes(StandardCharsets.UTF_8))
                // set the expiration time
                .setExpiration(exp);

        return builder.compact();
    }

    /**
     * Token decryption
     *
     * @param secretKey jwt secret key
     * @param token     encrypted token
     * @return
     */
    public static Claims parseJWT(String secretKey, String token) {
        // get DefaultJwtParser
        Claims claims = Jwts.parser()
                // set the secret key used for signature verification
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                // set the signature algorithm used for verification
                .parseClaimsJws(token).getBody();
        return claims;
    }

}
