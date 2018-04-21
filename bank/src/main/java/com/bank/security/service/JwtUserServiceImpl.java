package com.bank.security.service;

import com.bank.security.domain.JwtUser;
import com.bank.security.jwt.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @classDesc: 安全用户 业务逻辑处理实现类
 * @author: Vipin Zheng
 * @createDate: 2018-04-21 12:51:12
 * @version: v1.0
 */
@Service
public class JwtUserServiceImpl {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 登录(生成授权令牌)
     *
     * @param username 用户名
     * @param password 密码
     * @return 令牌
     */
    public String login(String username, String password) throws AuthenticationException {
        // 生成未验证令牌
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // 验证令牌
        Authentication authentication = authenticationManager.authenticate(upToken);// 对用户密码的正确性进行验证,失败则抛异常
        // 将验证信息放入安全上下文中，供后续的程序进行调用，如访问权限的鉴定等
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成已验证令牌
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        return jwtTokenUtil.generateToken(userDetails);
    }

    /**
     * 刷新令牌
     *
     * @param oldToken 原令牌
     * @return 新令牌
     */
    public Optional<String> refresh(String oldToken) {
        // 获得原令牌
        String token = oldToken.substring(7);
        // 获得当前用户
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) jwtUserDetailsService.loadUserByUsername(username);
        Optional<String> optional = Optional.empty();
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            // 生成新令牌
            optional = Optional.of(jwtTokenUtil.refreshToken(token));
        }
        return optional;
    }
}
