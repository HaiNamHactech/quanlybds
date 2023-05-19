package com.bkap.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileResponse {
    private String fileName;
    private String fileUri;
    private String type;
    private long size;

    public FileResponse(String fileName, String fileUri, String type, long size) {
        this.fileName = fileName;
        this.fileUri = fileUri;
        this.type = type;
        this.size = size;
    }
}
