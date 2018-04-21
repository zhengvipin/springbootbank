package com.jwt.security.util;


import com.jwt.domain.User;
import com.jwt.security.domain.Authority;
import com.jwt.security.domain.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @classDesc: 用户工厂类
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 10:20:11
 * @version: v1.0
 */
public final class JwtUserFactory {
    private JwtUserFactory() {

    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthority(user.getAuthorities())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<Authority> authorities) {
        return authorities.stream().
                map(authority -> new SimpleGrantedAuthority(authority.getName().name())).
                collect(Collectors.toList());
    }
}
