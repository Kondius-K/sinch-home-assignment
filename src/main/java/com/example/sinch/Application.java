package com.example.sinch;

import com.example.sinch.service.PairsByKService;
import com.example.sinch.service.PolishNotationService;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		terminalInput();
	}

	private static void terminalInput() {
		try (Scanner scanner = new Scanner(System.in)) {
			if (scanner.hasNext("go")) {
				System.out.println("Please enter \"1\" to run Task 1, or \"2\" to run Task 2:");
				scanner.next();
				var taskNumber = scanner.nextInt();
				if (taskNumber == 1) {
					new PairsByKService().countPairs();
				} else if (taskNumber == 2) {
					new PolishNotationService().evaluateExpressions();
				}
			}
		}
	}
}
