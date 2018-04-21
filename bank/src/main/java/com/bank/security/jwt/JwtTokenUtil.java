package com.bank.security.jwt;

import com.bank.security.domain.JwtUser;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @classDesc: Token工具类
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 21:35:42
 * @version: v1.0
 */
@Component
public class JwtTokenUtil {
    // ******************************** 引入常量 *******************************

    /**
     * 秘钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 过期时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    // ******************************** 主体方法 ********************************

    /**
     * 生成令牌
     *
     * @param userDetails 用户
     * @return 令牌
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    /**
     * 令牌能否被刷新
     *
     * @param token                 令牌
     * @param lastPasswordResetDate 上次登录时间
     * @return 能否刷新
     */
    public Boolean canTokenBeRefreshed(String token, Date lastPasswordResetDate) {
        final Date issueDate = getIssueDateFromToken(token);
        return !isIssueDateBeforeLastPasswordResetDate(issueDate, lastPasswordResetDate)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        Date issueDate = new Date();
        Date expirationDate = new Date(issueDate.getTime() + expiration * 1000);
        Claims claims = getAllClaimFromToken(token);
        claims.setIssuedAt(issueDate);
        claims.setExpiration(expirationDate);
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param userDetails 用户
     * @return 是否有效
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        JwtUser user = (JwtUser) userDetails;
        String username = getUsernameFromToken(token);
        Date issueDate = getIssueDateFromToken(token);
        return (
                username.equals(user.getUsername())
                        && !isTokenExpired(token)
                        && !isIssueDateBeforeLastPasswordResetDate(issueDate, user.getLastPasswordResetDate())
        );
    }

    /**
     * 从令牌中获得用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 从令牌中获得签发日期
     *
     * @param token 令牌
     * @return 签发日期
     */
    public Date getIssueDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    /**
     * 从令牌中获得过期时间
     *
     * @param token 令牌
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // ******************************** 私有方法 ********************************

    /**
     * 忽略过期时间
     *
     * @param token 令牌
     * @return 是否忽略
     */
    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    /**
     * 判断签发时间是否早于上次改密时间
     *
     * @param issueDate             签发时间
     * @param lastPasswordResetDate 上次改密时间
     * @return 是否早于
     */
    private Boolean isIssueDateBeforeLastPasswordResetDate(Date issueDate, Date lastPasswordResetDate) {
        return (lastPasswordResetDate != null && issueDate.before(lastPasswordResetDate));
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    private Boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 根据数据声明和用户名生成令牌
     *
     * @param claims   数据声明
     * @param username 用户名
     * @return 令牌
     */
    private String doGenerateToken(Map<String, Object> claims, String username) {
        Date issueDate = new Date();
        Date expirationDate = new Date(issueDate.getTime() + expiration * 1000);

        // 添加JWT参数
        JwtBuilder builder = Jwts.builder()
                // 1.头部(header)
                .setHeaderParam("typ", "JWT") // 可省略
                // 2.荷载(payload)
                .setClaims(claims) // 自定义数据声明
                .setSubject(username) // 面向的用户(主体)
                .setIssuedAt(issueDate) // 签发时间
                .setExpiration(expirationDate) // 过期时间
                // 3.签名(signature)
                .signWith(SignatureAlgorithm.HS512, secret); // 加密算法 | 秘钥
        // 生成JWT
        return builder.compact();
    }

    /**
     * 定义获得具体的数据声明的方法
     *
     * @param token          令牌
     * @param claimsResolver 数据声明解析器
     * @param <T>            返回类型
     * @return 具体方法
     */
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 根据令牌生成数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getAllClaimFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
