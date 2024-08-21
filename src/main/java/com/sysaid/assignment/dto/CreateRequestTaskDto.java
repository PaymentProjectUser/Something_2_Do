package com.sysaid.assignment.dto;

import jakarta.validation.constraints.NotNull;
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
public class CreateRequestTaskDto {
    private String activity;
    private Float accessibility;
    private String type;
    private Integer participants;
    private Float price;
    private String link;
    @NotNull
    private String key;
    private String username;
}
