package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.dto.AdoptionInfoDto;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;

public interface AdoptionInfoService extends IService<AdoptionInfo> {

    /**
     * 分页查询领养信息，并附带封面图
     * @param page 分页参数
     * @return 包含图片列表的领养信息分页数据
     */
    IPage<AdoptionInfoDto> getAdoptionInfoPage(IPage<AdoptionInfo> page);

    /**
     * 获取单个领养动物的详细信息，包含所有图片
     * @param id adoption_info的主键ID
     * @return 领养信息详情DTO
     */
    AdoptionInfoDto getAdoptionInfoDetail(Long id);
} 