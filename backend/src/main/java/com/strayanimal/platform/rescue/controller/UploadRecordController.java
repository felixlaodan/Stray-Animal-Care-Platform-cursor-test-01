package com.strayanimal.platform.rescue.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.rescue.dto.UploadRecordCreationDto;
import com.strayanimal.platform.rescue.dto.UploadRecordDto;
import com.strayanimal.platform.rescue.entity.UploadRecord;
import com.strayanimal.platform.rescue.service.UploadRecordService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rescue/upload-records")
@RequiredArgsConstructor
public class UploadRecordController {

    private final UploadRecordService uploadRecordService;
    private final UserService userService;

    /**
     * 创建新的动物上报记录
     * 需要用户角色
     */
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Result<UploadRecord> createUploadRecord(@RequestBody UploadRecordCreationDto creationDto) {
        UploadRecord createdRecord = uploadRecordService.createUploadRecord(creationDto);
        return Result.success(createdRecord);
    }

    /**
     * 分页查询当前用户的上报记录
     */
    @GetMapping
    public Result<IPage<UploadRecordDto>> getCurrentUserUploadRecordsPage(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        
        User currentUser = userService.findByUsername(userDetails.getUsername());
        
        IPage<UploadRecord> page = new Page<>(pageNum, pageSize);
        IPage<UploadRecordDto> resultPage = uploadRecordService.pageUserUploadRecords(page, currentUser.getId());
        return Result.success(resultPage);
    }
} 