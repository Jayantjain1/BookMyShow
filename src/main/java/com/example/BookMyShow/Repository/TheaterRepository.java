package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Model.MovieEntity;
import com.example.BookMyShow.Model.TheaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity,Integer> {

}
