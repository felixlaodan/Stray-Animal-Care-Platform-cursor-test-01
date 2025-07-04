package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.dto.AdoptionRecordCreationDto;
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
     * 根据用户ID查询其所有的领养记录
     * @param userId 用户ID
     * @return 领养记录列表
     */
    List<AdoptionRecord> getAdoptionRecordsByUserId(Long userId);

} 