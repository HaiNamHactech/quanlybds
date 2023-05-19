package com.bkap.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "packageService")
public class ServicePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private int rate;
    private String advantage;
    private String fit;
    private int armorial;
    private String typeTextPackge;
    private String colorTitle;
    private String tagDemo;
    @Column(name = "priceDay")
    private float dayPrice;
    @Column(name = "priceWeek")
    private float weekPrice;
    @Column(name = "priceMonth")
    private float monthPrice;
    @Column(name = "priceUpNew")
    private float priceUpNew;
    @Column(name = "autoBrowsing")
    private int autoBrowsing;


    @JsonIgnore
    @OneToMany(fetch=FetchType.EAGER,mappedBy="servicePackage")
    private List<Room> rooms;



}
