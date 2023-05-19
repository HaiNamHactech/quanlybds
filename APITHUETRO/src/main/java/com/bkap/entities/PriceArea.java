package com.bkap.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "priceArea")
public class PriceArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float priceForm;
    private float priceTo;
    private float areaFrom;
    private float areaTo;
    private String types;
    private String title;
}
