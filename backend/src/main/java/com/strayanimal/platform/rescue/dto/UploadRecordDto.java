package com.strayanimal.platform.rescue.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UploadRecordDto {

    private Long id;
    private Long userId;
    private String reporter;
    private String reporterPhone;
    private String name;
    private String species;
    private String gender;
    private String healthStatus;
    private String sterilizationStatus;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date discoveryTime;
    
    private String discoveryPlace;
    private String description;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    private List<String> imageUrls;
} 