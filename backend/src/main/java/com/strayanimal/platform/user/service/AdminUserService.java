package com.strayanimal.platform.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.strayanimal.platform.user.dto.AdminUpdateUserDto;
import com.strayanimal.platform.user.entity.User;

public interface AdminUserService {

    /**
     * (管理员) 分页查询所有用户
     * @param page 分页参数
     * @param keyword 模糊搜索关键词 (用户名或昵称)
     * @return 用户分页数据
     */
    IPage<User> pageAllUsers(IPage<User> page, String keyword);

    /**
     * (管理员) 切换用户状态 (启用/禁用)
     * @param userId 要切换状态的用户ID
     * @return 更新后的用户实体
     */
    User toggleUserStatus(Long userId);

    /**
     * (管理员) 更新用户信息
     * @param userId      用户ID
     * @param updateDto   更新数据
     * @return 更新后的用户实体
     */
    User adminUpdateUser(Long userId, AdminUpdateUserDto updateDto);

} 