package com.bkap.exception;

public class DuplicateRecordException extends  RuntimeException {
    public DuplicateRecordException(String message) {
        super(message);
    }
}
