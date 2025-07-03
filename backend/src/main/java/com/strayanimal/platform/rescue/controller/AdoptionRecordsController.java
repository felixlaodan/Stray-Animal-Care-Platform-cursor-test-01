package com.strayanimal.platform.rescue.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strayanimal.platform.core.common.Result;
import com.strayanimal.platform.rescue.entity.AdoptionRecords;
import com.strayanimal.platform.rescue.service.AdoptionRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rescue/adoption-records")
public class AdoptionRecordsController {

    @Autowired
    private AdoptionRecordsService adoptionRecordsService;

    @GetMapping
    public Result<Page<AdoptionRecords>> selectPages(@RequestParam(defaultValue = "") String petName,
                                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<AdoptionRecords> page = adoptionRecordsService.selectPages(petName, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<AdoptionRecords> selectById(@PathVariable Long id) {
        AdoptionRecords adoptionRecords = adoptionRecordsService.selectById(id);
        return Result.success(adoptionRecords);
    }

    @PostMapping
    public Result<Void> insertAdoptionRecords(@RequestBody AdoptionRecords adoptionRecords) {
        adoptionRecordsService.insertAdoptionRecords(adoptionRecords);
        return Result.success();
    }

    @PutMapping
    public Result<Void> updateAdoptionRecords(@RequestBody AdoptionRecords adoptionRecords) {
        adoptionRecordsService.updateAdoptionRecords(adoptionRecords);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<Void> deleteById(@PathVariable Long id) {
        adoptionRecordsService.deleteById(id);
        return Result.success();
    }

    @DeleteMapping("/batch")
    public Result<Void> deleteBatch(@RequestBody List<Long> ids) {
        adoptionRecordsService.deleteBatch(ids);
        return Result.success();
    }
} 