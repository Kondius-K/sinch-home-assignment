package com.example.sinch.service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class PairsByKService {

  public void countPairs() {
    int k, n = 0;
    var inputList = new ArrayList<Integer>();

    try (Scanner scanner = new Scanner(System.in)) {
      System.out.println("Please enter k:");

      while (true) {
        try {
          k = scanner.nextInt();
          inputList.add(k);
          break;
        } catch (InputMismatchException ex) {
          System.out.println("Please enter a valid number");
          scanner.next();
        }
      }
      System.out.printf("k=%s%nPlease enter array of integers:%n", k);

      while (true) {
        if (scanner.hasNext("run")) {
          if (n < 2) {
            System.out.printf("n=%s, but should be greater then 1", n);
            scanner.next();
          } else {
            break;
          }
        } else {
          if (scanner.hasNextInt()) {
            inputList.add(scanner.nextInt());
            n++;
          } else {
            System.out.printf(
                "You entered: \"%s\". Please enter a valid number or \"run\" if you want to calculate result.%n",
                scanner.next());
          }
        }
      }
    }

    var result = countPairs(inputList);
    System.out.printf("Result:%s", result);
  }

  public int countPairs(List<Integer> inputList) {
    int result = 0;
    Integer k = inputList.get(0);

    var inputMap = IntStream
        .range(1, inputList.size())
        .boxed()
        .collect(Collectors.toMap(inputList::get, Function.identity()));

    for (var entry: inputMap.entrySet()) {
      var matchValOrder = inputMap.get(k - entry.getKey());
      if (matchValOrder != null && matchValOrder > entry.getValue()) {
        result++;
      }
    }
    return result;
  }
}
