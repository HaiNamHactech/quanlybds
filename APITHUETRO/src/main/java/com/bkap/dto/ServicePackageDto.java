package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ServicePackageDto {

    private int id;
    private String title;
    private int rate;
    private String advantage;
    private String fit;
    private int armorial;
    private String colorTitle;
    private float dayPrice;
    private float weekPrice;
    private float monthPrice;
    private float priceUpNew;
    private int autoBrowsing;
    private String typeTextPackge;
    private int totalRoomByPackage;
    private String tagDemo;
}
