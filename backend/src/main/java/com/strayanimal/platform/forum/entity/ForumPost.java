package com.strayanimal.platform.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.io.Serializable;

@Data
@TableName("forum_post")
public class ForumPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("image_url")
    private String imageUrl;

    @TableField("author_name")
    private String authorName;

    @TableField("user_id")
    private Long userId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("likes_count")
    private Integer likesCount;

    @TableField(exist = false)
    private List<ForumComment> comments;

    @TableField(exist = false)
    private boolean likedByCurrentUser;
} 