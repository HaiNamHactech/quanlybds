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
@Table(name = "groupRole")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class GroupRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "groupRole",fetch = FetchType.LAZY)
    private List<RoleFunction> roleFunctions;

    @JsonIgnore
    @OneToMany(mappedBy = "groupRole",fetch = FetchType.LAZY)
    private List<UserRole> userRoles;
}
