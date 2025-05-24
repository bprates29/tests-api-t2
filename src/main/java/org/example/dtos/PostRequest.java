package org.example.dtos;

public record PostRequest(
        String title,
        String body,
        int userId
) {
}
