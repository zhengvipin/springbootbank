package com.bank.security.jwt;

import java.io.Serializable;

/**
 * @classDesc: JWT授权请求类
 * @author: Vipin Zheng
 * @createDate: 2018-04-19 09:01:51
 * @version: v1.0
 */
public class JwtAuthenticationRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public JwtAuthenticationRequest() {
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
