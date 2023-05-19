package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryDto {

    private int id;
    private String name;
    private String slug;
    private String title;
    private String description;
    private int status;
    private int parentId;
    private int totalRoom;
    private String type;
    private int showMenu;
    private int showInAdmin;
}
