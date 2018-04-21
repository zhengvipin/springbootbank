//package com.bank;
//
//import com.bank.mapper.UserMapper;
//import com.bank.security.domain.RoleName;
///*import com.bank.security.service.JwtUserDetailsServiceImpl;*/
//import io.jsonwebtoken.impl.DefaultClock;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.Date;
//import java.util.function.Function;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BankApplicationTests {
//
//    @Resource
//    private UserMapper userMapper;
//
///*    @Resource
//    private JwtUserDetailsServiceImpl userService;*/
//
//    @Test // String.format(String format, Object... args) 该方法使用指定的字符串格式和参数生成格式化的新字符串
//    public void stringFormatDemo() {
//
//        System.out.println(String.format("100的一半是：%d", 100 / 2)); // %d 整数类型
//        System.out.printf("你是我的: %s %n", "小苹果"); // %s 字符串类型  // %n 换行符
//        System.out.printf("这个手机的价格是：%f", 4578.64d); // %f 浮点类型
//    }
//
///*    @Test
//    public void findUserByUsername() {
//        System.out.println(userMapper.findByUserName("admin").getPassword());
//    }*/
//
///*    @Test
//    public void loadUserByUsername() {
//        System.out.println(userService.loadUserByUsername("admin").getAuthorities());
//    }*/
//
//    @Test
//    public void enumDemo() {
//        for (RoleName roleName : RoleName.values()) {
//            System.out.println(roleName.name());
//        }
//    }
//
//    @Test // Function作为一个函数式接口，主要方法apply接收一个参数，返回一个值
//    public void functionDemo() {
//        System.out.println(compute(5, value -> value * value));
//        System.out.println(compute(20, value -> value + value));
//        System.out.println(compute(28, value -> value - 5));
//    }
//
//    @Test
//    public void functionDemo2() {
//        System.out.println(countLength("余茜是我闺女", String::length));
//        System.out.println(countLength("余茜是我小闺女", String::length));
//    }
//
//    @Test
//    public void clockDemo() {
//        DefaultClock clock = new DefaultClock();
//        System.out.println(clock.now());
//        System.out.println(new Date());
//    }
//
//    private int countLength(String str, Function<String, Integer> function) {
//        return function.apply(str);
//    }
//
//    private int compute(int num, Function<Integer, Integer> function) {
//        return function.apply(num);
//    }
//}
