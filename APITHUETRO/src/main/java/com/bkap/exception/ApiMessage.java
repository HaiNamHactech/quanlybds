package com.bkap.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiMessage {

    private String message;
    private int status;
    private LocalDateTime timeStamp;

    public ApiMessage(int status, String message, LocalDateTime timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }
}
