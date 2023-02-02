package com.example.BookMyShow.Controlers;

import com.example.BookMyShow.DTOS.MovieRequestDTO;
import com.example.BookMyShow.Model.MovieEntity;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody()MovieRequestDTO movieRequestDTO){
        try {
            String reponse =  movieService.creatMovie(movieRequestDTO);
            return new ResponseEntity<>(reponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("movie couldn't added", HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/movie-name")
    public MovieEntity getMovieName(@RequestParam String movieName){
        return movieService.getMovieByName(movieName);
    }
}
