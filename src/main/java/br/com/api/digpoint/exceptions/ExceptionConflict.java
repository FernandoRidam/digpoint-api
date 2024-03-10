package br.com.api.digpoint.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExceptionConflict extends RuntimeException {
  public ExceptionConflict(String message) {
    super(message);
  }
}
