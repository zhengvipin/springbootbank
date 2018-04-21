package com.bank.controller;

import com.bank.UserServiceImpl;
import com.bank.domain.User;
import com.bank.security.domain.JwtUser;
import com.bank.security.jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserServiceImpl userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request){
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser)userDetailsService.loadUserByUsername(username);
    }

    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public ResponseEntity<?> changePassword(
            @RequestParam("password") String password,
            HttpServletRequest request
    ) {
        password = passwordEncoder.encode(password);

        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        int count = userService.changePassword(user.getId(), password);
        return ResponseEntity.ok(count);
    }

    // ---------- Restful -----------------------

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.find();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        User user = userService.find(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody User user) {
        // 加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 设置默认日期
        user.setLastPasswordResetDate(new Date());
        user.setLoginDate(new Date());

        int count = userService.add(user);
        return ResponseEntity.ok(count);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public ResponseEntity<?> modify(@RequestBody User user) {
        System.out.println(user);
        int count = userService.modify(user);
        return ResponseEntity.ok(count);
    }

    @PostMapping("/userauthority")
    public ResponseEntity<?> addUserAuthority(
            @RequestParam("userId") Long userId,
            @RequestParam("authorityIds") Long[] authorityIds) {
        int count = userService.addUserAuthority(userId, authorityIds);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/finduserauthority")
    public ResponseEntity<?> findUserAuthority(@RequestParam("userId") Long userId) {
        List<Integer> authorityIds = userService.findUserAuthority(userId);
        return new ResponseEntity<>(authorityIds, HttpStatus.OK);
    }
}
