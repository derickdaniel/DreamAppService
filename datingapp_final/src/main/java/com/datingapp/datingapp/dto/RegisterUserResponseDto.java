package com.datingapp.datingapp.dto;

public class RegisterUserResponseDto {

    private String requestId;

    public RegisterUserResponseDto(final String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(final String requestId) {
        this.requestId = requestId;
    }
}
