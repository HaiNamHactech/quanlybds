package com.bkap.utils;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private int statusCode;
    private String message;
    private int dataCount;
    private int totalData;
    private T data;

    public Response(String message, int statusCode, T data, int totalData) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
        this.totalData = totalData;
    }
}
