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
@Table(name = "userRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "groupRoleId",referencedColumnName = "id")
    private GroupRole groupRole;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;



}
