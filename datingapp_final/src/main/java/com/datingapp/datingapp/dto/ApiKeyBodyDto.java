package com.datingapp.datingapp.dto;

public class ApiKeyBodyDto {

    private String apiKey;

    public ApiKeyBodyDto(final String apiKey) {
        this.apiKey = apiKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }
}
