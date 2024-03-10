package br.com.api.digpoint.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExceptionUnauthorized extends RuntimeException {
  public ExceptionUnauthorized(String message) {
    super(message);
  }
}
