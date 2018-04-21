package com.bank.security.config;

import com.bank.security.jwt.JwtAuthenticationTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;

/**
 * @classDesc: 安全模块配置 -> 相当于一个applicationContext-mvc.xml配置文件，每次访问资源率先访问该类
 * @author: Vipin Zheng
 * @createDate: 2018-04-17 21:43:13
 * @version: v1.0
 */
@Configuration // 声明是一个配置类，可理解为用spring的时候xml里面的<beans>标签
@EnableWebSecurity // Spring Security会自动帮助我们创建一个名字为的springSecurityFilterChain过滤器
// Spring Security默认是禁用注解的，要想开启注解，
// 需要在继承WebSecurityConfigurerAdapter的类上加@EnableGlobalMethodSecurity注解，
// 并在该类中将AuthenticationManager定义为Bean。
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用PreAuthorize、preFilter注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // ******************************** 装载Bean ********************************

    @Bean // 配置BCrypt密码编码器
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Logger logger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // ******************************** 引入常量 ********************************

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.route.authentication.path}")
    private String authenticationPath;

    // ******************************** 引入组件 ********************************

    @Resource
    //@Qualifier("jwtUserDetailsServiceImpl") 有多个实现类时要声明具体需要绑定的实现类
    private UserDetailsService userDetailsService; // 会自定绑定其实现类

    @Resource // JWT过滤器
    private JwtAuthenticationTokenFilter authenticationTokenFilter;

    @Resource // JWT401异常处理类
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource // JWT403异常处理类
    private AccessDeniedHandler accessDeniedHandler;

    // ******************************** 主体方法 ********************************

    @Override // 配置认证管理器
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置userDetailsService
                .userDetailsService(userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    @Override // 设置拦截规则
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 1.spring security
        httpSecurity
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 支持跨域
                .cors().and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and() // 结束标签
                .authorizeRequests()
                // 对于获取token的rest api要允许匿名访问
                //.antMatchers("/api/auth/**").permitAll()
                // 除上面u外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 2.集成 JWT
        // 2.1 JWT过滤器
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 2.2 JWT异常处理类
        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override // 设置不拦截规则
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                // 不拦截自定义url
                .ignoring()
                .antMatchers(HttpMethod.POST, "/api/" + authenticationPath)

                .and()

                // 不拦截静态资源
                .ignoring()
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js");
    }
}
