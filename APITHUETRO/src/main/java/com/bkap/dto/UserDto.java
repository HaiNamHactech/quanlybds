package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {

    private int id;
    private String name;
    private String phone;
    private String email;
    private String pass;
    private String avatar;
    private float accountBalance;
}
