package com.example.demo;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class VirtualLibraryApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = "";
		ConfigurableApplicationContext ctx = SpringApplication.run(VirtualLibraryApplication.class, args);
		System.out.println("To terminate process, type 'close'");
		s = sc.nextLine();
		if(s.equals("close")) {
			ctx.close();
		}
		sc.close();
	}
}
