package br.com.api.digpoint.dtos;

import br.com.api.digpoint.models.Company;

public record LoginResponseDto(
  Company company,
  String token
) {}
