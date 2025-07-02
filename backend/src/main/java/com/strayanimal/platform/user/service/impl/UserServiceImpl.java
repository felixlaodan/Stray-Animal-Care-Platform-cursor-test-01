package com.strayanimal.platform.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.mapper.UserMapper;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Resource
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public Result<String> register(User user) {
        // 检查用户名是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User existingUser = getOne(queryWrapper);

        if (existingUser != null) {
            return Result.error("用户名已存在");
        }

        // 使用Bcrypt对密码进行加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        save(user);
        return Result.success("注册成功");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 根据用户名从数据库查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = getOne(queryWrapper);

        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }

        // 将我们自己的User实体转换为Spring Security需要的UserDetails对象
        // 从数据库动态加载用户的角色
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_" + user.getRole())
        );
    }

    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return getOne(queryWrapper);
    }
} 