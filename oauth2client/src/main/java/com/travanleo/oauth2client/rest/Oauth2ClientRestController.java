package com.travanleo.oauth2client.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.travanleo.oauth2client.OauthClientDetailsConfig;
import com.travanleo.oauth2client.util.OauthClientUtils;

import io.swagger.annotations.Api;

/**
 * Rest controller for the oauth client. This takes the request and delegates it to the 
 * Oauth server to fetch the access token.
 * 
 *
 */
@RestController
@RequestMapping("/oauthclient")
@Api(value = "Oauth Client Microservice", description = "Operations pertaining to fetching and refresing tokens from OauthServer")
public class Oauth2ClientRestController {

  @Value("${oauth2.server.uri}")
  String oauthServerBaseUrl;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  OauthClientDetailsConfig oauthClientDetailsConfig;

  @PostMapping("/accesstoken")
  public Object getAccessToken(@RequestBody @Valid TokenRequest tokenRequest, @RequestHeader("Authorization") String authHeader) {

    restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(tokenRequest.getClientId(),
      oauthClientDetailsConfig.getClientDetailsMap().get(tokenRequest.getClientId()).getClientSecret()));
    ResponseEntity<?> response = post(OauthClientUtils.preparePostParametersForPasswordTypeGrant(authHeader));
    if (hasError(response)) {
      return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
    return response.getBody();

  }

  @PostMapping("/refreshtoken")
  public Object getRefreshToken(@RequestBody @Valid TokenRequest tokenRequest) {

    restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(tokenRequest.getClientId(),
      oauthClientDetailsConfig.getClientDetailsMap().get(tokenRequest.getClientId()).getClientSecret()));
    ResponseEntity<?> response = post(OauthClientUtils.preparePostParametersForRefreshTokenTypeGrant(tokenRequest.getRefreshToken()));
    if (hasError(response)) {
      return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }
    return response.getBody();

  }

  private ResponseEntity<?> post(MultiValueMap<String, String> postParameters) {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    final HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(postParameters, headers);
    ResponseEntity<?> response;
    this.restTemplate.setErrorHandler(new MyErrorHandler());
    response = this.restTemplate.postForEntity(oauthServerBaseUrl + "/token", request, Object.class);

    return response;
  }

  private boolean hasError(ResponseEntity response) {
    return (response
      .getStatusCode()
      .series() == HttpStatus.Series.CLIENT_ERROR
      || response
        .getStatusCode()
        .series() == HttpStatus.Series.SERVER_ERROR);
  }
}
