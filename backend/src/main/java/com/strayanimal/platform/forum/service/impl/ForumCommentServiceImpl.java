package com.strayanimal.platform.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.forum.entity.ForumComment;
import com.strayanimal.platform.forum.mapper.ForumCommentMapper;
import com.strayanimal.platform.forum.service.ForumCommentService;
import org.springframework.stereotype.Service;

/**
 * 论坛评论服务实现类
 */
@Service
public class ForumCommentServiceImpl extends ServiceImpl<ForumCommentMapper, ForumComment> implements ForumCommentService {

    @Override
    public boolean deleteCommentByIdAndUserId(Long commentId, Long userId) {
        QueryWrapper<ForumComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", commentId).eq("user_id", userId);
        int affectedRows = baseMapper.delete(queryWrapper);
        return affectedRows > 0;
    }
} 