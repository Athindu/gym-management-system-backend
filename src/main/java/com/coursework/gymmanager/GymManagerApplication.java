package com.coursework.gymmanager;

import com.coursework.gymmanager.view.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GymManagerApplication implements CommandLineRunner {

	@Autowired
	private Console console;

	public static void main(String[] args) {
		SpringApplication.run(GymManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		console.welcome();
	}
}