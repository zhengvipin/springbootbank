package com.bank.mapper;

import com.bank.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @classDesc: 用户操作数据接口
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 17:35:16
 * @version: v1.0
 */
@Repository
public interface UserMapper {
    User findByUsername(@Param("username") String username);

    @Update("update user set password=#{password},last_password_reset_date=now() where id=#{id}")
    int changePassword(@Param("id") Long id,
                       @Param("password") String password);

    @Select("select id,username,email,enabled,last_password_reset_date,login_date from user")
    @Results({
            @Result(property = "lastPasswordResetDate", column = "last_password_reset_date"),
            @Result(property = "loginDate", column = "login_date")
    })
    List<User> find();

    @Select("select id,username,email,enabled,last_password_reset_date lastPasswordResetDate,login_date loginDate from user where id=#{id}")
    User findById(Long id);

    @Insert("insert into user(username,password,email,enabled,last_password_reset_date,login_date) values(#{username},#{password},#{email},#{enabled},#{lastPasswordResetDate},#{loginDate})")
    int add(User user);

    @Update("update user set email=#{email},enabled=#{enabled} where id=#{id}")
    int modify(User user);


    // 关联用户-角色
    int addUserAuthority(
            @Param("userId") Long userId,
            @Param("authorityIds") Long[] authorityIds
    );


    // 删除指定用户的角色
    @Delete("delete from user_authority where user_id=#{userId}")
    int removeUserAuthority(@Param("userId") Long userId);


    // 查询指定用户的角色
    @Select("select authority_id from user_authority where user_id=#{userId}")
    List<Integer> findUserAuthority(@Param("userId") Long userId);
}
