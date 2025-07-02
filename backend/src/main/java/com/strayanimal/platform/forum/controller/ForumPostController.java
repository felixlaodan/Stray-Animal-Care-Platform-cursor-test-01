package com.strayanimal.platform.forum.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.core.util.SecurityUtil;
import com.strayanimal.platform.forum.entity.ForumComment;
import com.strayanimal.platform.forum.entity.ForumPost;
import com.strayanimal.platform.forum.service.ForumCommentService;
import com.strayanimal.platform.forum.service.ForumPostService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forum-post")
public class ForumPostController {

    @Autowired
    private ForumPostService forumPostService;

    @Autowired
    private ForumCommentService forumCommentService;

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public Result<Page<ForumPost>> getPostList(@RequestParam(defaultValue = "1") int pageNum,
                                               @RequestParam(defaultValue = "10") int pageSize,
                                               @RequestParam(required = false) String keyword,
                                               @RequestParam(defaultValue = "latest") String sortBy) {
        Page<ForumPost> postPage = new Page<>(pageNum, pageSize);
        return Result.success(forumPostService.getPostList(postPage, keyword, sortBy));
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
    public Result<ForumPost> createPost(@RequestBody ForumPost post) {
        // 获取当前登录用户
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername == null) {
            return Result.error("用户未登录");
        }
        
        // 查询用户信息并设置到帖子中
        User currentUser = userService.findByUsername(currentUsername);
        if (currentUser == null) {
            return Result.error("用户信息不存在");
        }
        
        post.setUserId(currentUser.getId());
        post.setAuthorName(currentUser.getNickname());
        
        forumPostService.save(post);
        return Result.success(post);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deletePost(@PathVariable Long id) {
        // 获取当前登录用户
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername == null) {
            return Result.error("用户未登录");
        }
        
        // 查询用户信息
        User currentUser = userService.findByUsername(currentUsername);
        if (currentUser == null) {
            return Result.error("用户信息不存在");
        }
        
        // 检查权限：只有帖子作者才能删除
        ForumPost existingPost = forumPostService.getById(id);
        if (existingPost == null) {
            return Result.error("帖子不存在");
        }
        
        if (!existingPost.getUserId().equals(currentUser.getId())) {
            return Result.error("无权限删除此帖子");
        }
        
        forumPostService.removeById(id);
        return Result.success(null);
    }

    @PutMapping("/{id}")
    public Result<ForumPost> updatePost(@PathVariable Long id, @RequestBody ForumPost postUpdate) {
        // 获取当前登录用户
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername == null) {
            return Result.error("用户未登录");
        }

        // 查询用户信息
        User currentUser = userService.findByUsername(currentUsername);
        if (currentUser == null) {
            return Result.error("用户信息不存在");
        }

        // 检查权限：只有帖子作者才能编辑
        ForumPost existingPost = forumPostService.getById(id);
        if (existingPost == null) {
            return Result.error("帖子不存在");
        }

        if (!existingPost.getUserId().equals(currentUser.getId())) {
            return Result.error("无权限编辑此帖子");
        }

        // 更新字段
        existingPost.setTitle(postUpdate.getTitle());
        existingPost.setContent(postUpdate.getContent());
        existingPost.setImageUrl(postUpdate.getImageUrl());
        
        forumPostService.updateById(existingPost);

        return Result.success(existingPost);
    }

    @PostMapping("/{postId}/like")
    public Result<Void> likePost(@PathVariable Long postId) {
        forumPostService.toggleLike(postId);
        return Result.success();
    }
} 