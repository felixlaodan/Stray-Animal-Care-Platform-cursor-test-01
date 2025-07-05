package com.strayanimal.platform.forum.dto;

import lombok.Data;

@Data
public class UpdatePostDto {
    private String title;
    private String content;
    private String imageUrl;
} 