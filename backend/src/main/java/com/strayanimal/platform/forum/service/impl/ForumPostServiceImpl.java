package com.strayanimal.platform.forum.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.forum.entity.ForumComment;
import com.strayanimal.platform.forum.entity.ForumPost;
import com.strayanimal.platform.forum.entity.UserPostLike;
import com.strayanimal.platform.forum.mapper.ForumCommentMapper;
import com.strayanimal.platform.forum.mapper.ForumPostMapper;
import com.strayanimal.platform.forum.service.ForumPostService;
import com.strayanimal.platform.forum.service.UserPostLikeService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ForumPostServiceImpl extends ServiceImpl<ForumPostMapper, ForumPost> implements ForumPostService {

    @Autowired
    private ForumPostMapper forumPostMapper;

    @Autowired
    private ForumCommentMapper forumCommentMapper;

    @Resource
    private UserService userService;

    @Resource
    private UserPostLikeService userPostLikeService;

    @Override
    public Page<ForumPost> getPostList(Page<ForumPost> postPage, String keyword) {
        QueryWrapper<ForumPost> queryWrapper = new QueryWrapper<>();
        
        if (StringUtils.hasText(keyword)) {
            // 如果关键字不为空，则添加模糊查询条件
            // 查询标题或内容中包含关键字的帖子
            queryWrapper.and(qw -> qw.like("title", keyword).or().like("content", keyword));
        }

        queryWrapper.orderByDesc("create_time");
        Page<ForumPost> page = baseMapper.selectPage(postPage, queryWrapper);

        // 高效处理"当前用户是否点赞"
        String currentUsername = com.strayanimal.platform.core.util.SecurityUtil.getCurrentUsername();
        if (currentUsername != null && !page.getRecords().isEmpty()) {
            User currentUser = userService.findByUsername(currentUsername);
            if (currentUser != null) {
                // 1. 获取当前页所有帖子的ID
                List<Long> postIds = page.getRecords().stream().map(ForumPost::getId).collect(Collectors.toList());
                
                // 2. 一次性查询出当前用户对这些帖子点过赞的记录
                Set<Long> likedPostIds = userPostLikeService.lambdaQuery()
                        .eq(UserPostLike::getUserId, currentUser.getId())
                        .in(UserPostLike::getPostId, postIds)
                        .list()
                        .stream()
                        .map(UserPostLike::getPostId)
                        .collect(Collectors.toSet());

                // 3. 在内存中设置每个帖子的点赞状态
                page.getRecords().forEach(post -> post.setLikedByCurrentUser(likedPostIds.contains(post.getId())));
            }
        }
        return page;
    }

    @Override
    public ForumPost getPostDetailById(Long id) {
        ForumPost post = baseMapper.selectById(id);
        if (post != null) {
            // 加载评论
            QueryWrapper<ForumComment> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("post_id", id);
            List<ForumComment> comments = forumCommentMapper.selectList(queryWrapper);
            post.setComments(comments);

            // 检查当前用户是否已点赞
            String currentUsername = com.strayanimal.platform.core.util.SecurityUtil.getCurrentUsername();
            if (currentUsername != null) {
                User currentUser = userService.findByUsername(currentUsername);
                if (currentUser != null) {
                    UserPostLike like = userPostLikeService.lambdaQuery()
                            .eq(UserPostLike::getUserId, currentUser.getId())
                            .eq(UserPostLike::getPostId, post.getId())
                            .one();
                    post.setLikedByCurrentUser(like != null);
                }
            }
        }
        return post;
    }

    @Override
    @Transactional
    public void toggleLike(Long postId) {
        String currentUsername = com.strayanimal.platform.core.util.SecurityUtil.getCurrentUsername();
        User currentUser = userService.findByUsername(currentUsername);
        if (currentUser == null) {
            // 在实际应用中，由于有Spring Security保护，理论上不会发生。
            // 但作为健壮性检查，可以抛出异常或直接返回。
            return;
        }
        Long userId = currentUser.getId();

        // 检查是否已点赞
        UserPostLike existingLike = userPostLikeService.lambdaQuery()
                .eq(UserPostLike::getUserId, userId)
                .eq(UserPostLike::getPostId, postId)
                .one();

        if (existingLike != null) {
            // 已点赞，则取消点赞
            userPostLikeService.removeById(existingLike.getId());
            // 帖子点赞数-1
            baseMapper.update(null,
                    new com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper<ForumPost>()
                            .eq(ForumPost::getId, postId)
                            .setSql("likes_count = likes_count - 1"));
        } else {
            // 未点赞，则添加点赞
            UserPostLike newLike = new UserPostLike();
            newLike.setUserId(userId);
            newLike.setPostId(postId);
            userPostLikeService.save(newLike);
            // 帖子点赞数+1
            baseMapper.update(null,
                    new com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper<ForumPost>()
                            .eq(ForumPost::getId, postId)
                            .setSql("likes_count = likes_count + 1"));
        }
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