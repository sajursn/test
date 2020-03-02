package com.travanleo.oauth2client;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Class to load the client details from the application.yml
 * 
 *
 */
@Configuration
@ConfigurationProperties(prefix = "oauth2.clients")
public class OauthClientDetailsConfig {

  List<OauthClientDetails> clientDetails;

  Map<String, OauthClientDetails> clientDetailsMap;

  @PostConstruct
  public void postConstruct() {
    if (clientDetails != null) {
      clientDetailsMap = clientDetails.stream().collect(Collectors.toMap(OauthClientDetails::getClientId, c -> c));
    }
  }

  public List<OauthClientDetails> getClientDetails() {
    return clientDetails;
  }

  public void setClientDetails(List<OauthClientDetails> clientDetails) {
    this.clientDetails = clientDetails;
  }

  public Map<String, OauthClientDetails> getClientDetailsMap() {
    return clientDetailsMap;
  }

  public static class OauthClientDetails {
    String clientId;
    String clientSecret;

    public String getClientId() {
      return clientId;
    }

    public void setClientId(String clientId) {
      this.clientId = clientId;
    }

    public String getClientSecret() {
      return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
    }

  }

}
