package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RoomDto {
    private int id;
    private String title;
    private String slug;
    private String avatar;
    private String description;
    private Date createAt;
    private Date updateAt;
    private String createBy;
    private String updateBy;
    private String consciousId;
    private String consciousName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
    private float price;
    private float area;
    private Date dateSubmited;
    private Date dateExpiration;
    private String specificAddress;
    private String map;
    private int subjectId;
    private String videoLink;
    private int status;
    private int categoryId;
    private String categoryName;
    private int packageId;
    private int rateService;
    private String titleService;
    private String colorTilteService;
    private int userId;
    private String phoneNumber;
    private String avatarUser;
    private String listImgExtra;
    private String sortDescription;
}
