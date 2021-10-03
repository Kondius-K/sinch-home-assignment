package com.example.sinch.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class PolishNotationService {

  public void evaluateExpressions() {
    var inputExps = new ArrayList<String>();
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Please enter Polish style notation expressions and \"run\" if you want to calculate result");

      while (true) {
        if (scanner.hasNext("run")) {
          break;
        } else if (scanner.hasNextLine()) {
          inputExps.add(scanner.nextLine());
        } else {
          scanner.nextLine();
        }
      }
    }

    var result = evaluateExpressions(inputExps);
    System.out.println("Result:");
    Arrays.stream(result).forEach(System.out::println);
  }

  public String[] evaluateExpressions(List<String> inputExps) {
    var result = new String[inputExps.size()];

    for (int i = 0; i < inputExps.size(); i++) {
      var expArray = inputExps.get(i).trim().split("\\s+");
      var stack = new LinkedList<Double>();
      var parseErrorFlag = false;

      for (var j = expArray.length - 1; j >= 0; j--) {
        try {
          if (expArray[j].matches("[*/+-]")) {
            double d1 = stack.pop();
            double d2 = stack.pop();
            double temp = evaluatePair(d2, d1, expArray[j].charAt(0));
            stack.push(temp);
          } else {
            stack.push(Double.valueOf(expArray[j]));
          }
        } catch (NoSuchElementException | UnsupportedOperationException | NumberFormatException ex) {
          parseErrorFlag = true;
          break;
        }
      }

      if (stack.size() != 1 || parseErrorFlag) {
        result[i] = "error";
      } else {
        result[i] = String.format("%.2f", stack.pop());
      }
    }
    return result;
  }

  private Double evaluatePair(double d1, double d2, char operator){
    switch (operator) {
      case '+':
        return d1 + d2;
      case '-':
        return d2 - d1;
      case '*':
        return d1 * d2;
      case '/':
        if (d1 == 0) {
          throw new UnsupportedOperationException();
        }
        return d2 / d1;
    }
    return 0.0;
  }
}
