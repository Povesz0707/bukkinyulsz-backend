package com.example.bukkinyulszbackend.model.payload.imageUpload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileUploadResponse {
    private String encodedFilePath;

    public FileUploadResponse() {
    }

    public FileUploadResponse(String encodedFilePath) {
        this.encodedFilePath = encodedFilePath;
    }
}
