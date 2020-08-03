package com.wsf.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    private static final String SECRET = "123456";
    private static final Long EXPIRE = 1000L * 60;
    public static final String AUTH_HEADER = "Authorization";

    /**
     * 创建token
     * @return
     */
    public static String createToken() {

        Date nowTime = new Date();
        Date expireTime = new Date(nowTime.getTime() + EXPIRE);

        JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(nowTime)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS512, SECRET);
        return jwtBuilder.compact();
    }

    /**
     * 获取token中保存的信息
     * @param token
     * @return
     */
    public static Claims getClaim(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    public static Boolean isExpired(String token) {

        //判断过期时间是否小于当前时间，true表示过期，false表示未过期
        return getClaim(token).getExpiration().before(new Date());
    }

    /**
     * 获取token发行者
     * @param token
     * @return
     */
    public static String getSubject(String token) {
        return getClaim(token).getSubject();
    }

    /**
     * 获取token创建时间
     * @param token
     * @return
     */
    public static Date getIssuedAt(String token) {
        return getClaim(token).getIssuedAt();
    }

    /**
     * 获取token过期时间
     * @param token
     * @return
     */
    public static Date getExpiration(String token) {
        return getClaim(token).getExpiration();
    }
}
