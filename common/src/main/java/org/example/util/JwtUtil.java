package org.example.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

public class JwtUtil {
    private static final String JWT_SECRET = "cereshuzhitingnizhenbangcereshuz";
    public static final long JWT_EXPIRATION = 24 * 60 * 60 * 1000L;

    private JwtUtil() {}

    public static String encode(String jsonString) {
        return encode(jsonString, JWT_SECRET, JWT_EXPIRATION);
    }

    public static String encode(String jsonString, String secret, long expiration) {
        // Prepare JWT with claims set
        final Date now = new Date();
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issuer("self")
                .issueTime(now)
                .subject(jsonString)
                .expirationTime(new Date(now.getTime() + expiration))
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        try {
            // Apply the HMAC protection
            signedJWT.sign(new MACSigner(secret));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return signedJWT.serialize();
    }

    public static JWTClaimsSet parseClaims(String token) throws ParseException {
        // On the consumer side, parse the JWS and verify its HMAC
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet();
    }

    public static String decode(String token) {
        if(token == null || token.isBlank()) {
            return "";
        }
        // On the consumer side, parse the JWS and verify its HMAC
        JWTClaimsSet claimsSet = null;
        try {
            claimsSet = JwtUtil.parseClaims(token);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return claimsSet.getSubject();
    }

    public static boolean isValid(String token) {
        return isValid(token, JWT_SECRET);
    }

    public static boolean isValid(String token, String secret) {
        if(token == null || token.isBlank()) {
            return false;
        }
        JWSVerifier verifier = null;
        try {
            verifier = new MACVerifier(secret);
        } catch (JOSEException e) {
            System.out.println("JOSEException: " + e);
            return false;
        }
        SignedJWT signedJWT = null;
        try {
            signedJWT = SignedJWT.parse(token);
        } catch (ParseException e) {
            System.out.println("SignedJWT ParseException: " + e);
            return false;
        }
        try {
            return signedJWT.verify(verifier) && new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime());
        } catch (JOSEException | ParseException e) {
            System.out.println("SignedJWT JOSEException: " + e);
            return false;
        }
    }
}
