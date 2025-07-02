package com.strayanimal.platform.forum.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.forum.entity.ForumPost;

public interface ForumPostService extends IService<ForumPost> {

    Page<ForumPost> getPostList(Page<ForumPost> postPage, String keyword);

    ForumPost getPostDetailById(Long id);

    boolean deletePostByIdAndUserId(Long postId, Long userId);

    ForumPost createPost(ForumPost post);
    ForumPost updatePost(ForumPost post);
    void deletePost(Long id);
    void toggleLike(Long postId);
} 