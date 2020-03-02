package com.travanleo.oauth2client;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Oauth2clientApplication {

  private static final int CONNECTION_TIMEOUT_DEFAULT = 2000;

  private static final int READ_TIMEOUT_DEFAULT = 10000;

  private static final int MAX_CONN_TOTAL_DEFAULT = 200;

  private static final int MAX_CONN_PER_ROUTE_DEFAULT = 100;

  public static void main(String[] args) {
    SpringApplication.run(Oauth2clientApplication.class, args);
  }

  /**
   * Defining Rest template properties
   * @return
   */
  @Bean
  RestTemplate restTemplate() {

    final HttpClient httpClient = HttpClientBuilder.create().setMaxConnTotal(MAX_CONN_TOTAL_DEFAULT)
      .setMaxConnPerRoute(MAX_CONN_PER_ROUTE_DEFAULT).build();
    final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
    // set default timeouts
    factory.setReadTimeout(READ_TIMEOUT_DEFAULT);
    factory.setConnectTimeout(CONNECTION_TIMEOUT_DEFAULT);

    return new RestTemplate(factory);
  }

}
