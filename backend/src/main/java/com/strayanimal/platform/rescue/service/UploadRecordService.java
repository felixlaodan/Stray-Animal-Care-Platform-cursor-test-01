package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.entity.UploadRecord;

import java.util.List;

public interface UploadRecordService extends IService<UploadRecord> {

    Page<UploadRecord> selectPages(String name, Integer pageNum, Integer pageSize);

    UploadRecord selectById(Long id);

    int insertUploadRecord(UploadRecord uploadRecord);

    int updateUploadRecord(UploadRecord uploadRecord);

    int deleteById(Long id);

    int deleteBatch(List<Long> ids);
} 