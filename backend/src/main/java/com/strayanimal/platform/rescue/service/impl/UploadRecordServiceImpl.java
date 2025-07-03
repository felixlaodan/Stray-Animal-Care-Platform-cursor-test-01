package com.strayanimal.platform.rescue.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strayanimal.platform.core.util.SecurityUtil;
import com.strayanimal.platform.rescue.entity.UploadRecord;
import com.strayanimal.platform.rescue.mapper.UploadRecordMapper;
import com.strayanimal.platform.rescue.service.UploadRecordService;
import com.strayanimal.platform.user.entity.User;
import com.strayanimal.platform.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UploadRecordServiceImpl extends ServiceImpl<UploadRecordMapper, UploadRecord> implements UploadRecordService {

    @Resource
    private UserService userService;

    @Override
    public Page<UploadRecord> selectPages(String name, Integer pageNum, Integer pageSize) {
        Page<UploadRecord> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UploadRecord> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            queryWrapper.like("name", name);
        }
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public UploadRecord selectById(Long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int insertUploadRecord(UploadRecord uploadRecord) {
        String currentUsername = SecurityUtil.getCurrentUsername();
        if (currentUsername != null) {
            User user = userService.getOne(new QueryWrapper<User>().eq("username", currentUsername));
            if (user != null) {
                uploadRecord.setUserId(user.getId());
            }
        }
        return baseMapper.insert(uploadRecord);
    }

    @Override
    public int updateUploadRecord(UploadRecord uploadRecord) {
        return baseMapper.updateById(uploadRecord);
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