package com.example.sinch.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;


class PolishNotationServiceTest {

  private final PolishNotationService service = new PolishNotationService();

  @Test
  public void evaluateExpressionsTest() {
    var input = Arrays.asList(
        "",
        "+ + 0.5 1.5 * 4 10",
        "+ / 2 0 3",
        "- 2e3 - 700 + 7 * 2 15",
        "+ er 3",
        "- -1.5 * 3.1415 / -7 -2",
        "100500",
        "1 2",
        "+ 1");

    var expected = new String[] {
        "error",
        "42,00",
        "error",
        "1337,00",
        "error",
        "-12,50",
        "100500,00",
        "error",
        "error"};

    var actual = service.evaluateExpressions(input);
    assertArrayEquals(actual, expected);
  }

}