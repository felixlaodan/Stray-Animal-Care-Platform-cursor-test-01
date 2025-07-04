package com.strayanimal.platform.rescue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.strayanimal.platform.rescue.entity.enums.AdoptionStatus;
import lombok.Data;
import java.util.Date;

@Data
@TableName("adoption_info")
public class AdoptionInfo {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("upload_record_id")
    private Long uploadRecordId;

    private String name;
    private String species;
    private String gender;

    @TableField("health_status")
    private String healthStatus;

    @TableField("sterilization_status")
    private String sterilizationStatus;

    private String description;

    @TableField("adoption_status")
    private AdoptionStatus adoptionStatus;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
} 