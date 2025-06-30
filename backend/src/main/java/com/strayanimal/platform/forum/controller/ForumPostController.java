package com.strayanimal.platform.forum.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.forum.entity.ForumComment;
import com.strayanimal.platform.forum.entity.ForumPost;
import com.strayanimal.platform.forum.service.ForumCommentService;
import com.strayanimal.platform.forum.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class ForumPostController {

    @Autowired
    private ForumPostService forumPostService;

    @Autowired
    private ForumCommentService forumCommentService;

    @GetMapping
    public Result<Page<ForumPost>> getPostList(@RequestParam(defaultValue = "1") long page,
                                               @RequestParam(defaultValue = "10") long size) {
        Page<ForumPost> postPage = new Page<>(page, size);
        return Result.success(forumPostService.getPostList(postPage));
    }

    @GetMapping("/{id}")
    public Result<ForumPost> getPostDetailById(@PathVariable Long id) {
        ForumPost postDetail = forumPostService.getPostDetailById(id);
        if (postDetail != null) {
            return Result.success(postDetail);
        } else {
            return Result.error(404, "Post not found");
        }
    }

    @PostMapping("/{postId}/comments")
    public Result<?> addComment(@PathVariable Long postId, @RequestBody ForumComment comment) {
        System.out.println("接收到的评论数据: " + comment.toString());
        comment.setPostId(postId);
        forumCommentService.save(comment);
        return Result.success();
    }

    @PostMapping
    public Result<?> createPost(@RequestBody ForumPost post) {
        forumPostService.save(post);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> deletePost(@PathVariable Long id, @RequestParam Long userId) {
        boolean success = forumPostService.deletePostByIdAndUserId(id, userId);
        if (success) {
            return Result.success("删除成功");
        } else {
            return Result.error(403, "无权删除或帖子不存在");
        }
    }

    @PutMapping("/{id}")
    public Result<?> updatePost(@PathVariable Long id, @RequestParam Long userId, @RequestBody ForumPost postUpdate) {
        // 1. 查询原始帖子
        ForumPost originalPost = forumPostService.getById(id);

        // 2. 权限校验
        if (originalPost == null || !originalPost.getUserId().equals(userId)) {
            return Result.error(403, "无权修改或帖子不存在");
        }

        // 3. 更新字段
        originalPost.setTitle(postUpdate.getTitle());
        originalPost.setContent(postUpdate.getContent());
        // authorName 和 userId 不应在此处更新

        // 4. 执行更新
        forumPostService.updateById(originalPost);

        return Result.success("帖子更新成功");
    }
} 