package com.example.bukkinyulszbackend.model.payload.imageUpload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileUploadRequest {
    private String baseType;
    private String name;
    private String folder;
    private String base64;
}
