package com.strayanimal.platform.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.user.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dong
 * @since 2024-07-25
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param user 包含用户名和密码的用户对象
     * @return 注册结果
     */
    Result<String> register(User user);

    /**
     * 用户登录
     * @param user 包含用户名和密码的用户对象
     * @return 登录结果
     */
    Result<User> login(User user);
} 