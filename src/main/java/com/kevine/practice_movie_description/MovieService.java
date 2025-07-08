package com.kevine.practice_movie_description;

// this marks the class as a Spring service & tells Spring to manage and inject it where needed
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    // list of movies in memory & acts like a simple database.... for now
    private final List<Movie> movies = new ArrayList<>();
    // reference to the GeminiClient for calling the AI
    private final GeminiClient geminiClient;

    // constructor injection / Spring will automatically provide the GeminiClient
    public MovieService(GeminiClient geminiClient) {
        this.geminiClient = geminiClient;
    }

    // adds a new movie / calls Gemini to get description & review / creates a new movie object and stores it
    public Movie addMovie(String title, String rating) {
        String description = geminiClient.generateDescription(title);
        String review = geminiClient.generateReview(title);

        Movie movie = new Movie(title, rating, description, review);
        movies.add(movie);
        return movie;
    }

    // returns entire list of movies / used for the GET /movies endpoint
    public List<Movie> getAllMovies() {
        return movies;
    }
}
