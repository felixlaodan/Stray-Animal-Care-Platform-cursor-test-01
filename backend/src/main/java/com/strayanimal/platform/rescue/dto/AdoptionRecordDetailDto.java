package com.strayanimal.platform.rescue.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AdoptionRecordDetailDto {

    // --- 领养记录自身的信息 ---
    private Long id;
    private String adopterName;
    private String adopterPhone;
    private String adopterIdCard;
    private String adoptionReason;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    // --- 关联的动物信息 ---
    private AdoptionInfoDto animalInfo;

} 