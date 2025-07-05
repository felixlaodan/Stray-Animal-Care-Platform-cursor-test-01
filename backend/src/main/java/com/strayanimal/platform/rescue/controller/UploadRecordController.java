package com.strayanimal.platform.rescue.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.rescue.dto.UploadRecordCreationDto;
import com.strayanimal.platform.rescue.dto.UploadRecordDto;
import com.strayanimal.platform.rescue.dto.UpdateUploadRecordDto;
import com.strayanimal.platform.rescue.entity.UploadRecord;
import com.strayanimal.platform.rescue.entity.UploadRecordImage;
import com.strayanimal.platform.rescue.mapper.UploadRecordImageMapper;
import com.strayanimal.platform.rescue.service.UploadRecordService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rescue/upload-records")
@RequiredArgsConstructor
public class UploadRecordController {

    private final UploadRecordService uploadRecordService;
    private final UserService userService;
    private final UploadRecordImageMapper uploadRecordImageMapper;

    /**
     * 创建新的动物上报记录
     * 需要用户角色
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Result<UploadRecord> createUploadRecord(@RequestBody UploadRecordCreationDto creationDto) {
        UploadRecord createdRecord = uploadRecordService.createUploadRecord(creationDto);
        return Result.success(createdRecord);
    }

    /**
     * 分页查询当前用户的上报记录
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Result<IPage<UploadRecordDto>> getCurrentUserUploadRecordsPage(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String keyword) {
        
        User currentUser = userService.findByUsername(userDetails.getUsername());
        
        IPage<UploadRecord> page = new Page<>(pageNum, pageSize);
        IPage<UploadRecordDto> resultPage = uploadRecordService.pageUserUploadRecords(page, currentUser.getId(), keyword);
        return Result.success(resultPage);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Result<UploadRecordDto> getUploadRecordDetail(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        // 在Service层没有单独的获取详情方法，我们可以临时在这里组装
        // 更好的做法是在Service层也创建一个getById的详情方法
        UploadRecord record = uploadRecordService.getById(id);
        if (record == null) {
            return Result.error(404, "记录未找到");
        }

        // 权限校验
        User currentUser = userService.findByUsername(userDetails.getUsername());
        if (!record.getUserId().equals(currentUser.getId())) {
             return Result.error(403, "无权访问该记录");
        }

        // 组装DTO (这部分逻辑可以抽取到Service)
        UploadRecordDto dto = new UploadRecordDto();
        BeanUtils.copyProperties(record, dto);
        
        // 关键修复：查询并组装图片信息
        List<UploadRecordImage> images = uploadRecordImageMapper.selectList(
            new QueryWrapper<UploadRecordImage>().eq("upload_record_id", record.getId())
        );
        List<String> imageUrls = images.stream().map(UploadRecordImage::getImageUrl).collect(Collectors.toList());
        dto.setImageUrls(imageUrls);
        
        return Result.success(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Result<UploadRecord> updateUploadRecord(@PathVariable Long id, 
                                                   @RequestBody UpdateUploadRecordDto updateDto, 
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findByUsername(userDetails.getUsername());
        try {
            UploadRecord updatedRecord = uploadRecordService.updateUploadRecord(id, updateDto, currentUser.getId());
            return Result.success(updatedRecord);
        } catch (IllegalStateException e) {
            return Result.error(403, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Result<Void> deleteUploadRecord(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findByUsername(userDetails.getUsername());
        try {
            uploadRecordService.deleteUploadRecord(id, currentUser.getId());
            return Result.success();
        } catch (IllegalStateException e) {
            return Result.error(403, e.getMessage());
        }
    }
} 