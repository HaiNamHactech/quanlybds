package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DepositDto {

    private int id;
    private String methodDeposit;
    private Float amountDeposit;
    private String giveExtra;
    private Float amountReality;
    private int status;
    private String description;
    private Date createAt;
    private int userId;
}
