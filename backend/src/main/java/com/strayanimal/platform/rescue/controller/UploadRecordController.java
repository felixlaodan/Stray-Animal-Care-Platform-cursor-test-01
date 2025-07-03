package com.strayanimal.platform.rescue.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.rescue.entity.UploadRecord;
import com.strayanimal.platform.rescue.service.UploadRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rescue/upload-records")
public class UploadRecordController {

    @Autowired
    private UploadRecordService uploadRecordService;

    @GetMapping
    public Result<Page<UploadRecord>> selectPages(@RequestParam(defaultValue = "") String name,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<UploadRecord> page = uploadRecordService.selectPages(name, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<UploadRecord> selectById(@PathVariable Long id) {
        UploadRecord uploadRecord = uploadRecordService.selectById(id);
        return Result.success(uploadRecord);
    }

    @PostMapping
    public Result<Void> insertUploadRecord(@RequestBody UploadRecord uploadRecord) {
        uploadRecordService.insertUploadRecord(uploadRecord);
        return Result.success();
    }

    @PutMapping
    public Result<Void> updateUploadRecord(@RequestBody UploadRecord uploadRecord) {
        uploadRecordService.updateUploadRecord(uploadRecord);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        uploadRecordService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<Long> ids) {
        uploadRecordService.deleteBatch(ids);
        return Result.success();
    }
} 