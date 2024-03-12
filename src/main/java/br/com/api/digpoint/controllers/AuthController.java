package br.com.api.digpoint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.digpoint.dtos.AuthRecordDto;
import br.com.api.digpoint.dtos.CompanyRecordDto;
import br.com.api.digpoint.models.Company;
import br.com.api.digpoint.services.AuthService;
import br.com.api.digpoint.services.CompanyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {

  @Autowired
  AuthService authService;
  @Autowired
  CompanyService companyService;

  @PostMapping("/register")
  public ResponseEntity<Company> saveCompany(@RequestBody @Valid CompanyRecordDto companyRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(companyRecordDto));
  }

  @PostMapping("/login")
  public ResponseEntity<Object> login(@RequestBody @Valid AuthRecordDto authDto) {
    return authService.login(authDto);
  }

  @PostMapping("/logout")
  public ResponseEntity<Boolean> logout(@RequestBody @Valid AuthRecordDto authDto) {
    var success = authService.logout(authDto);

    return success ? ResponseEntity.status(HttpStatus.OK).body(success) : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(success);
  }
}
