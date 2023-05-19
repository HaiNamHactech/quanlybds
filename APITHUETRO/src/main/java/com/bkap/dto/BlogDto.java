package com.bkap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BlogDto {

    private int id;
    private String slug;
    private String title;
    private String shortDescription;
    private String detailDescription;
    private String image;
    private Date createAt;
    private Date updateAt;
    private String createBy;
    private String updateBy;
    private int authorId;


}
