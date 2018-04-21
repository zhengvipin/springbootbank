Spring Security:
    Spring Security使用一个Authentication对象来描述当前用户的相关信息。
    SecurityContextHolder中持有的是当前用户的SecurityContext，
    而SecurityContext持有的是代表当前用户相关信息的Authentication的引用。
    UsernamePasswordAuthenticationToken继承AbstractAuthenticationToken实现Authentication
    所以当在页面中输入用户名和密码之后首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)

※Spring Boot中使用使用Spring Security和JWT:
    https://www.cnblogs.com/hackyo/p/8004928.html

※spring security认证过程：
    https://www.cnblogs.com/shiyu404/p/6530894.html

※spring security的登录密码验证过程 UsernamePasswordAuthenticationFilter：
    https://www.cnblogs.com/lexiaofei/p/7018405.html?utm_source=itdadao&utm_medium=referral

※@ControllerAdvice + @ExceptionHandler 全局处理 Controller 层异常：
    https://blog.csdn.net/kinginblue/article/details/70186586

※SpringMVC开启CORS支持:
    https://www.jianshu.com/p/d05303d34222
※CORS在Spring框架中的支持:
    https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
※spring MVC cors跨域实现源码解析:
    https://www.cnblogs.com/leftthen/p/6378090.html

※字符串格式化：String.format()方法的使用:
    http://kgd1120.iteye.com/blog/1293633

※@RequestBody和@RequestParam区别:
    https://blog.csdn.net/xinluke/article/details/52710706

