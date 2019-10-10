package com.VetSystem.RestAPI.config;

public class Response {
    
    public enum ResponseCode {
    Ok,
    NotFound,
    Error
    }
    
    private ResponseCode responseCode;
    private String userMessage;
    private String errorMessage;

    public Response() {
    }

    
    
    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    
}
