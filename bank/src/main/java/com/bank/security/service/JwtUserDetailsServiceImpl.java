package com.bank.security.service;

import com.bank.domain.User;
import com.bank.mapper.UserMapper;
import com.bank.security.jwt.JwtUserFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @classDesc: 安全用户 业务逻辑处理实现类
 * @author: Vipin Zheng
 * @createDate: 2018-04-21 12:42:07
 * @version: v1.0
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户
     * @throws UsernameNotFoundException 未找到该用户名异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws AuthenticationException {
        // 从数据库中加载用户对象
        User user = userMapper.findByUsername(username);
        // 返回数据
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}
