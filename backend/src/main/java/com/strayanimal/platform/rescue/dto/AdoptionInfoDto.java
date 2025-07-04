package com.strayanimal.platform.rescue.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.strayanimal.platform.rescue.entity.enums.AdoptionStatus;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class AdoptionInfoDto {

    private Long id;
    private Long uploadRecordId;
    private String name;
    private String species;
    private String gender;
    private String healthStatus;
    private String sterilizationStatus;
    private String description;
    private AdoptionStatus adoptionStatus;

    // 封面图，即上报记录的第一张图片
    private String coverImageUrl;
    
    // 所有的图片URL，用于详情展示
    private List<String> imageUrls;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
} 