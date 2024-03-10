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

import br.com.api.digpoint.dtos.EmplyeeRecordDto;
import br.com.api.digpoint.models.Emplyee;
import br.com.api.digpoint.services.EmplyeeService;
import jakarta.validation.Valid;

@RestController
public class EmplyeeController {
  @Autowired
  EmplyeeService emplyeeService;

  @PostMapping("/company/{companyId}/emplyee")
  public ResponseEntity<Emplyee> saveEmplyee(@PathVariable(value="companyId") UUID companyId, @RequestBody @Valid EmplyeeRecordDto emplyeeRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(emplyeeService.saveEmplyee(companyId, emplyeeRecordDto));
  }

  @GetMapping("/company/{companyId}/emplyee")
  public ResponseEntity<List<Emplyee>> getAllCompanies(@PathVariable(value="companyId") UUID companyId) {
    return ResponseEntity.status(HttpStatus.OK).body(emplyeeService.getAllCompanies(companyId));
  }

  @GetMapping("/company/{companyId}/emplyee/{id}")
  public ResponseEntity<Object> getEmplyee(@PathVariable(value="companyId") UUID companyId, @PathVariable(value="id") UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(emplyeeService.getEmplyee(companyId, id));
  }

  @GetMapping("/company/{companyId}/emplyee/code/{code}")
  public ResponseEntity<Object> verifyCode(@PathVariable(value="companyId") UUID companyId, @PathVariable(value="code") String code) {
    return ResponseEntity.status(HttpStatus.OK).body(emplyeeService.getEmplyeeByCode(companyId, code));
  }

  @PutMapping("/company/{companyId}/emplyee/{id}")
  public ResponseEntity<Object> updateEmplyee(@PathVariable(value="companyId") UUID companyId, @PathVariable(value="id") UUID id, @RequestBody @Valid EmplyeeRecordDto emplyeeRecordDto) {
    return ResponseEntity.status(HttpStatus.OK).body(emplyeeService.updateEmplyee(companyId, id, emplyeeRecordDto));
  }

  @DeleteMapping("/company/{companyId}/emplyee/{id}")
  public ResponseEntity<Object> deleteEmplyee(@PathVariable(value="companyId") UUID companyId, @PathVariable(value="id") UUID id) {
    return ResponseEntity.status(HttpStatus.OK).body(emplyeeService.deleteEmplyee(companyId, id));
  }
}
