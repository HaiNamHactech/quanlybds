package com.bkap.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class User {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String email;
    private String pass;
    private String avatar;
    private float accountBalance;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy="user")
    private List<Favourite> favourites;

    @JsonIgnore
    @OneToMany(fetch=FetchType.EAGER,mappedBy="user")
    private List<UserRole> userRoles;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy="user")
    private List<Room> rooms;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Blog> blogs;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Instruct> instructs;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Contact>  contacts;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Balances>  balances;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Deposit>  deposits;

    @Transient
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (UserRole ur : userRoles){
            authorities.add(new SimpleGrantedAuthority(ur.getGroupRole().getRoleName()));
        }
        return authorities;
    }
}
