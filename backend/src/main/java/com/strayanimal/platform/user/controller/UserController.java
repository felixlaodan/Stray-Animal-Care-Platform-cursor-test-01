package com.strayanimal.platform.user.controller;

import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.core.jwt.JwtUtil;
import com.strayanimal.platform.user.dto.LoginResponseDTO;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author dong
 * @since 2024-07-25
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;

    /**
     * 用户注册接口
     * @param user 包含用户名、密码和昵称的用户对象
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        // 基本的参数校验
        if (user.getUsername() == null || user.getPassword() == null || user.getNickname() == null) {
            return Result.error("用户名、密码和昵称不能为空");
        }
        return userService.register(user);
    }

    /**
     * 用户登录接口
     * @param user 包含用户名和密码的用户对象
     * @return 登录结果，成功则包含JWT Token
     */
    @PostMapping("/login")
    public Result<LoginResponseDTO> login(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("用户名和密码不能为空");
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (Exception e) {
            return Result.error("用户名或密码错误");
        }

        // 认证成功，加载Spring Security用户详情
        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        
        // 从数据库获取完整的用户信息实体
        User userInDb = userService.findByUsername(userDetails.getUsername());
        // 出于安全，清除密码信息
        if(userInDb != null) {
            userInDb.setPassword(null);
        }

        // 生成JWT
        final String token = jwtUtil.generateToken(userDetails);

        // 封装DTO并返回
        LoginResponseDTO response = new LoginResponseDTO(token, userInDb);
        return Result.success(response);
    }
} 