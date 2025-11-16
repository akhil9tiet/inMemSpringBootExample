package com.inmemDBexample.inmemCRUD.domain.dto;

public record ErrorResponse(
        int status,
        String message,
        String detail
) {
}
