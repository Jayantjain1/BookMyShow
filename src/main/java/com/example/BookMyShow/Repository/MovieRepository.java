package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
    MovieEntity findByMovieName(String movieName) ;  //These sql querries are implemented in Jpa repository we need to manually write it
    //This is function available in Jpa repository
}
