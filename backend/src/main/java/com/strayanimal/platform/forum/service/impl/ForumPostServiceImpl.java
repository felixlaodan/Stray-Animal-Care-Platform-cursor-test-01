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
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ForumPostServiceImpl extends ServiceImpl<ForumPostMapper, ForumPost> implements ForumPostService {

    @Autowired
    private ForumPostMapper forumPostMapper;

    @Autowired
    private ForumCommentMapper forumCommentMapper;

    @Override
    public Page<ForumPost> getPostList(Page<ForumPost> postPage, String keyword) {
        QueryWrapper<ForumPost> queryWrapper = new QueryWrapper<>();
        
        if (StringUtils.hasText(keyword)) {
            // 如果关键字不为空，则添加模糊查询条件
            // 查询标题或内容中包含关键字的帖子
            queryWrapper.and(qw -> qw.like("title", keyword).or().like("content", keyword));
        }

        queryWrapper.orderByDesc("create_time");
        return baseMapper.selectPage(postPage, queryWrapper);
    }

    @Override
    public ForumPost getPostDetailById(Long id) {
        ForumPost post = baseMapper.selectById(id);
        if (post != null) {
            QueryWrapper<ForumComment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("post_id", id);
            List<ForumComment> comments = forumCommentMapper.selectList(queryWrapper);
            post.setComments(comments);
        }
        return post;
    }

    @Override
    public boolean deletePostByIdAndUserId(Long postId, Long userId) {
        QueryWrapper<ForumPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", postId).eq("user_id", userId);
        int affectedRows = baseMapper.delete(queryWrapper);
        return affectedRows > 0;
    }

    @Override
    public ForumPost createPost(ForumPost post) {
        save(post);
        return post;
    }

    @Override
    public ForumPost updatePost(ForumPost post) {
        updateById(post);
        return post;
    }

    @Override
    public void deletePost(Long id) {
        removeById(id);
    }
} 