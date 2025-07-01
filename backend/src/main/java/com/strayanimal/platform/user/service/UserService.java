package com.strayanimal.platform.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.user.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author dong
 * @since 2024-07-25
 */
public interface UserService extends IService<User>, UserDetailsService {

    /**
     * 用户注册
     * @param user 包含用户名和密码的用户对象
     * @return 注册结果
     */
    Result<String> register(User user);

    User findByUsername(String username);
} 