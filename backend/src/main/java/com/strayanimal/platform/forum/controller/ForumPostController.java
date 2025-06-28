package com.strayanimal.platform.forum.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.forum.entity.ForumPost;
import com.strayanimal.platform.forum.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class ForumPostController {

    @Autowired
    private ForumPostService forumPostService;

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
} 