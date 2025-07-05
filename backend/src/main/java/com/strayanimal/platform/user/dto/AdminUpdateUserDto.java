package com.strayanimal.platform.user.dto;

import lombok.Data;

@Data
public class AdminUpdateUserDto {
    
    private String nickname;
    private String password; // 可选，不为空时才更新

} 