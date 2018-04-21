package com.jwt.domain;

import com.jwt.security.domain.Authority;

import java.io.Serializable;
import java.util.List;

/**
 * @classDesc: 用户模型
 * @author: Vipin Zheng
 * @createDate: 2018-04-16 17:58:23
 * @version: v1.0
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private List<Authority> authorities;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
