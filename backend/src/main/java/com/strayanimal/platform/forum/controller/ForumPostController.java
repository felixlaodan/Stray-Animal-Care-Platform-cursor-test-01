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
import com.strayanimal.platform.forum.dto.CreatePostDto;
import com.strayanimal.platform.forum.dto.UpdatePostDto;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forum-post")
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
    public Result<ForumPost> createPost(@RequestBody CreatePostDto createPostDto,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return Result.error(401, "用户未登录");
        }
        User currentUser = userService.findByUsername(userDetails.getUsername());
        
        ForumPost newPost = new ForumPost();
        newPost.setTitle(createPostDto.getTitle());
        newPost.setContent(createPostDto.getContent());
        newPost.setImageUrl(createPostDto.getImageUrl());
        newPost.setUserId(currentUser.getId());
        newPost.setAuthorName(currentUser.getNickname());
        
        forumPostService.save(newPost);
        return Result.success(newPost);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deletePost(@PathVariable Long id,
                                   @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return Result.error(401, "用户未登录");
        }
        User currentUser = userService.findByUsername(userDetails.getUsername());
        
        ForumPost existingPost = forumPostService.getById(id);
        if (existingPost == null) {
            return Result.error(404, "帖子不存在");
        }
        
        boolean isOwner = existingPost.getUserId().equals(currentUser.getId());
        boolean isAdmin = "ADMIN".equals(currentUser.getRole());

        if (!isOwner && !isAdmin) {
            return Result.error(403, "无权限删除此帖子");
        }
        
        forumPostService.removeById(id);
        return Result.success(null);
    }

    @PutMapping("/{id}")
    public Result<ForumPost> updatePost(@PathVariable Long id, 
                                        @RequestBody UpdatePostDto postUpdateDto,
                                        @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return Result.error(401, "用户未登录");
        }
        User currentUser = userService.findByUsername(userDetails.getUsername());

        ForumPost existingPost = forumPostService.getById(id);
        if (existingPost == null) {
            return Result.error(404, "帖子不存在");
        }

        boolean isOwner = existingPost.getUserId().equals(currentUser.getId());
        boolean isAdmin = "ADMIN".equals(currentUser.getRole());

        if (!isOwner && !isAdmin) {
            return Result.error(403, "无权限编辑此帖子");
        }

        existingPost.setTitle(postUpdateDto.getTitle());
        existingPost.setContent(postUpdateDto.getContent());
        existingPost.setImageUrl(postUpdateDto.getImageUrl());
        
        forumPostService.updateById(existingPost);

        return Result.success(existingPost);
    }

    @PostMapping("/{postId}/like")
    public Result<Void> likePost(@PathVariable Long postId) {
        forumPostService.toggleLike(postId);
        return Result.success();
    }
} 