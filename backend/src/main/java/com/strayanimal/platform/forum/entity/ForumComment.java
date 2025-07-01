package com.strayanimal.platform.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ForumComment {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long postId;
    private String content;
    private String authorName;
    private Long userId;

    private LocalDateTime createTime;
} 