package com.strayanimal.platform.forum.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.forum.entity.ForumPost;

public interface ForumPostService extends IService<ForumPost> {

    Page<ForumPost> getPostList(Page<ForumPost> page);

    ForumPost getPostDetailById(Long id);
} 