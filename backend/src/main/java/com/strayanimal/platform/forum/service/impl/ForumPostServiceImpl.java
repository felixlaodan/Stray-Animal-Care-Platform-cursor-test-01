package com.strayanimal.platform.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.forum.entity.ForumComment;
import com.strayanimal.platform.forum.entity.ForumPost;
import com.strayanimal.platform.forum.mapper.ForumCommentMapper;
import com.strayanimal.platform.forum.mapper.ForumPostMapper;
import com.strayanimal.platform.forum.service.ForumPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForumPostServiceImpl extends ServiceImpl<ForumPostMapper, ForumPost> implements ForumPostService {

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