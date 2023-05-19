package com.bkap.filter;

import lombok.*;

import javax.validation.constraints.Pattern;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortType {
    String field;
    @Pattern(regexp = "asc|desc")
    String type;
}
