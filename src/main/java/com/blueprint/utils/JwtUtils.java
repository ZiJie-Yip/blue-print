package com.blueprint.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.blueprint.exception.JwtAuthException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ZiJie.Yip
 * @Description: Json Web Token 工具类
 * @date: 2018/11/23 15:39
 */
@Slf4j
public class JwtUtils {
    /**
     * jwt配置文件名
     */
    private final static String DEFAULT_FILE = "jwt";
    /**
     * token秘钥
     */
    public static final String SECRET;
    /**
     * 过期时间类型
     */
    public static final int calendarField = Calendar.MINUTE;
    /**
     * 过去时间
     */
    public static final int calendarInterval;


    static{
        Map<String, String> params = ResourceUtils.getResource(DEFAULT_FILE).getMap();
        SECRET = params.get("jwt.secret");
        calendarInterval = params.get("jwt.minutes") == null ? 24 * 60 : Integer.parseInt(params.get("jwt.minutes"));
    }

    /**
     * JWT生成Token.
     *
     */
    public static String createToken(String username){
        Date iatDate = new Date();
        // expire time
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(calendarField, calendarInterval);
        Date expiresDate = nowTime.getTime();
        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create().withHeader(map)
                .withClaim("iss", "Service")
                .withClaim("aud", "APP").withClaim("username", null == username ? null : username)
                .withIssuedAt(iatDate)
                .withExpiresAt(expiresDate)
                .sign(Algorithm.HMAC256(SECRET));
        return token;

    }



    /**
     * 解密Token
     *
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new JwtAuthException();
        }
        return jwt.getClaims();
    }

    /**
     * 根据Token获取username
     *
     * @param token
     * @return username
     */
    public static String getAppUsername(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim username_claim = claims.get("username");
        if (null == username_claim || StringUtils.isEmpty(username_claim.asString())) {
            // token 校验失败, 抛出Token验证非法异常
            throw new JwtAuthException();
        }
        return username_claim.asString();
    }
}
