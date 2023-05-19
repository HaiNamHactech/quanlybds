package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleFunctionDto {

    private int id;
    private int allChildFunction;
    private int c;
    private int r;
    private int u;
    private int d;
    private int groupRoleId;
    private int functionId;
}
