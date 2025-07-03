package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;

import java.util.List;

public interface AdoptionInfoService extends IService<AdoptionInfo> {

    Page<AdoptionInfo> selectPages(String name, Integer pageNum, Integer pageSize);

    AdoptionInfo selectById(Long id);

    int insertAdoptionInfo(AdoptionInfo adoptionInfo);

    int updateAdoptionInfo(AdoptionInfo adoptionInfo);

    int deleteById(Long id);

    int deleteBatch(List<Long> ids);
} 