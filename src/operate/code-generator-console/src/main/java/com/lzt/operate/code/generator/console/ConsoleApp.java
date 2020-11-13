package com.lzt.operate.code.generator.console;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

	public static void main(String[] args) {
		System.out.println("start console!");
		SpringApplication.run(ConsoleApp.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("begin run");
	}

}
