package com.strayanimal.platform.rescue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.rescue.dto.AdoptionRecordCreationDto;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;
import com.strayanimal.platform.rescue.entity.AdoptionRecord;
import com.strayanimal.platform.rescue.entity.enums.AdoptionStatus;
import com.strayanimal.platform.rescue.mapper.AdoptionInfoMapper;
import com.strayanimal.platform.rescue.mapper.AdoptionRecordMapper;
import com.strayanimal.platform.rescue.service.AdoptionRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdoptionRecordServiceImpl extends ServiceImpl<AdoptionRecordMapper, AdoptionRecord> implements AdoptionRecordService {

    private final AdoptionRecordMapper adoptionRecordMapper;
    private final AdoptionInfoMapper adoptionInfoMapper;
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
    public List<AdoptionRecord> getAdoptionRecordsByUserId(Long userId) {
        return adoptionRecordMapper.selectList(
                new QueryWrapper<AdoptionRecord>().eq("adopter_user_id", userId)
        );
    }
} 