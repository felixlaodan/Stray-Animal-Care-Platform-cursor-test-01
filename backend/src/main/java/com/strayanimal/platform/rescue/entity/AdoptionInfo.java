package com.strayanimal.platform.rescue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("adoption_infos")
public class AdoptionInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("upload_record_id")
    private Long uploadRecordId;

    private String name;
    private String species;
    private String gender;

    @TableField("health_status")
    private String healthStatus;

    @TableField("sterilization_status")
    private String sterilizationStatus;

    @TableField("base_name")
    private String baseName;

    @TableField("discovery_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date discoveryTime;

    @TableField("discovery_place")
    private String discoveryPlace;

    private String description;

    @TableField("image_url")
    private String imageUrl;

    @TableField("adoption_status")
    private String adoptionStatus;

    @TableField("adopted_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adoptedTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
} 