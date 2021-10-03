package com.example.sinch.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.sinch.model.PairsRequest;
import com.example.sinch.model.PolishRequest;
import com.example.sinch.service.PairsByKService;
import com.example.sinch.service.PolishNotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    produces = APPLICATION_JSON_VALUE,
    consumes = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class Controller {
  private final PolishNotationService polishNotationService;
  private final PairsByKService pairsByKService;

  @GetMapping(value = "/polish")
  public ResponseEntity<String[]> polishExpression(@RequestBody PolishRequest expressions) {
    var expList = expressions.getExpressions();
    if (expList.isEmpty()) {
      throw new IllegalArgumentException("Expression list is empty");
    }
    return ResponseEntity.ok(polishNotationService.evaluateExpressions(expList));
  }

  @GetMapping(value = "/pairs")
  public ResponseEntity<Integer> pairsExpression(@RequestBody PairsRequest expressions) {
    var expList = expressions.getExpressions();
    if (expList.isEmpty() || expList.size() < 3) {
      throw new IllegalArgumentException("Expression list should have more then 2 int values");
    }
    return ResponseEntity.ok(pairsByKService.countPairs(expList));
  }
}
