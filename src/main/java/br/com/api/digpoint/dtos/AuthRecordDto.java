package br.com.api.digpoint.dtos;

import jakarta.validation.constraints.*;

public record AuthRecordDto(
    @NotBlank
    @Email
  String email,
    @NotBlank
  String password
) {}
