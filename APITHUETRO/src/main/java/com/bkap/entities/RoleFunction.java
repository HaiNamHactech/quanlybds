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
@Table(name = "roleFunction")
public class RoleFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int allChildFunction;
    private int c;
    private int r;
    private int u;
    private int d;

    @ManyToOne
    @JoinColumn(name = "groupRoleId",referencedColumnName = "id")
    private GroupRole groupRole;

    @ManyToOne
    @JoinColumn(name = "functionId",referencedColumnName = "id")
    private Function function;
}
