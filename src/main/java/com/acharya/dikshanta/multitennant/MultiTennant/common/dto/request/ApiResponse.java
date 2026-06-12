package com.acharya.dikshanta.multitennant.MultiTennant.common.dto.request;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        String message,
        int statusCode,
        T data
) {
    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .statusCode(500)
                .build();
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .message(message)
                .statusCode(200)
                .data(data)
                .build();
    }
}
