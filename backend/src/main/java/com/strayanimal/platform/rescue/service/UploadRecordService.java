package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.dto.UploadRecordCreationDto;
import com.strayanimal.platform.rescue.dto.UploadRecordDto;
import com.strayanimal.platform.rescue.dto.UpdateUploadRecordDto;
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
     * @param keyword 模糊搜索关键词 (可选)
     * @return 包含图片列表的上报记录分页数据
     */
    IPage<UploadRecordDto> pageUserUploadRecords(IPage<UploadRecord> page, Long userId, String keyword);

    /**
     * 更新上报记录
     * @param recordId 记录ID
     * @param updateDto 更新数据
     * @param userId 操作用户ID
     * @return 更新后的记录
     */
    UploadRecord updateUploadRecord(Long recordId, UpdateUploadRecordDto updateDto, Long userId);

    /**
     * (管理员) 更新上报记录
     * @param recordId 记录ID
     * @param updateDto 更新数据
     * @return 更新后的记录
     */
    UploadRecord adminUpdateUploadRecord(Long recordId, UpdateUploadRecordDto updateDto);

    /**
     * (管理员) 根据ID获取单个上报记录详情
     * @param recordId 记录ID
     * @return 包含图片列表的上报记录详情
     */
    UploadRecordDto adminGetUploadRecordById(Long recordId);

    /**
     * (管理员) 分页查询所有用户的上报记录
     * @param page 分页参数
     * @param keyword 模糊搜索关键词 (可选)
     * @return 包含图片列表的上报记录分页数据
     */
    IPage<UploadRecordDto> pageAllUploadRecords(IPage<UploadRecord> page, String keyword);

    /**
     * 删除上报记录
     * @param recordId 记录ID
     * @param userId 操作用户ID
     */
    void deleteUploadRecord(Long recordId, Long userId);

    /**
     * (管理员) 删除上报记录
     * @param recordId 记录ID
     */
    void adminDeleteUploadRecord(Long recordId);

} 