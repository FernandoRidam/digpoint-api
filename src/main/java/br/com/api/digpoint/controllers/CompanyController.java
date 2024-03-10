package br.com.api.digpoint.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import br.com.api.digpoint.dtos.CompanyRecordDto;
import br.com.api.digpoint.models.Company;
import br.com.api.digpoint.services.CompanyService;
import jakarta.validation.Valid;

@RestController
public class CompanyController {
  @Autowired
  CompanyService companyService;

  @PostMapping("/company")
  public ResponseEntity<Company> saveCompany(@RequestBody @Valid CompanyRecordDto companyRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(companyRecordDto));
  }

  @GetMapping("/company")
  public ResponseEntity<List<Company>> getAllCompanies() {
    return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
  }

  @GetMapping("/company/{id}")
  public ResponseEntity<Object> getCompany(@PathVariable(value="id") UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompany(id));
  }

  @PutMapping("/company/{id}")
  public ResponseEntity<Object> updateCompany(@PathVariable(value="id") UUID id, @RequestBody @Valid CompanyRecordDto companyRecordDto) {
    return ResponseEntity.status(HttpStatus.OK).body(companyService.updateCompany(id, companyRecordDto));
  }

  @DeleteMapping("/company/{id}")
  public ResponseEntity<Object> deleteCompany(@PathVariable(value="id") UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(companyService.deleteCompany(id));
  }
}
