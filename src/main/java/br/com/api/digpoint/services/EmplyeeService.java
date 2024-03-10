package br.com.api.digpoint.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.api.digpoint.dtos.EmplyeeRecordDto;
import br.com.api.digpoint.exceptions.ExceptionNotFound;
import br.com.api.digpoint.models.Company;
import br.com.api.digpoint.models.Emplyee;
import br.com.api.digpoint.repositorys.EmplyeeRepository;

import br.com.api.digpoint.utils.CodeUtil;

@Service
public class EmplyeeService {
  @Autowired
  EmplyeeRepository emplyeeRepository;

  @Autowired
  CompanyService companyService;

  @Autowired
  PointService pointService;

  CodeUtil codeUtil = new CodeUtil();

  public Emplyee saveEmplyee(UUID companyId, EmplyeeRecordDto emplyeeRecordDto) {
    Optional<Company> company = companyService.getCompany(companyId);

    var emplyee = new Emplyee();

    BeanUtils.copyProperties(emplyeeRecordDto, emplyee);

    Boolean codeIsUnavailable;

    do {
      emplyee.setCode(codeUtil.codeGeneration());

      Optional<Emplyee> _emplyee = emplyeeRepository.findByCode(emplyee.getCode());

      codeIsUnavailable = _emplyee.isPresent();
    } while (codeIsUnavailable);

    emplyee.setCompany(company.get());

    return emplyeeRepository.save(emplyee);
  }

  public List<Emplyee> getAllCompanies(UUID companyId) {
    return emplyeeRepository.findAll();
  }

  public Optional<Emplyee> getEmplyee(UUID companyId, UUID id) {
    Optional<Emplyee> emplyee = emplyeeRepository.findById(id);

    if(emplyee.isEmpty()) {
      throw new ExceptionNotFound("Emplyee not found");
    }

    return emplyee;
  }

  public Object getEmplyeeByCode(UUID companyId, String code) {
    Optional<Emplyee> emplyee = emplyeeRepository.findByCode(code);

    if(emplyee.isEmpty()) {
      throw new ExceptionNotFound("Emplyee not found");
    }

    pointService.savePoint(companyId, emplyee.get());

    return emplyee;
  }

  public Object updateEmplyee(UUID companyId, UUID id, EmplyeeRecordDto emplyeeRecordDto) {
    Optional<Emplyee> emplyee = emplyeeRepository.findById(id);

    if(emplyee.isEmpty()) {
      throw new ExceptionNotFound("Emplyee not found");
    }

    var emplyeeData = emplyee.get();

    BeanUtils.copyProperties(emplyeeRecordDto, emplyeeData);

    return emplyeeRepository.save(emplyeeData);
  }

  public String deleteEmplyee(UUID companyId, UUID id) {
    Optional<Emplyee> emplyee = emplyeeRepository.findById(id);

    if(emplyee.isEmpty()) {
      throw new ExceptionNotFound("Emplyee not found");
    }

    emplyeeRepository.delete(emplyee.get());

    return "Emplyee deleted successfully";
  }
}
