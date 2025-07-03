package com.strayanimal.platform.rescue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.core.util.SecurityUtil;
import com.strayanimal.platform.rescue.entity.AdoptionRecords;
import com.strayanimal.platform.rescue.mapper.AdoptionRecordsMapper;
import com.strayanimal.platform.rescue.service.AdoptionRecordsService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionRecordsServiceImpl extends ServiceImpl<AdoptionRecordsMapper, AdoptionRecords> implements AdoptionRecordsService {

    @Resource
    private UserService userService;

    @Override
    public Page<AdoptionRecords> selectPages(String petName, Integer pageNum, Integer pageSize) {
        Page<AdoptionRecords> page = new Page<>(pageNum, pageSize);
        QueryWrapper<AdoptionRecords> queryWrapper = new QueryWrapper<>();
        if (petName != null && !petName.isEmpty()) {
            queryWrapper.like("pet_name", petName);
        }
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public AdoptionRecords selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int insertAdoptionRecords(AdoptionRecords adoptionRecords) {
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername != null) {
            User user = userService.getOne(new QueryWrapper<User>().eq("username", currentUsername));
            if (user != null) {
                adoptionRecords.setUserId(user.getId());
            }
        }
        return baseMapper.insert(adoptionRecords);
    }

    @Override
    public int updateAdoptionRecords(AdoptionRecords adoptionRecords) {
        return baseMapper.updateById(adoptionRecords);
    }

    @Override
    public int deleteById(Long id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return baseMapper.deleteBatchIds(ids);
    }
} 