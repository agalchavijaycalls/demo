package com.example.demo.exception;

public class InvalidAttributeException extends ServiceException {

  private String key;
  private String errorCode;

  private static final long serialVersionUID = 1L;

  public InvalidAttributeException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidAttributeException(String message) {
    super(message);
  }

  public InvalidAttributeException(String key, String errorCode) {
    super();
    this.key = key;
    this.errorCode = errorCode;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public String getMessage() {
    return key + " - " + errorCode;
  }
}
