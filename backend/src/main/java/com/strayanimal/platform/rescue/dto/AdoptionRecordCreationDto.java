package com.strayanimal.platform.rescue.dto;

import lombok.Data;

@Data
public class AdoptionRecordCreationDto {
    
    private Long adoptionInfoId;
    private String adopterName;
    private String adopterPhone;
    private String adopterIdCard;
    private String adoptionReason;

} 