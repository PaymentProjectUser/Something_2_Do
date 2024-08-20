package com.sysaid.assignment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddRequestTaskToUserWishListDto {
    private String username;

    private ExistTaskDto existTaskDto;
}
