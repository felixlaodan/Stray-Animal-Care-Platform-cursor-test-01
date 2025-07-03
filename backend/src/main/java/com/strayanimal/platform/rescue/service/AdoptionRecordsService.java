package com.strayanimal.platform.rescue.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.strayanimal.platform.rescue.entity.AdoptionRecords;

import java.util.List;

public interface AdoptionRecordsService extends IService<AdoptionRecords> {

    Page<AdoptionRecords> selectPages(String petName, Integer pageNum, Integer pageSize);

    AdoptionRecords selectById(Long id);

    int insertAdoptionRecords(AdoptionRecords adoptionRecords);

    int updateAdoptionRecords(AdoptionRecords adoptionRecords);

    int deleteById(Long id);

    int deleteBatch(List<Long> ids);
} 