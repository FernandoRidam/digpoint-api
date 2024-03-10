package br.com.api.digpoint.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.digpoint.dtos.AuthRecordDto;
import br.com.api.digpoint.dtos.LoginResponseDto;
import br.com.api.digpoint.models.Auth;
import br.com.api.digpoint.models.Company;
import br.com.api.digpoint.repositorys.CompanyRepository;
import jakarta.validation.Valid;

@Service
public class AuthService implements UserDetailsService {
  @Autowired
  private ApplicationContext context;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private TokenService tokenService;

  private AuthenticationManager authenticationManager;

  BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return companyRepository.findByEmail(email);
  }

  public boolean logout(@RequestBody @Valid AuthRecordDto authRecordDto) {
    var auth = new Auth();

    BeanUtils.copyProperties(authRecordDto, auth);

    UserDetails company = companyRepository.findByEmail(auth.getEmail());

    return bcrypt.matches(auth.getPassword(), company.getPassword());
  }

  public ResponseEntity<Object> login(@RequestBody @Valid AuthRecordDto data) {
    authenticationManager = context.getBean(AuthenticationManager.class);

    var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    var token = tokenService.generateToken((Company) auth.getPrincipal());
    return ResponseEntity.ok(new LoginResponseDto((Company) auth.getPrincipal(), token));
  }
}
