package com.strayanimal.platform.rescue.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class UploadRecordCreationDto {
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
    
    private List<String> imageUrls;
} 