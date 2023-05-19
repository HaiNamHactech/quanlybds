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
@Table(name = "balances")
@EntityListeners(AuditingEntityListener.class)
public class Balances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String typeBalances;
    private Float surplus;
    private Float fee;
    private Float remaining;
    private int status;
    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date createDate;
    private String methodBalances;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "categoryId",referencedColumnName = "id")
    private Category category;
}
