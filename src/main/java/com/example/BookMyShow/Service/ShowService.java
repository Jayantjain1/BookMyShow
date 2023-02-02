package com.example.BookMyShow.Service;

import com.example.BookMyShow.DTOS.ShowRequestDTO;
import com.example.BookMyShow.Model.*;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.ShowSeatsRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;   //because movie name is comming from movie repository, (which movie is in the show)

    @Autowired
    TheaterRepository theaterRepository;  //beacuse In which theater show is running

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    ShowRepository showRepository;

    public String createShow(ShowRequestDTO showRequestDTO) {
        //Now it has to set two foreign keys and here two foriegn keys are movieId and theaterId

        //Show Entity
        ShowEntity showEntity = ShowEntity.builder().showDate(showRequestDTO.getShowDate()).showTime(showRequestDTO.getShowTime())
                .multiplier(showRequestDTO.getMultiplier()).build();

        //Need Movie Repository to get movieEntity using findbyName This show has which movie
        MovieEntity movieEntity = movieRepository.findByMovieName(showRequestDTO.getMovieName());  //goal is to get the movieName and fetch it

        //Need Theater Repository
        TheaterEntity theaterEntity = theaterRepository.findById(showRequestDTO.getTheaterId()).get();

        showEntity.setTheater(theaterEntity);  //we got the theater and movie Entity corresponding to the show so now I have set it back to the show Entity
        showEntity.setMovie(movieEntity);

        List<ShowSeatsEntity> seatsEntityList = createShowSeats(theaterEntity.getTheaterSeatEntityList());

        showEntity.setListOfSeats(seatsEntityList);

        movieEntity.getListOfshows().add(showEntity);
        theaterEntity.getListOfShows().add(showEntity);


        //this is how the thr particular showseat belonging to which show and (foreign key is added)
        for (ShowSeatsEntity showSeats : seatsEntityList) {
            showSeats.setShow(showEntity);
        }
        movieRepository.save(movieEntity);
        theaterRepository.save(theaterEntity);
//        showRepository.save(showEntity);    //it doesn't need to be called because the parent save function is called
        return "show succesfuly added";
    }

    public List<ShowSeatsEntity> createShowSeats(List<TheaterSeatEntity> theaterSeatEntityList) {
        List<ShowSeatsEntity> seats = new ArrayList<>();

        for (TheaterSeatEntity theaterSeatEntity : theaterSeatEntityList) {

            ShowSeatsEntity showSeatsEntity = ShowSeatsEntity.builder().seatNo(theaterSeatEntity.getSeatNo()).seatType(theaterSeatEntity.getSeatType()).build();
            seats.add(showSeatsEntity);
        }
        showSeatsRepository.saveAll(seats);
        return seats;
    }
}