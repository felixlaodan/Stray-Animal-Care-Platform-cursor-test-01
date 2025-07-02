package com.strayanimal.platform.forum.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.forum.entity.UserPostLike;
import com.strayanimal.platform.forum.mapper.UserPostLikeMapper;
import com.strayanimal.platform.forum.service.UserPostLikeService;
import org.springframework.stereotype.Service;

@Service
public class UserPostLikeServiceImpl extends ServiceImpl<UserPostLikeMapper, UserPostLike> implements UserPostLikeService {
} 