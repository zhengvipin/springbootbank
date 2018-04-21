package com.bank.security.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * @classDesc: 安全用户模型
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 18:18:53
 * @version: v1.0
 */
public class JwtUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private Date lastPasswordResetDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginDate;
    // 关联安全用户和权限
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String username, String password, String email, boolean enabled, Date lastPasswordResetDate, Date loginDate, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.loginDate = loginDate;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未上锁
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 用户凭证是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 用户账号是否已启用
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }


    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }
}
