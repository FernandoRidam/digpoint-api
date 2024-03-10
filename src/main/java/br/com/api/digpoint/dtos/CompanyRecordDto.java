package br.com.api.digpoint.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CompanyRecordDto(
    @NotBlank
  String name,
    @NotBlank
    @Email
  String email,
    @NotBlank
    @Length(min=6)
  String password
) {}
