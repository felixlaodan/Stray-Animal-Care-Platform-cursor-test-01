package com.strayanimal.platform.rescue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.rescue.dto.UploadRecordCreationDto;
import com.strayanimal.platform.rescue.dto.UploadRecordDto;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;
import com.strayanimal.platform.rescue.entity.UploadRecord;
import com.strayanimal.platform.rescue.entity.UploadRecordImage;
import com.strayanimal.platform.rescue.entity.enums.AdoptionStatus;
import com.strayanimal.platform.rescue.mapper.AdoptionInfoMapper;
import com.strayanimal.platform.rescue.mapper.UploadRecordImageMapper;
import com.strayanimal.platform.rescue.mapper.UploadRecordMapper;
import com.strayanimal.platform.rescue.service.UploadRecordService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UploadRecordServiceImpl extends ServiceImpl<UploadRecordMapper, UploadRecord> implements UploadRecordService {

    private static final Logger log = LoggerFactory.getLogger(UploadRecordServiceImpl.class);

    private final UploadRecordMapper uploadRecordMapper;
    private final UploadRecordImageMapper uploadRecordImageMapper;
    private final AdoptionInfoMapper adoptionInfoMapper;
    private final UserService userService;

    @Override
    @Transactional
    public UploadRecord createUploadRecord(UploadRecordCreationDto creationDto) {
        log.info("开始创建新的上报记录, 接收到数据: {}", creationDto.toString());
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByUsername(username);
        log.info("获取到当前用户: {}, ID: {}", currentUser.getUsername(), currentUser.getId());

        UploadRecord uploadRecord = new UploadRecord();
        BeanUtils.copyProperties(creationDto, uploadRecord);
        uploadRecord.setUserId(currentUser.getId());
        if (creationDto.getReporter() == null || creationDto.getReporter().isEmpty()) {
            uploadRecord.setReporter(currentUser.getNickname());
        }

        log.info("准备插入主记录到数据库: {}", uploadRecord.toString());
        uploadRecordMapper.insert(uploadRecord);
        log.info("主记录插入成功, 生成的ID: {}", uploadRecord.getId());

        // 关键新增：自动创建对应的待领养信息
        createAdoptionInfoFromUpload(uploadRecord);

        List<String> imageUrls = creationDto.getImageUrls();
        if (!CollectionUtils.isEmpty(imageUrls)) {
            log.info("准备插入 {} 条图片记录", imageUrls.size());
            for (String imageUrl : imageUrls) {
                UploadRecordImage image = new UploadRecordImage();
                image.setUploadRecordId(uploadRecord.getId());
                image.setImageUrl(imageUrl);
                uploadRecordImageMapper.insert(image);
            }
            log.info("所有图片记录插入成功");
        } else {
            log.warn("本次上报没有包含任何图片");
        }
        
        log.info("上报记录创建成功, 事务即将提交. Record ID: {}", uploadRecord.getId());
        return uploadRecord;
    }

    private void createAdoptionInfoFromUpload(UploadRecord uploadRecord) {
        log.info("根据上报记录ID: {}, 自动创建待领养信息", uploadRecord.getId());
        AdoptionInfo adoptionInfo = new AdoptionInfo();
        adoptionInfo.setUploadRecordId(uploadRecord.getId());
        adoptionInfo.setName(uploadRecord.getName());
        adoptionInfo.setSpecies(uploadRecord.getSpecies());
        adoptionInfo.setGender(uploadRecord.getGender());
        adoptionInfo.setHealthStatus(uploadRecord.getHealthStatus());
        adoptionInfo.setSterilizationStatus(uploadRecord.getSterilizationStatus());
        adoptionInfo.setDescription(uploadRecord.getDescription());
        adoptionInfo.setAdoptionStatus(AdoptionStatus.AVAILABLE); // 默认状态为可领养
        
        adoptionInfoMapper.insert(adoptionInfo);
        log.info("待领养信息创建成功, 新AdoptionInfo ID: {}", adoptionInfo.getId());
    }

    @Override
    public IPage<UploadRecordDto> pageUserUploadRecords(IPage<UploadRecord> page, Long userId) {
        log.info("开始为用户ID: {} 分页查询上报记录, 页码: {}, 每页数量: {}", userId, page.getCurrent(), page.getSize());
        
        // 关键修复: 构建带 user_id 查询条件的 Wrapper
        QueryWrapper<UploadRecord> queryWrapper = new QueryWrapper<UploadRecord>()
                .eq("user_id", userId)
                .orderByDesc("create_time");

        IPage<UploadRecord> recordPage = uploadRecordMapper.selectPage(page, queryWrapper);
        List<UploadRecord> records = recordPage.getRecords();

        if (CollectionUtils.isEmpty(records)) {
            log.info("用户ID: {} 在当前分页条件下没有找到任何记录", userId);
            return new Page<UploadRecordDto>().setTotal(0).setCurrent(page.getCurrent()).setSize(page.getSize());
        }

        log.info("查询到 {} 条主记录, 准备查询关联图片", records.size());
        List<Long> recordIds = records.stream().map(UploadRecord::getId).collect(Collectors.toList());
        QueryWrapper<UploadRecordImage> imageQueryWrapper = new QueryWrapper<UploadRecordImage>()
                .in("upload_record_id", recordIds);
        List<UploadRecordImage> images = uploadRecordImageMapper.selectList(imageQueryWrapper);
        log.info("查询到 {} 条关联图片记录", images.size());

        Map<Long, List<String>> imagesMap = images.stream()
                .collect(Collectors.groupingBy(
                        UploadRecordImage::getUploadRecordId,
                        Collectors.mapping(UploadRecordImage::getImageUrl, Collectors.toList())
                ));

        List<UploadRecordDto> dtoList = records.stream().map(record -> {
            UploadRecordDto dto = new UploadRecordDto();
            BeanUtils.copyProperties(record, dto);
            dto.setImageUrls(imagesMap.getOrDefault(record.getId(), Collections.emptyList()));
            return dto;
        }).collect(Collectors.toList());

        IPage<UploadRecordDto> resultPage = new Page<>(recordPage.getCurrent(), recordPage.getSize(), recordPage.getTotal());
        resultPage.setRecords(dtoList);
        log.info("组装DTO完成, 返回分页结果. 总记录数: {}", resultPage.getTotal());
        return resultPage;
    }
}