package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PriceAreaDto {

    private int id;
    private float priceForm;
    private float priceTo;
    private float areaFrom;
    private float areaTo;
    private String types;
    private  String title;
}
