package com.travanleo.oauth2client.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


public final class OauthClientUtils {

  private OauthClientUtils() {
    // private constructor
  }

  public static MultiValueMap<String, String> preparePostParametersForPasswordTypeGrant(String authHeader) {
    final MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
    String[] userNameAndPassword = extractBasicAuthenticationDetails(authHeader);

    postParameters.add("grant_type", "password");
    postParameters.add("username", userNameAndPassword[0]);
    postParameters.add("password", userNameAndPassword[1]);

    return postParameters;
  }

  public static MultiValueMap<String, String> preparePostParametersForRefreshTokenTypeGrant(String refreshToken) {
    final MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();

    postParameters.add("grant_type", "refresh_token");
    postParameters.add("refresh_token", refreshToken);

    return postParameters;
  }

  private static String[] extractBasicAuthenticationDetails(String authHeader) {
    String[] authParts = authHeader.split("\\s+");
    String authInfo = authParts[1];
    // Decode the data back to original string
    byte[] bytes = null;
    bytes = new Base64().decode(authInfo);
    String decodedAuth = new String(bytes);
    String[] userNameAndPassword = decodedAuth.split(":");
    return userNameAndPassword;
  }
}
