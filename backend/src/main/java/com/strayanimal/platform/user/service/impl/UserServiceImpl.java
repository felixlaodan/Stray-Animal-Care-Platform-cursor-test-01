package com.strayanimal.platform.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.mapper.UserMapper;
import com.strayanimal.platform.user.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author dong
 * @since 2024-07-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Result<String> register(User user) {
        // 检查用户名是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User existingUser = getOne(queryWrapper);

        if (existingUser != null) {
            return Result.error("用户名已存在");
        }

        // TODO: 在后续的安全升级中，密码需要加密存储
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        save(user);
        return Result.success("注册成功");
    }

    @Override
    public Result<User> login(User user) {
        // 根据用户名查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User userInDb = getOne(queryWrapper);

        if (userInDb == null) {
            return Result.error("用户名或密码错误");
        }

        // TODO: 在后续的安全升级中，此处应为加密密码匹配
        if (!user.getPassword().equals(userInDb.getPassword())) {
            return Result.error("用户名或密码错误");
        }

        // 登录成功，返回用户信息（注意：为安全起见，清空密码）
        userInDb.setPassword(null);
        return Result.success(userInDb);
    }
} 