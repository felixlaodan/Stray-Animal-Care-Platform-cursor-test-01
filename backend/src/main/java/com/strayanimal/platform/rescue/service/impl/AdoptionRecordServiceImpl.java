package com.strayanimal.platform.rescue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.rescue.dto.AdoptionInfoDto;
import com.strayanimal.platform.rescue.dto.AdoptionRecordCreationDto;
import com.strayanimal.platform.rescue.dto.AdoptionRecordDetailDto;
import com.strayanimal.platform.rescue.dto.UpdateAdoptionRecordDto;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;
import com.strayanimal.platform.rescue.entity.AdoptionRecord;
import com.strayanimal.platform.rescue.entity.UploadRecordImage;
import com.strayanimal.platform.rescue.entity.enums.AdoptionStatus;
import com.strayanimal.platform.rescue.mapper.AdoptionInfoMapper;
import com.strayanimal.platform.rescue.mapper.AdoptionRecordMapper;
import com.strayanimal.platform.rescue.mapper.UploadRecordImageMapper;
import com.strayanimal.platform.rescue.service.AdoptionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdoptionRecordServiceImpl extends ServiceImpl<AdoptionRecordMapper, AdoptionRecord> implements AdoptionRecordService {

    private final AdoptionRecordMapper adoptionRecordMapper;
    private final AdoptionInfoMapper adoptionInfoMapper;
    private final UploadRecordImageMapper uploadRecordImageMapper;
    // 未来可能需要加密服务来处理身份证信息
    // private final EncryptionService encryptionService;

    @Override
    @Transactional
    public AdoptionRecord createAdoptionRecord(AdoptionRecordCreationDto creationDto, Long adopterUserId) {
        // 1. 检查动物是否还可领养
        AdoptionInfo adoptionInfo = adoptionInfoMapper.selectById(creationDto.getAdoptionInfoId());
        if (adoptionInfo == null || adoptionInfo.getAdoptionStatus() == AdoptionStatus.ADOPTED) {
            throw new IllegalStateException("该动物已被领养或信息不存在。");
        }

        // 2. 更新动物领养状态为"已被领养"
        adoptionInfo.setAdoptionStatus(AdoptionStatus.ADOPTED);
        adoptionInfoMapper.updateById(adoptionInfo);

        // 3. 创建并保存领养记录
        AdoptionRecord newRecord = new AdoptionRecord();
        BeanUtils.copyProperties(creationDto, newRecord);
        newRecord.setAdopterUserId(adopterUserId);
        
        // 注意：在真实项目中，身份证号应加密存储
        // newRecord.setAdopterIdCard(encryptionService.encrypt(creationDto.getAdopterIdCard()));
        
        adoptionRecordMapper.insert(newRecord);

        return newRecord;
    }

    @Override
    @Transactional
    public AdoptionRecord updateAdoptionRecord(Long recordId, UpdateAdoptionRecordDto updateDto, Long adopterUserId) {
        // 1. 权限检查：确保操作者是记录的拥有者
        AdoptionRecord existingRecord = adoptionRecordMapper.selectById(recordId);
        if (existingRecord == null || !existingRecord.getAdopterUserId().equals(adopterUserId)) {
            throw new IllegalStateException("无权修改该领养记录或记录不存在。");
        }

        // 2. 更新记录信息
        BeanUtils.copyProperties(updateDto, existingRecord);
        adoptionRecordMapper.updateById(existingRecord);

        return existingRecord;
    }

    @Override
    public List<AdoptionRecordDetailDto> getAdoptionRecordsByUserId(Long userId) {
        // 1. 查询该用户的所有领养记录
        List<AdoptionRecord> records = adoptionRecordMapper.selectList(
                new QueryWrapper<AdoptionRecord>().eq("adopter_user_id", userId).orderByDesc("create_time")
        );

        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }

        // 2. 收集所有关联的 adoption_info_id
        List<Long> adoptionInfoIds = records.stream().map(AdoptionRecord::getAdoptionInfoId).collect(Collectors.toList());

        // 3. 一次性查询所有相关的 adoption_info
        Map<Long, AdoptionInfo> infoMap = adoptionInfoMapper.selectBatchIds(adoptionInfoIds).stream()
                .collect(Collectors.toMap(AdoptionInfo::getId, info -> info));
        
        // 4. 一次性查询所有相关的图片
        List<Long> uploadRecordIds = infoMap.values().stream().map(AdoptionInfo::getUploadRecordId).collect(Collectors.toList());
        Map<Long, List<UploadRecordImage>> imagesMap = uploadRecordImageMapper.selectList(
                new QueryWrapper<UploadRecordImage>().in("upload_record_id", uploadRecordIds)
        ).stream().collect(Collectors.groupingBy(UploadRecordImage::getUploadRecordId));
        

        // 5. 组装最终的DTO列表
        return records.stream().map(record -> {
            AdoptionRecordDetailDto recordDto = new AdoptionRecordDetailDto();
            BeanUtils.copyProperties(record, recordDto);

            AdoptionInfo info = infoMap.get(record.getAdoptionInfoId());
            if (info != null) {
                AdoptionInfoDto infoDto = new AdoptionInfoDto();
                BeanUtils.copyProperties(info, infoDto);

                List<UploadRecordImage> relatedImages = imagesMap.getOrDefault(info.getUploadRecordId(), Collections.emptyList());
                List<String> imageUrls = relatedImages.stream().map(UploadRecordImage::getImageUrl).collect(Collectors.toList());
                infoDto.setImageUrls(imageUrls);
                if (!imageUrls.isEmpty()) {
                    infoDto.setCoverImageUrl(imageUrls.get(0));
                }
                recordDto.setAnimalInfo(infoDto);
            }
            return recordDto;
        }).collect(Collectors.toList());
    }
} 