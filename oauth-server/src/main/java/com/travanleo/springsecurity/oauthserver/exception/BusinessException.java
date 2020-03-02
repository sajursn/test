package com.travanleo.springsecurity.oauthserver.exception;

/**
 * Custom Business Exception class.
 * 
 *
 */
public class BusinessException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private final String message;
  private final String summary;

  public BusinessException(String summary, String message) {
    super(message);
    this.message = message;
    this.summary = summary;
  }

  public BusinessException(String summary, String message, Throwable t) {
    super(message, t);
    this.message = message;
    this.summary = summary;
  }

  @Override
  public String getMessage() {
    return message;
  }

  public String getSummary() {
    return summary;
  }

}
