package com.example.strayanimalforum.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.strayanimalforum.entity.ForumPost;

public interface ForumPostService {

    Page<ForumPost> getPostList(Page<ForumPost> page);

    ForumPost getPostDetailById(Long id);
} 