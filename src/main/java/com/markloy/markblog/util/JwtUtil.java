package com.markloy.markblog.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    //密钥
    private static final String secret = "#$%*^%$$^&*($##^&aqa";

    //7天毫秒数
    private static final Long expires = 604800000L;

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String createToken(Map<String, String> map)  {
        //创建jwt实例
        JWTCreator.Builder builder = JWT.create();
        //将map中的信息放入jwt中
        map.forEach(builder::withClaim);
        Date expiresAt = new Date(expires + System.currentTimeMillis());
        return builder
                //过期时间配置
                .withExpiresAt(expiresAt)
                //设置密钥
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * 解析token
     * @param token
     */
    public static void verifyToken(String token) {
        JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

}
