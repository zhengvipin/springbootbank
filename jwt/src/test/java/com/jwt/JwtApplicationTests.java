package com.jwt;

import com.jwt.domain.User;
import com.jwt.mapper.UserMapper;
import com.jwt.security.service.JwtUserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtApplicationTests {

    @Value("${jwt.secret}")
    private String secret;

    @Resource
    private UserMapper userMapper;

    @Resource
    private JwtUserDetailsServiceImpl userDetailsService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void test() throws ParseException {
        //System.out.println(secret);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = sdf.parse("2018-01-12");
        System.out.println(newDate.before(new Date()));
    }

    @Test
    public void findByUsername(){
        System.out.println(userDetailsService.loadUserByUsername("admin").getAuthorities());
    }

    @Test
    public void insert(){
        User user = new User();
        user.setUsername("king");
        user.setPassword("king");
        userMapper.add(user);
    }

    @Test
    public void getPassword(){
        System.out.println(passwordEncoder.encode("aaaaaaaa"));
    }
}
