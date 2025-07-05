package com.strayanimal.platform.forum.dto;

import lombok.Data;

@Data
public class CreatePostDto {
    private String title;
    private String content;
    private String imageUrl;
} 