package com.ntq.components;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtService {

    public static final String SECRET_KEY = "11111111111111111111111111111111";
    public static final byte[] SHARED_SECRET_KEY = SECRET_KEY.getBytes();
    public static final int EXPIRE_TIME = 86400000;

    public String generateTokenLogin(String username) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(SHARED_SECRET_KEY);

            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim("username", username);

            builder.expirationTime(new Date(System.currentTimeMillis() + EXPIRE_TIME));

            JWTClaimsSet claimsSet = builder.build();
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            signedJWT.sign(signer);
            token = signedJWT.serialize();
        } catch (JOSEException e) {
            System.out.println(e.getMessage());
        }
        return token;
    }

    private JWTClaimsSet getClaimsFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SHARED_SECRET_KEY);
            if (signedJWT.verify(verifier)) {
                return signedJWT.getJWTClaimsSet();
            }
        } catch (JOSEException | ParseException e) {
            System.err.println("Error parsing or verifying token: " + e.getMessage());
        }
        return null;
    }

    private Date getExpirationDateFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        return (claims != null) ? claims.getExpirationTime() : null;
    }

    public String getUsernameFromToken(String token) {
        JWTClaimsSet claims = getClaimsFromToken(token);
        try {
            return (claims != null) ? claims.getStringClaim("username") : null;
        } catch (ParseException e) {
            System.err.println("Error retrieving username from token: " + e.getMessage());
            return null;
        }
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return (expiration != null) && expiration.before(new Date());
    }

    public Boolean validateTokenLogin(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }
        String username = getUsernameFromToken(token);
        return username != null && !username.isEmpty() && !isTokenExpired(token);
    }


}
