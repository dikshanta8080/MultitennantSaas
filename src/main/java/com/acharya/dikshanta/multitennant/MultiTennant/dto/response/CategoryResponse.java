package com.acharya.dikshanta.multitennant.MultiTennant.dto.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CategoryResponse(
        UUID id,
        String name,
        String description
) {
}
