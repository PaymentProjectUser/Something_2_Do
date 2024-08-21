package com.sysaid.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestTaskDto {
    private String activity;
    private Float accessibility;
    private String type;
    private Integer participants;
    private Float price;
    private String link;
    private String key;
}
