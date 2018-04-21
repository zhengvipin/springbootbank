package com.jwt.mapper;

import com.jwt.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @classDesc: 用户表操作接口
 * @author: Vipin Zheng
 * @createDate: 2018-04-16 18:01:22
 * @version: v1.0
 */
@Repository
public interface UserMapper {
    User findByUsername(@Param("username") String username);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    void add(User user);
}
