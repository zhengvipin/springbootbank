package com.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @classDesc: 跨域配置
 * @author: Vipin Zheng
 * @createDate: 2018-04-21 14:34:12
 * @version: v1.0
 */
@Configuration
public class CorsConfig {

    // 允许跨域的URI
    private static List<String> origins = Arrays.asList(
            "127.0.0.1:8080",
            "localhost:8080"
//            "127.0.0.1:8086",
//            "localhost:8086",
//            "google.com",
//            "gmail.google.com",
//            "photo.google.com",
//            "keep.google.com"
    );

    @Bean
    public CorsFilter corsFilter() {
        // 1.封装自定义跨域信息
        // 1.1 获得具体封装跨域配置信息的pojo对象
        CorsConfiguration corsConfig = new CorsConfiguration();
        // 1.2 允许的请求源，*表示全部允许
        //corsConfig.addAllowedOrigin("*");
        origins.forEach(origin -> corsConfig.addAllowedOrigin("http://" + origin));
        // 1.3 允许的请求头，*表示全部允许
        corsConfig.addAllowedHeader("*");
        // 1.4 允许的http方法，*表示全部允许
        corsConfig.addAllowedMethod("*");
        // 2.注册新的跨域信息
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // 存储request与跨域配置信息的容器
        source.registerCorsConfiguration("/**", corsConfig);
        return new CorsFilter(source);
    }
}
