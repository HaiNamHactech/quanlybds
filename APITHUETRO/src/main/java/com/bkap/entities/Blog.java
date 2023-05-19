package com.bkap.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "blog")
@EntityListeners(AuditingEntityListener.class)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String slug;
    private String title;
    private String shortDescription;
    private String detailDescription;
    private String image;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private Date updateAt;
    @CreatedBy
    private String createBy;
    @LastModifiedBy
    private String updateBy;


    @ManyToOne
    @JoinColumn(name = "authorId",referencedColumnName = "id")
    private User user;
}
