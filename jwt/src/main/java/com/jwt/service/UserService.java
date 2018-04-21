package com.jwt.service;

import com.jwt.domain.User;

/**
 * @classDesc: 用户操作接口
 * @author: Vipin Zheng
 * @createDate: 2018-04-16 21:54:50
 * @version: v1.0
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     */
    String login(String username, String password);

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 操作结果
     */
    String register(User user);

    /**
     * 刷新密钥
     *
     * @param oldToken 原密钥
     * @return 新密钥
     */
    String refreshToken(String oldToken);
}
