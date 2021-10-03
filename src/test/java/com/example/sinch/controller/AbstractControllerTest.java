package com.example.sinch.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;
import org.mockito.MockitoSession;

public class AbstractControllerTest {

  protected static MockitoSession mockitoSession;

  @BeforeAll
  static void beforeAll() {
    mockitoSession = Mockito.mockitoSession().startMocking();
  }

  @AfterAll
  static void afterAll() {
    mockitoSession.finishMocking();
  }
}
