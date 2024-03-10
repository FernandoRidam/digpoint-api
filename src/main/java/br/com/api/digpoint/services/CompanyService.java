package br.com.api.digpoint.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.api.digpoint.dtos.CompanyRecordDto;
import br.com.api.digpoint.exceptions.ExceptionNotFound;
import br.com.api.digpoint.models.Company;
import br.com.api.digpoint.repositorys.CompanyRepository;

@Service
public class CompanyService {
  @Autowired
  CompanyRepository companyRepository;

  BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

  public Company saveCompany(CompanyRecordDto companyRecordDto) {
    var company = new Company();

    BeanUtils.copyProperties(companyRecordDto, company);

    String pass = bcrypt.encode(company.getPassword());

    company.setPassword(pass);

    return companyRepository.save(company);
  }

  public List<Company> getAllCompanies() {
    return companyRepository.findAll();
  }

  public Optional<Company> getCompany(UUID id) {
    Optional<Company> company = companyRepository.findById(id);

    if(company.isEmpty()) {
      throw new ExceptionNotFound("Company not found");
    }

    return company;
  }

  public Object updateCompany(UUID id, CompanyRecordDto companyRecordDto) {
    Optional<Company> company = companyRepository.findById(id);

    if(company.isEmpty()) {
      throw new ExceptionNotFound("Company not found");
    }

    var companyData = company.get();

    BeanUtils.copyProperties(companyRecordDto, companyData);

    return companyRepository.save(companyData);
  }

  public String deleteCompany(UUID id) {
    Optional<Company> company = companyRepository.findById(id);

    if(company.isEmpty()) {
      throw new ExceptionNotFound("Company not found");
    }

    companyRepository.delete(company.get());

    return "Company deleted successfully";
  }
}
