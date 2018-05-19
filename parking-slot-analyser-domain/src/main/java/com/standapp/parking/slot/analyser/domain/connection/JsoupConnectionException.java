package com.standapp.parking.slot.analyser.domain.connection;

public class JsoupConnectionException extends RuntimeException {

  public JsoupConnectionException(final String message, final Throwable throwable)  {
    super(message, throwable);
  }
}
