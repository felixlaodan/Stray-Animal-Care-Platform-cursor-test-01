package com.strayanimal.platform.rescue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("adoption_records")
public class AdoptionRecords {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("adoption_info_id")
    private Long adoptionInfoId;

    @TableField("pet_name")
    private String petName;

    @TableField("adopter_name")
    private String adopterName;

    @TableField("adopter_gender")
    private String adopterGender;

    private Integer age;

    @TableField("id_card")
    private String idCard;

    private String address;

    @TableField("contact_phone")
    private String contactPhone;

    @TableField("adoption_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adoptionTime;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
} 