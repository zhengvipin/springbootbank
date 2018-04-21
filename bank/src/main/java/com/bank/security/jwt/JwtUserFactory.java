package com.bank.security.jwt;

import com.bank.domain.User;
import com.bank.security.domain.JwtUser;
import com.bank.security.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {
    private JwtUserFactory() {
    }

    /**
     * 由User类创建JwtUser类：关联用户和安全用户
     *
     * @param user 用户类
     * @return 安全用户类
     */
    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getEnabled() == 1,
                user.getLastPasswordResetDate(),
                user.getLoginDate(),
                roleToAuthority(user.getRoles())
                );
    }

    /**
     * 关联角色和权限
     *
     * @param roles 角色集合
     * @return 权限集合
     */
    private static List<GrantedAuthority> roleToAuthority(List<Role> roles) {
        // before java 8 : Without lambda expressions:
        /*List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return authorities;*/

        // Java 8 way : With lambda expressions:
        return roles.stream().// 将list集合转为流
                map(role -> new SimpleGrantedAuthority(role.getName().name())).// 将每个角色映射为权限
                collect(Collectors.toList());// 封装回list集合
    }
}
