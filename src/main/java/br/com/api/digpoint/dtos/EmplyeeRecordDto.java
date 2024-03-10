package br.com.api.digpoint.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmplyeeRecordDto(
    @NotBlank
  String name
) {}
