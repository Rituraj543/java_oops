package com.kiet.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class LibraryApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(LibraryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Library Book Management System Started");
		logger.info("Loading Book Catalogue...");
		logger.info("Book Management REST API is Ready");
	}
}
