package com.strayanimal.platform.rescue.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;
import com.strayanimal.platform.rescue.service.AdoptionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rescue/adoption-info")
public class AdoptionInfoController {

    @Autowired
    private AdoptionInfoService adoptionInfoService;

    @GetMapping
    public Result<Page<AdoptionInfo>> selectPages(@RequestParam(defaultValue = "") String name,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<AdoptionInfo> page = adoptionInfoService.selectPages(name, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<AdoptionInfo> selectById(@PathVariable Long id) {
        AdoptionInfo adoptionInfo = adoptionInfoService.selectById(id);
        return Result.success(adoptionInfo);
    }

    @PostMapping
    public Result<Void> insertAdoptionInfo(@RequestBody AdoptionInfo adoptionInfo) {
        adoptionInfoService.insertAdoptionInfo(adoptionInfo);
        return Result.success();
    }

    @PutMapping
    public Result<Void> updateAdoptionInfo(@RequestBody AdoptionInfo adoptionInfo) {
        adoptionInfoService.updateAdoptionInfo(adoptionInfo);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        adoptionInfoService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<Long> ids) {
        adoptionInfoService.deleteBatch(ids);
        return Result.success();
    }
} 