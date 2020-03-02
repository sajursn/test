package com.travanleo.oauth2client.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class MyErrorHandler implements ResponseErrorHandler {
  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    response.getBody();
  }

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return (response
      .getStatusCode()
      .series() == HttpStatus.Series.CLIENT_ERROR
      || response
        .getStatusCode()
        .series() == HttpStatus.Series.SERVER_ERROR);
  }
}
