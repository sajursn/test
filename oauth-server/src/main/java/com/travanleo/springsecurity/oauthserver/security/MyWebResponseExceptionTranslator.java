package com.travanleo.springsecurity.oauthserver.security;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import com.travanleo.springsecurity.oauthserver.exception.ErrorDetail;

public class MyWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
  public static final String APPLICATION_ERROR_JSON = "application/error+json";

  @Override

  public ResponseEntity translate(Exception exception) throws Exception {
    ErrorDetail problem = new ErrorDetail("Authentication Error", "User Credentials seem to be Incorrect");
    problem.setStatus(HttpStatus.UNAUTHORIZED.value());

    return new ResponseEntity<>(problem, updateContentType(), HttpStatus.UNAUTHORIZED);
  }

  private HttpHeaders updateContentType() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Content-Type", APPLICATION_ERROR_JSON);
    return httpHeaders;
  }

}
