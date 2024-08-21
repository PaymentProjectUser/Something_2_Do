package com.sysaid.assignment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class AddRequestTaskToUserWishListDto {
    private String username;

    @JsonProperty("existTaskDto")
    private ExistTaskDto existTaskDto;
}
