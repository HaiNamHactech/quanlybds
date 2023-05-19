package com.bkap.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "room")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value= {"hibernateLazyInitializer","handler"})
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String slug;
    private String avatar;
    private String description;
    @CreatedBy
    private String createBy;
    @LastModifiedBy
    private String updateBy;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updateAt;
    private String consciousId;
    private String districtId;
    private String wardId;
    private float price;
    private float area;
    @Temporal(TemporalType.DATE)
    private Date dateSubmited;
    @Temporal(TemporalType.DATE)
    private Date dateExpiration;
    private String specificAddress;
    @Column(name = "maps")
    private String map;
    private int subjectId;
    private String videoLink;
    private int status;
    private String consciousName;
    private String districtName;
    private String wardName;
    private String listImgExtra;
    private String sortDescription;

    @ManyToOne
    @JoinColumn(name = "categoryId" , referencedColumnName="id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "packageId",referencedColumnName = "id")
    private ServicePackage servicePackage;

    @JsonIgnore
    @OneToMany(fetch=FetchType.LAZY,mappedBy="room")
    private List<Favourite> favourites;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

}
