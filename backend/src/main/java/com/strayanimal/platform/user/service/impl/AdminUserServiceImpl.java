package com.strayanimal.platform.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.user.dto.AdminUpdateUserDto;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.entity.enums.UserStatus;
import com.strayanimal.platform.user.mapper.UserMapper;
import com.strayanimal.platform.user.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl extends ServiceImpl<UserMapper, User> implements AdminUserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public IPage<User> pageAllUsers(IPage<User> page, String keyword) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            queryWrapper.and(qw -> qw
                    .like("username", keyword)
                    .or()
                    .like("nickname", keyword)
            );
        }
        queryWrapper.orderByDesc("create_time");
        return userMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public User toggleUserStatus(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new IllegalStateException("用户不存在");
        }

        // 切换状态
        if (user.getStatus() == UserStatus.ACTIVE) {
            user.setStatus(UserStatus.DISABLED);
        } else {
            user.setStatus(UserStatus.ACTIVE);
        }

        userMapper.updateById(user);
        return user;
    }

    @Override
    @Transactional
    public User adminUpdateUser(Long userId, AdminUpdateUserDto updateDto) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new IllegalStateException("用户不存在");
        }

        // 更新昵称
        if (updateDto.getNickname() != null && !updateDto.getNickname().trim().isEmpty()) {
            user.setNickname(updateDto.getNickname());
        }

        // 如果提供了新密码，则更新密码
        if (updateDto.getPassword() != null && !updateDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }

        userMapper.updateById(user);
        return user;
    }
} 