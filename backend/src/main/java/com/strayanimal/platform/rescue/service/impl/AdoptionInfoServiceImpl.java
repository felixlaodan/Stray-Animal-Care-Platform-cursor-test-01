package com.strayanimal.platform.rescue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.core.util.SecurityUtil;
import com.strayanimal.platform.rescue.entity.AdoptionInfo;
import com.strayanimal.platform.rescue.mapper.AdoptionInfoMapper;
import com.strayanimal.platform.rescue.service.AdoptionInfoService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionInfoServiceImpl extends ServiceImpl<AdoptionInfoMapper, AdoptionInfo> implements AdoptionInfoService {

    @Resource
    private UserService userService;

    @Override
    public Page<AdoptionInfo> selectPages(String name, Integer pageNum, Integer pageSize) {
        Page<AdoptionInfo> page = new Page<>(pageNum, pageSize);
        QueryWrapper<AdoptionInfo> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public AdoptionInfo selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int insertAdoptionInfo(AdoptionInfo adoptionInfo) {
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername != null) {
            User user = userService.getOne(new QueryWrapper<User>().eq("username", currentUsername));
            if (user != null) {
                adoptionInfo.setUserId(user.getId());
            }
        }
        return baseMapper.insert(adoptionInfo);
    }

    @Override
    public int updateAdoptionInfo(AdoptionInfo adoptionInfo) {
        return baseMapper.updateById(adoptionInfo);
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