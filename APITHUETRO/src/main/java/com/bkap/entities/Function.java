package com.bkap.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "functions")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class Function {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String functionName;
    private String url;
    private String icon;
    private int parentId;
    private String systems;

    @JsonIgnore
    @OneToMany(fetch=FetchType.EAGER,mappedBy="function")
    private List<RoleFunction> roleFunctions;
}
