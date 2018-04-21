package com.jwt.security.domain;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @classDesc: 自定义401(身份验证失败)返回值
 * @author: Vipin Zheng
 * @createDate: 2018-04-16 21:51:27
 * @version: v1.0
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有域名的脚本访问该资源。
        response.setStatus(401);
    }

}
