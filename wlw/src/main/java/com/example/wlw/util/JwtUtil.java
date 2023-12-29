//package com.example.wlw.util;
//
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.Claim;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import com.example.wlw.entity.Login;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JwtUtil {
//    /**
//     * 生成 JWT Token
//     * */
//    public static String CreateToken(Login login) throws JWTCreationException {
//        Map<String, Object> payload = new HashMap<>();
//        payload.put("account", login.account);
//        payload.put("password",login.password);
//        String token = JWT.create()
//                .withClaim("user",payload)
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *12)) // 指定令牌的过期时间
//                .sign(Algorithm.HMAC256("123456"));//签名
//        System.out.println(token);
//        Map<String, Object> ret = Gettoken(token);
//        return token;
//    }
//
//    /**
//     * 验证 token
//     * */
//    public static boolean CheckToken(String token) throws JWTVerificationException {
//        try {
//            // 通过签名生成验证对象
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
//            DecodedJWT decodedJWT = jwtVerifier.verify(token);
//            Map<String, Claim> claims = decodedJWT.getClaims();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public static Map<String, Object> Gettoken(String token) throws JWTVerificationException {
//        try {
//            // 通过签名生成验证对象
//            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
//            DecodedJWT decodedJWT = jwtVerifier.verify(token);
//            Map<String,Claim> claims = decodedJWT.getClaims();
//            return claims.get("user").asMap();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}

