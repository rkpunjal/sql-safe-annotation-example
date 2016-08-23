package com.rk.example.sqlsafe.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WebResponse {

    private boolean success = true;
    private String messageCode = "";
    private String message = "";
    private Object data = null;

    public WebResponse(boolean success) {
        this.success = success;
    }


    public WebResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
