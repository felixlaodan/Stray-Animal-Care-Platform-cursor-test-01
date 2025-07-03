package com.strayanimal.platform.forum.controller;

import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.core.util.SecurityUtil;
import com.strayanimal.platform.forum.entity.ForumComment;
import com.strayanimal.platform.forum.service.ForumCommentService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forum-comment")
public class ForumCommentController {

    @Resource
    private UserService userService;

    @Resource
    private ForumCommentService forumCommentService;

    @PostMapping
    public Result<ForumComment> addComment(@RequestBody ForumComment comment) {
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername == null) {
            return Result.error("用户未登录，无法评论");
        }

        User currentUser = userService.findByUsername(currentUsername);
        if (currentUser == null) {
            return Result.error("用户信息不存在");
        }

        comment.setUserId(currentUser.getId());
        comment.setAuthorName(currentUser.getNickname());

        forumCommentService.save(comment);
        return Result.success(comment);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteComment(@PathVariable Long id) {
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername == null) {
            return Result.error("用户未登录");
        }

        User currentUser = userService.findByUsername(currentUsername);
        if (currentUser == null) {
            return Result.error("用户信息不存在");
        }
        
        ForumComment existingComment = forumCommentService.getById(id);
        if (existingComment == null) {
            return Result.error("评论不存在");
        }

        // 权限检查：只有评论的作者才能删除
        if (!existingComment.getUserId().equals(currentUser.getId())) {
            return Result.error("无权限删除此评论");
        }

        forumCommentService.removeById(id);
        return Result.success(null);
    }

    /**
     * 更新评论
     * @param id 评论ID
     * @param comment 包含新内容的评论对象
     * @return 更新后的评论
     */
    @PutMapping("/{id}")
    public Result<ForumComment> updateComment(@PathVariable Long id, @RequestBody ForumComment comment) {
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername == null) {
            return Result.error("用户未登录");
        }

        User currentUser = userService.findByUsername(currentUsername);
        if (currentUser == null) {
            return Result.error("用户信息不存在");
        }

        ForumComment existingComment = forumCommentService.getById(id);
        if (existingComment == null) {
            return Result.error("评论不存在");
        }

        // 权限检查：只有评论的作者才能编辑
        if (!existingComment.getUserId().equals(currentUser.getId())) {
            return Result.error("无权限编辑此评论");
        }

        // 只允许更新内容
        existingComment.setContent(comment.getContent());
        forumCommentService.updateById(existingComment);

        return Result.success(existingComment);
    }

    /**
     * 根据帖子ID获取评论列表
     * @param postId 帖子ID
     * @return 该帖子的评论列表
     */
    @GetMapping("/post/{postId}")
    public Result<java.util.List<ForumComment>> getCommentsByPostId(@PathVariable Long postId) {
        java.util.List<ForumComment> comments = forumCommentService.lambdaQuery()
                .eq(ForumComment::getPostId, postId)
                .orderByDesc(ForumComment::getCreateTime) // 按创建时间降序排序，新评论在前
                .list();
        return Result.success(comments);
    }
} 