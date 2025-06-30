package com.strayanimal.platform.user.controller;

import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
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
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

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
     * @return 登录结果，成功则包含用户信息
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody User user) {
        // 基本的参数校验
        if (user.getUsername() == null || user.getPassword() == null) {
            return Result.error("用户名和密码不能为空");
        }
        return userService.login(user);
    }
} 