package com.jwt.service.impl;


import com.jwt.domain.User;
import com.jwt.mapper.UserMapper;
import com.jwt.security.service.JwtUserDetailsServiceImpl;
import com.jwt.security.util.JwtTokenUtil;
import com.jwt.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @classDesc: 用户操作接口实现类
 * @author: Vipin Zheng
 * @createDate: 2018-04-16 22:08:21
 * @version: v1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
        System.out.println(userDetails.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }

    @Override
    public String register(User user) {
        String username = user.getUsername();
        if (userMapper.findByUsername(username) != null) {
            return "用户已存在";
        }
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        List<String> authorities = new ArrayList<>();
        authorities.add("ROLE_USER");
       // user.setRoles(roles);
        userMapper.add(user);
        return "success";
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }

}
