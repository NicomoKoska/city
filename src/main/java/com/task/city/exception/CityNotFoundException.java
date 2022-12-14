package com.task.city.exception;

public class CityNotFoundException extends RuntimeException {

  private static final String MSG = "City was not found by id = %s";

  public CityNotFoundException(Long id) {
    super(String.format(MSG, id));
  }
}
