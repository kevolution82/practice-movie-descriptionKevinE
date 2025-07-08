package com.kevine.practice_movie_description;

// tells Springboot this is the main application class
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// marks this as the entry point for Springboot
public class PracticeMovieDescriptionApplication {
	// main method that starts the Springboot application
	public static void main(String[] args) {
		SpringApplication.run(PracticeMovieDescriptionApplication.class, args);
	}
}
