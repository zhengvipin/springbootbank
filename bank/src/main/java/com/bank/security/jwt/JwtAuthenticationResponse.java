package com.bank.security.jwt;

import java.io.Serializable;

/**
 * @classDesc: JWT授权响应类
 * @author: Vipin Zheng
 * @createDate: 2018-04-19 09:01:18
 * @version: v1.0
 */
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
