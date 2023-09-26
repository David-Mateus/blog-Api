package com.example.blog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BlogPostDto(@NotBlank String titlePost, @NotBlank String textPost) {
}
