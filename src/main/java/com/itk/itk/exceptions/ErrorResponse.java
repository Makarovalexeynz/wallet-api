package com.itk.itk.exceptions;

import lombok.Data;
import java.time.Instant;

@Data
public class ErrorResponse {
    private Instant timestamp;
    private String path;
    private int status;
    private String error;
    private String code;
    private String message;


    public ErrorResponse(Instant timestamp, String path, int status,
                         String error, String code, String message
                         ) {
        this.timestamp = timestamp;
        this.path = path;
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }
}
