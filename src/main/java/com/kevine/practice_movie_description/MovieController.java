package com.kevine.practice_movie_description;

// takes care of incoming http requests for my API
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Spring will automatically convert return values to JSON
@RestController
@RequestMapping("/movies")
public class MovieController {
    // reference to the service layer that does all the work
    private final MovieService movieService;

    // constructor injection & Spring automatically provides the MovieService
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // handles the POST requests to /movies & calls the service to add the new movie
    @PostMapping
    public Movie addMovie(@RequestParam String title, @RequestParam String rating) {
        return movieService.addMovie(title, rating);
    }

    // handles the GET requests to /movies & returns the list of all of the movies as JSON
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
}
