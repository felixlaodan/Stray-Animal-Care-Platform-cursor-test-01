package com.strayanimal.platform.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strayanimal.platform.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author dong
 * @since 2024-07-25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

} 