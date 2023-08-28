package com.project.menuflash.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.menuflash.dto.response.LoginUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenService {

    private final String ttl = "1500000";
    private static final String SEED = "seedTokenGenerator";
    private static final String USER_CLAIM = "user";

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public String getJwtToken(LoginUser user) {

        String jwtToken = null;
        try {
            jwtToken = createJWTAndSign(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwtToken;
    }

    public LoginUser getUserFromToken(String token) throws Exception {
        verifyToken(token);

        LoginUser user = null;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getDecoder().decode(SEED))
                    .parseClaimsJws(token)
                    .getBody();
            final ObjectMapper mapper = new ObjectMapper();
            user = mapper.convertValue(claims.get(USER_CLAIM), LoginUser.class);

        } catch (ExpiredJwtException e) {
            throw new Exception("Token expired");
        } catch (Exception e) {
            throw new Exception("Could not get all claims Token from passed token");
        }

        return user;
    }

    private String createJWTAndSign(LoginUser user) throws Exception {
        JwtBuilder builder = Jwts.builder()
                .claim(USER_CLAIM, user)
                .signWith(SIGNATURE_ALGORITHM, Base64.getDecoder().decode(SEED))
                .setExpiration(getExpiration());

        return builder.compact();
    }

    private Date getExpiration() {
        Long ttlMillis = Long.parseLong(ttl);
        Long nowMillis = System.currentTimeMillis();
        Date exp = new Date(nowMillis);

        if (ttlMillis != null && ttlMillis > 0) {
            Long expMillis = nowMillis + ttlMillis;
            exp = new Date(expMillis);
        }
        return exp;
    }

    private void verifyToken(String token) throws Exception {
        System.out.println("tokennn: " + token);
        String[] chunks = token.split("\\.");

        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.getDecoder().decode(SEED), SIGNATURE_ALGORITHM.getJcaName());
        String tokenWithoutSignature = chunks[0] + "." + chunks[1];
        String signature = chunks[2];

        DefaultJwtSignatureValidator validator = new DefaultJwtSignatureValidator(SIGNATURE_ALGORITHM, secretKeySpec);

        if (!validator.isValid(tokenWithoutSignature, signature))
            throw new Exception("Could not verify JWT token integrity!");
    }

}
