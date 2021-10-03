package com.example.sinch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class PairsByKServiceTest {

  private final PairsByKService service = new PairsByKService();

  @Test
  public void countPairs() {
    var input = Arrays.asList(
        6,
        2,
        1,
        4,
        5,
        3);

    var actual = service.countPairs(input);
    assertEquals(actual, 2);
  }

}