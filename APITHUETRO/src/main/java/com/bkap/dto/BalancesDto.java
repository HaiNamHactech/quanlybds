package com.bkap.dto;

import com.bkap.entities.Category;
import com.bkap.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BalancesDto {
    private int id;
    private String typeBalances;
    private Float surplus;
    private Float fee;
    private Float remaining;
    private int status;
    private Date createDate;
    private String methodBalances;
    private int categoryId;
    private String categoryName;
    private int userId;
    private int roomId;
    private String roomName;
}
