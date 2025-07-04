package com.strayanimal.platform.rescue.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.rescue.dto.AdoptionInfoDto;
import com.strayanimal.platform.rescue.dto.AdoptionRecordCreationDto;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;
import com.strayanimal.platform.rescue.entity.AdoptionRecord;
import com.strayanimal.platform.rescue.service.AdoptionInfoService;
import com.strayanimal.platform.rescue.service.AdoptionRecordService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adoption")
@RequiredArgsConstructor
public class AdoptionController {

    private final AdoptionInfoService adoptionInfoService;
    private final AdoptionRecordService adoptionRecordService;
    private final UserService userService;

    // --- Adoption Info Endpoints ---

    @GetMapping("/infos")
    public Result<IPage<AdoptionInfoDto>> getAdoptionInfosPage(@RequestParam(defaultValue = "1") int pageNum,
                                                             @RequestParam(defaultValue = "10") int pageSize) {
        IPage<AdoptionInfo> page = new Page<>(pageNum, pageSize);
        IPage<AdoptionInfoDto> resultPage = adoptionInfoService.getAdoptionInfoPage(page);
        return Result.success(resultPage);
    }

    @GetMapping("/infos/{id}")
    public Result<AdoptionInfoDto> getAdoptionInfoDetail(@PathVariable Long id) {
        AdoptionInfoDto dto = adoptionInfoService.getAdoptionInfoDetail(id);
        if (dto == null) {
            return Result.error(404, "领养信息未找到");
        }
        return Result.success(dto);
    }

    // --- Adoption Record Endpoints ---

    @PostMapping("/records")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Result<AdoptionRecord> createAdoptionRecord(@RequestBody AdoptionRecordCreationDto creationDto,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User currentUser = userService.findByUsername(userDetails.getUsername());
            AdoptionRecord newRecord = adoptionRecordService.createAdoptionRecord(creationDto, currentUser.getId());
            return Result.success(newRecord);
        } catch (IllegalStateException e) {
            return Result.error(400, e.getMessage());
        }
    }

    @GetMapping("/records/my")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Result<List<AdoptionRecord>> getMyAdoptionRecords(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.findByUsername(userDetails.getUsername());
        List<AdoptionRecord> records = adoptionRecordService.getAdoptionRecordsByUserId(currentUser.getId());
        return Result.success(records);
    }
} 