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
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String slug;
    private String title;
    private String description;
    private int parentId;
    private int status;
    private String type;
    private int showMenu;
    private int showInAdmin;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy="category")
    private List<Room> rooms;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy="category")
    private List<Balances> balances;



}
