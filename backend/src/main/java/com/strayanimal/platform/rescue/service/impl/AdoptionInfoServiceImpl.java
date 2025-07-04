package com.strayanimal.platform.rescue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.rescue.dto.AdoptionInfoDto;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;
import com.strayanimal.platform.rescue.entity.UploadRecordImage;
import com.strayanimal.platform.rescue.mapper.AdoptionInfoMapper;
import com.strayanimal.platform.rescue.mapper.UploadRecordImageMapper;
import com.strayanimal.platform.rescue.service.AdoptionInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdoptionInfoServiceImpl extends ServiceImpl<AdoptionInfoMapper, AdoptionInfo> implements AdoptionInfoService {

    private final AdoptionInfoMapper adoptionInfoMapper;
    private final UploadRecordImageMapper uploadRecordImageMapper;

    @Override
    public IPage<AdoptionInfoDto> getAdoptionInfoPage(IPage<AdoptionInfo> page) {
        // 1. 分页查询主信息
        IPage<AdoptionInfo> infoPage = adoptionInfoMapper.selectPage(page, new QueryWrapper<AdoptionInfo>().orderByDesc("create_time"));
        List<AdoptionInfo> infos = infoPage.getRecords();

        if (CollectionUtils.isEmpty(infos)) {
            return new Page<AdoptionInfoDto>().setTotal(0);
        }

        // 2. 一次性获取所有关联的图片 (N+1查询优化)
        List<Long> uploadRecordIds = infos.stream().map(AdoptionInfo::getUploadRecordId).collect(Collectors.toList());
        List<UploadRecordImage> images = uploadRecordImageMapper.selectList(
                new QueryWrapper<UploadRecordImage>().in("upload_record_id", uploadRecordIds)
        );

        // 3. 按 upload_record_id 分组
        Map<Long, List<UploadRecordImage>> imagesMap = images.stream()
                .collect(Collectors.groupingBy(UploadRecordImage::getUploadRecordId));

        // 4. 组装DTO
        List<AdoptionInfoDto> dtoList = infos.stream().map(info -> {
            AdoptionInfoDto dto = new AdoptionInfoDto();
            BeanUtils.copyProperties(info, dto);
            List<UploadRecordImage> relatedImages = imagesMap.getOrDefault(info.getUploadRecordId(), Collections.emptyList());
            if (!relatedImages.isEmpty()) {
                // 设置封面图为第一张
                dto.setCoverImageUrl(relatedImages.get(0).getImageUrl());
            }
            return dto;
        }).collect(Collectors.toList());

        IPage<AdoptionInfoDto> resultPage = new Page<>(infoPage.getCurrent(), infoPage.getSize(), infoPage.getTotal());
        resultPage.setRecords(dtoList);
        return resultPage;
    }

    @Override
    public AdoptionInfoDto getAdoptionInfoDetail(Long id) {
        AdoptionInfo info = this.getById(id);
        if (info == null) {
            return null;
        }

        AdoptionInfoDto dto = new AdoptionInfoDto();
        BeanUtils.copyProperties(info, dto);

        // 获取所有图片
        List<UploadRecordImage> images = uploadRecordImageMapper.selectList(
                new QueryWrapper<UploadRecordImage>().eq("upload_record_id", info.getUploadRecordId())
        );
        List<String> imageUrls = images.stream().map(UploadRecordImage::getImageUrl).collect(Collectors.toList());
        dto.setImageUrls(imageUrls);
        if (!imageUrls.isEmpty()) {
            dto.setCoverImageUrl(imageUrls.get(0));
        }

        return dto;
    }
} 