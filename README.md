Spring Security:
===
introduction:
---
* Spring Security使用一个`Authentication`对象来描述当前用户的相关信息。
* `SecurityContextHolder`中持有的是当前用户的`SecurityContext`，
* 而`SecurityContext`持有的是代表当前用户相关信息的`Authentication`的引用。
* `UsernamePasswordAuthenticationToken`继承`AbstractAuthenticationToken`实现`Authentication`
* 所以当在页面中输入用户名和密码之后首先会进入到`UsernamePasswordAuthenticationToken`验证(`Authentication`)

adout info：
---
[Spring Boot中使用使用Spring Security和JWT](https://www.cnblogs.com/hackyo/p/8004928.html)<br/>

[spring security认证过程](https://www.cnblogs.com/shiyu404/p/6530894.html)<br/>

[spring security的登录密码验证过程 UsernamePasswordAuthenticationFilter](https://www.cnblogs.com/lexiaofei/p/7018405.html?utm_source=itdadao&utm_medium=referral)<br/>

[@ControllerAdvice + @ExceptionHandler 全局处理 Controller 层异常](https://blog.csdn.net/kinginblue/article/details/70186586)<br/>

[SpringMVC开启CORS支持](https://www.jianshu.com/p/d05303d34222)<br/>

[CORS在Spring框架中的支持](https://spring.io/blog/2015/06/08/cors-support-in-spring-framework)<br/>

[spring MVC cors跨域实现源码解析](https://www.cnblogs.com/leftthen/p/6378090.html)<br/>

[字符串格式化：String.format()方法的使用](http://kgd1120.iteye.com/blog/1293633)<br/>

[@RequestBody和@RequestParam区别](https://blog.csdn.net/xinluke/article/details/52710706)<br/>
