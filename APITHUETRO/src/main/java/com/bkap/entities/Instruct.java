package com.bkap.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "instruct")
@EntityListeners(AuditingEntityListener.class)
public class Instruct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String types;
    private String description;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    private Date updateAt;
    private String title;
    private String videoLink;
    private String image;
    private String tagContent;
    private int rate;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
}
