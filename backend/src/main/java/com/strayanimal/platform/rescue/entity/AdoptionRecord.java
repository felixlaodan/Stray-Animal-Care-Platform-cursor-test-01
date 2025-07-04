package com.strayanimal.platform.rescue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("adoption_record")
public class AdoptionRecord {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("adoption_info_id")
    private Long adoptionInfoId;

    @TableField("adopter_user_id")
    private Long adopterUserId;

    @TableField("adopter_name")
    private String adopterName;

    @TableField("adopter_phone")
    private String adopterPhone;

    @TableField("adopter_id_card")
    private String adopterIdCard;

    @TableField("adoption_reason")
    private String adoptionReason;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;
} 