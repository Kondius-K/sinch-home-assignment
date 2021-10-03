package com.example.sinch.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  @ResponseStatus(BAD_REQUEST)
  public ErrorDTO handleIllegalArgumentException(IllegalArgumentException ex) {
    return handleException(BAD_REQUEST, ex);
  }

  @ExceptionHandler
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorDTO handleRuntimeException(RuntimeException ex) {
    return handleException(INTERNAL_SERVER_ERROR, ex);
  }

  private ErrorDTO handleException(HttpStatus status, Exception ex) {
    log.error(ex.getMessage(), ex);
    return ErrorDTO.builder()
        .code(status.name())
        .message(ex.getMessage())
        .details(ExceptionUtils.getStackTrace(ex))
        .build();
  }

  @Getter
  @Builder
  public static class ErrorDTO {
    private final String code;
    private final String message;
    private final String details;
  }

}
