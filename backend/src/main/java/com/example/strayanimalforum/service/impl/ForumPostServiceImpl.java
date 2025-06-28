package com.example.strayanimalforum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.strayanimalforum.entity.ForumComment;
import com.example.strayanimalforum.entity.ForumPost;
import com.example.strayanimalforum.mapper.ForumCommentMapper;
import com.example.strayanimalforum.mapper.ForumPostMapper;
import com.example.strayanimalforum.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumPostServiceImpl implements ForumPostService {

    @Autowired
    private ForumPostMapper forumPostMapper;

    @Autowired
    private ForumCommentMapper forumCommentMapper;

    @Override
    public Page<ForumPost> getPostList(Page<ForumPost> page) {
        return forumPostMapper.selectPage(page, null);
    }

    @Override
    public ForumPost getPostDetailById(Long id) {
        ForumPost post = forumPostMapper.selectById(id);
        if (post != null) {
            QueryWrapper<ForumComment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("post_id", id);
            List<ForumComment> comments = forumCommentMapper.selectList(queryWrapper);
            post.setComments(comments);
        }
        return post;
    }
} 