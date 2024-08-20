package com.sysaid.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DeleteResponseTaskDto {
    private String activity;
    private Float accessibility;
    private String type;
    private Integer participants;
    private Float price;
    private String link;
    private String key;
    private String username;
}
