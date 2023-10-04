package com.bilitech.yilimusic.exception;

public class RefreshTokenException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RefreshTokenException(String message) {
    super(String.format(message));
  }
}
