package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.dto.UploadRecordCreationDto;
import com.strayanimal.platform.rescue.dto.UploadRecordDto;
import com.strayanimal.platform.rescue.entity.UploadRecord;

public interface UploadRecordService extends IService<UploadRecord> {

    /**
     * 创建新的动物上报记录
     * @param creationDto 上报记录数据
     * @return 创建成功后的记录详情
     */
    UploadRecord createUploadRecord(UploadRecordCreationDto creationDto);

    /**
     * 分页查询指定用户的上报记录，并附带图片信息
     * @param page 分页参数
     * @param userId 要查询的用户ID
     * @return 包含图片列表的上报记录分页数据
     */
    IPage<UploadRecordDto> pageUserUploadRecords(IPage<UploadRecord> page, Long userId);

} 