package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FunctionDto {

    private int id;
    private String functionName;
    private String url;
    private String icon;
    private int parentId;
    private String systems;

}
