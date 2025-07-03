package com.strayanimal.platform.rescue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("upload_records")
public class UploadRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    private String reporter;

    @TableField("reporter_phone")
    private String reporterPhone;

    private String name;
    private String species;
    private String gender;

    @TableField("health_status")
    private String healthStatus;

    @TableField("sterilization_status")
    private String sterilizationStatus;

    @TableField("discovery_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date discoveryTime;

    @TableField("discovery_place")
    private String discoveryPlace;

    private String description;

    @TableField("image_url")
    private String imageUrl;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
} 