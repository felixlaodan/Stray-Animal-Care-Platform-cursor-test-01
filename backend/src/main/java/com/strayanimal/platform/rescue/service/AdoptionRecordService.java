package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.dto.AdoptionRecordCreationDto;
import com.strayanimal.platform.rescue.dto.AdoptionRecordDetailDto;
import com.strayanimal.platform.rescue.dto.UpdateAdoptionRecordDto;
import com.strayanimal.platform.rescue.entity.AdoptionRecord;
import java.util.List;

public interface AdoptionRecordService extends IService<AdoptionRecord> {

    /**
     * 创建领养记录
     * @param creationDto 领养表单数据
     * @param adopterUserId 领养用户ID
     * @return 创建的领养记录实体
     */
    AdoptionRecord createAdoptionRecord(AdoptionRecordCreationDto creationDto, Long adopterUserId);

    /**
     * 更新领养记录
     * @param recordId      记录ID
     * @param updateDto     更新数据
     * @param adopterUserId 操作用户ID
     * @return 更新后的记录
     */
    AdoptionRecord updateAdoptionRecord(Long recordId, UpdateAdoptionRecordDto updateDto, Long adopterUserId);

    /**
     * 根据用户ID查询其所有的领养记录
     * @param userId 用户ID
     * @return 包含动物详情的领养记录列表
     */
    List<AdoptionRecordDetailDto> getAdoptionRecordsByUserId(Long userId);

} 