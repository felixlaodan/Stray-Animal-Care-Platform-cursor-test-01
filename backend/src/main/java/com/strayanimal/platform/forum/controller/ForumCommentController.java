package com.strayanimal.platform.forum.controller;

import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.forum.entity.ForumComment;
import com.strayanimal.platform.forum.service.ForumCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class ForumCommentController {

    @Autowired
    private ForumCommentService forumCommentService;

    @DeleteMapping("/{id}")
    public Result<?> deleteComment(@PathVariable Long id, @RequestParam Long userId) {
        boolean success = forumCommentService.deleteCommentByIdAndUserId(id, userId);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error(403, "无权删除或评论不存在");
        }
    }

    @PutMapping("/{id}")
    public Result<?> updateComment(@PathVariable Long id, @RequestParam Long userId, @RequestBody ForumComment commentUpdate) {
        // 1. 查询原始评论
        ForumComment originalComment = forumCommentService.getById(id);

        // 2. 权限校验
        if (originalComment == null || !originalComment.getUserId().equals(userId)) {
            return Result.error(403, "无权修改或评论不存在");
        }

        // 3. 只更新内容
        originalComment.setContent(commentUpdate.getContent());

        // 4. 执行更新
        forumCommentService.updateById(originalComment);

        return Result.success("评论更新成功");
    }
} 