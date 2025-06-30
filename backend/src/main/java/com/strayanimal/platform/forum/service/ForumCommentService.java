package com.strayanimal.platform.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.forum.entity.ForumComment;

/**
 * 论坛评论服务接口
 */
public interface ForumCommentService extends IService<ForumComment> {
    boolean deleteCommentByIdAndUserId(Long commentId, Long userId);
} 