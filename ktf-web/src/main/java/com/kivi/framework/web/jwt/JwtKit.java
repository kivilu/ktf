package com.kivi.framework.web.jwt;

import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kivi.framework.constant.KtfError;
import com.kivi.framework.exception.KtfException;
import com.kivi.framework.util.kit.DateTimeKit;

public class JwtKit {

    /**
     * 创建jwt
     * 
     * @param identifier
     *            用户标识
     * @param password
     *            用户密码
     * @return
     * @throws Exception
     */
    public static String create( String identifier, String token, Date expiresAt ) throws Exception {

        long nowMillis = System.currentTimeMillis();

        Builder builder = JWT.create().withAudience(identifier).withExpiresAt(expiresAt)
                .withIssuedAt(DateTimeKit.date(nowMillis));

        return builder.sign(Algorithm.HMAC256(token));
    }

    public static boolean verify( String jwt, String token ) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(token)).build();
        try {
            jwtVerifier.verify(jwt);
        }
        catch (JWTVerificationException e) {
            throw new KtfException(KtfError.E_UNAUTHORIZED, "用户未认证");
        }

        return true;
    }

    /**
     * 解密Jwt，并获取用户标识
     * 
     * @param jwt
     * @return
     * @throws Exception
     */
    public static String getIdentifier( String jwt ) throws Exception {
        List<String> auds = JWT.decode(jwt).getAudience();
        return (auds == null || auds.isEmpty()) ? null : auds.get(0);
    }

    public static void main( String[] args ) {

    }

}
