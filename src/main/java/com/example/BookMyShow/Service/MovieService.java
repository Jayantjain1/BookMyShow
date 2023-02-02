package com.example.BookMyShow.Service;

import com.example.BookMyShow.DTOS.MovieRequestDTO;
import com.example.BookMyShow.Model.MovieEntity;
import com.example.BookMyShow.Model.ShowEntity;
import com.example.BookMyShow.Model.TheaterEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String creatMovie(MovieRequestDTO movieRequestDTO){
        //Convert DTO to movieentity
        MovieEntity movie = MovieEntity.builder().movieName(movieRequestDTO.getName()).duration(movieRequestDTO.getDuration())
                .releaseDate(movieRequestDTO.getReleasedate()).build();
        movieRepository.save(movie);
        return "sucessFully added";
    }
    public MovieEntity getMovieByName(String movieName){
        MovieEntity movie = movieRepository.findByMovieName(movieName);
        return movie;
    }
}
