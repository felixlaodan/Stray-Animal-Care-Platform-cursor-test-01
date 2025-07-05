package com.strayanimal.platform.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.user.dto.AdminUpdateUserDto;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public Result<IPage<User>> getAllUsersPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        
        IPage<User> page = new Page<>(pageNum, pageSize);
        IPage<User> resultPage = adminUserService.pageAllUsers(page, keyword);
        return Result.success(resultPage);
    }

    @PutMapping("/{id}/status")
    public Result<User> toggleUserStatus(@PathVariable Long id) {
        try {
            User updatedUser = adminUserService.toggleUserStatus(id);
            return Result.success(updatedUser);
        } catch (IllegalStateException e) {
            return Result.error(404, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Result<User> updateUser(@PathVariable Long id, @RequestBody AdminUpdateUserDto updateDto) {
        try {
            User updatedUser = adminUserService.adminUpdateUser(id, updateDto);
            return Result.success(updatedUser);
        } catch (IllegalStateException e) {
            return Result.error(404, e.getMessage());
        }
    }
} 