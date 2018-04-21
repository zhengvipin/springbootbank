package com.bank.security.controller;

import java.util.Optional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.bank.security.jwt.JwtAuthenticationRequest;
import com.bank.security.jwt.JwtAuthenticationResponse;
import com.bank.security.service.JwtUserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * @classDesc: 授权控制器
 * @author: Vipin Zheng
 * @createDate: 2018-04-19 10:43:26
 * @version: v1.0
 */
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {

    // ******************************** 引入常量 ********************************

    @Value("${jwt.header}")
    private String tokenHeader;

    // ******************************** 引入组件 ********************************

    @Resource
    private JwtUserServiceImpl jwtUserService;

    // ******************************** 主体方法 *********************************

    /**
     * 登录(生成令牌)
     *
     * @param request 授权请求封装类
     * @return 令牌
     * @throws AuthenticationException 自定义验证异常
     */
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest request) throws AuthenticationException {
        // 登录验证
        String token = jwtUserService.login(request.getUsername(), request.getPassword());
        // 返回令牌
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    /**
     * 刷新令牌
     *
     * @param request http请求类
     * @return 新令牌
     */
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        // 获得原令牌
        String authToken = request.getHeader(tokenHeader);
        String token = authToken.substring(7);
        // 刷新令牌
        Optional<String> optional = jwtUserService.refresh(token);
        // 返回令牌
        return optional.<ResponseEntity<?>>map(s -> ResponseEntity.ok(new JwtAuthenticationResponse(s))).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }
}
