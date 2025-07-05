package com.strayanimal.platform.rescue.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class UpdateUploadRecordDto {
    // 这个ID将从URL路径中获取，而不是在请求体中
    // private Long id;

    private String reporter;
    private String reporterPhone;
    private String name;
    private String species;
    private String gender;
    private String healthStatus;
    private String sterilizationStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date discoveryTime;
    
    private String discoveryPlace;
    private String description;
    
    // 包含图片URL的完整列表，用于"先删后增"策略
    private List<String> imageUrls;
} 