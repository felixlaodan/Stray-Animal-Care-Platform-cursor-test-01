package com.strayanimal.platform.rescue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("upload_record_image")
public class UploadRecordImage {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("upload_record_id")
    private Long uploadRecordId;

    @TableField("image_url")
    private String imageUrl;

    @TableField("create_time")
    private Date createTime;
} 