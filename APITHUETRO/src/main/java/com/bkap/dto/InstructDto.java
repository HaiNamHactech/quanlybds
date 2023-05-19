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
public class InstructDto {

    private int id;
    private String types;
    private String description;
    private Date createAt;
    private Date updateAt;
    private int userId;
    private String title;
    private String videoLink;
    private String image;
    private String tagContent;
    private int rate;

}
