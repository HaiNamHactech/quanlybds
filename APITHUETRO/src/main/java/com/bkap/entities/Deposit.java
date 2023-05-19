package com.bkap.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "deposit")
@EntityListeners(AuditingEntityListener.class)
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String methodDeposit;
    private Float amountDeposit;
    private String giveExtra;
    private Float amountReality;
    private int status;
    private String description;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;
}
