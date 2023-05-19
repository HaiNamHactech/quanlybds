package com.bkap.filter;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseFilter {

    private int page;
    private int pageSize;
    Map<String,String> columnFilters;
    SortType[] sort;

}

