package com.strayanimal.platform.rescue.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.rescue.dto.UploadRecordDto;
import com.strayanimal.platform.rescue.dto.UpdateUploadRecordDto;
import com.strayanimal.platform.rescue.dto.UploadRecordCreationDto;
import com.strayanimal.platform.rescue.entity.UploadRecord;
import com.strayanimal.platform.rescue.service.UploadRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/uploads")
@RequiredArgsConstructor
public class AdminUploadController {

    private final UploadRecordService uploadRecordService;

    @GetMapping
    public Result<IPage<UploadRecordDto>> getAllUploadRecordsPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        
        IPage<UploadRecord> page = new Page<>(pageNum, pageSize);
        IPage<UploadRecordDto> resultPage = uploadRecordService.pageAllUploadRecords(page, keyword);
        return Result.success(resultPage);
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteUploadRecord(@PathVariable Long id) {
        uploadRecordService.adminDeleteUploadRecord(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result<UploadRecordDto> getUploadRecordDetail(@PathVariable Long id) {
        UploadRecordDto record = uploadRecordService.adminGetUploadRecordById(id);
        if (record == null) {
            return Result.error(404, "记录未找到");
        }
        return Result.success(record);
    }

    @PostMapping
    public Result<UploadRecord> createUploadRecord(@RequestBody UploadRecordCreationDto creationDto) {
        UploadRecord createdRecord = uploadRecordService.createUploadRecord(creationDto);
        return Result.success(createdRecord);
    }

    @PutMapping("/{id}")
    public Result<UploadRecord> updateUploadRecord(@PathVariable Long id, @RequestBody UpdateUploadRecordDto updateDto) {
        try {
            UploadRecord updatedRecord = uploadRecordService.adminUpdateUploadRecord(id, updateDto);
            return Result.success(updatedRecord);
        } catch (IllegalStateException e) {
            return Result.error(404, e.getMessage());
        }
    }
} 