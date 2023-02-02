package com.example.BookMyShow.Service;

import com.example.BookMyShow.DTOS.TheaterRequestDTO;
import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Model.MovieEntity;
import com.example.BookMyShow.Model.ShowEntity;
import com.example.BookMyShow.Model.TheaterEntity;
import com.example.BookMyShow.Model.TheaterSeatEntity;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Repository.TheaterSeatRepository;
import com.example.BookMyShow.ResoponseDTO.TheaterReponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterSeatRepository theaterSeatRepository ;
    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    MovieRepository movieRepository;

    public String createTheater(TheaterRequestDTO theaterRequestDTO){
        TheaterEntity theater = TheaterEntity.builder().name(theaterRequestDTO.getName()).city(theaterRequestDTO.getCity()).
                address(theaterRequestDTO.getAddress()).build();

        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();

        theater.setTheaterSeatEntityList(theaterSeats);
        //For each theater : set the theaterEntity
        for(TheaterSeatEntity theaterSeat: theaterSeats){
            theaterSeat.setTheater(theater);      //This particular theater seat is belonging to which theater
        }
        theaterRepository.save(theater);
        return "theater added successfully";
    }
    public List<TheaterSeatEntity> createTheaterSeats(){
        List<TheaterSeatEntity> seats = new ArrayList<>();
        //This i am creating seats by creating the objects;
        //Here i have asumed that there are 10 seats available
        for(int i=0;i<10;i++) {
            if (i < 5) {
                char ch = (char) ('A' + i);
                String seatNo = "1" + ch;
                TheaterSeatEntity theaterSeatEntity = new TheaterSeatEntity(seatNo, 100, SeatType.CLASSIC);
                seats.add(theaterSeatEntity);
            } else {
                char ch = (char) ('A' + (i - 5));
                String seatNo = "2" + ch;
                TheaterSeatEntity theaterSeatEntity = new TheaterSeatEntity(seatNo, 200, SeatType.PLATINUM);
                seats.add(theaterSeatEntity);
            }
        }
//        TheaterSeatEntity theaterSeatEntity1 = new TheaterSeatEntity("1A",100, SeatType.CLASSIC);
//        TheaterSeatEntity theaterSeatEntity2 = new TheaterSeatEntity("1B",100, SeatType.CLASSIC);
//        TheaterSeatEntity theaterSeatEntity3 = new TheaterSeatEntity("1C",100, SeatType.CLASSIC);
//        TheaterSeatEntity theaterSeatEntity4 = new TheaterSeatEntity("1D",100, SeatType.CLASSIC);
//        TheaterSeatEntity theaterSeatEntity5 = new TheaterSeatEntity("1E",100, SeatType.CLASSIC);
//        TheaterSeatEntity theaterSeatEntity6 = new TheaterSeatEntity("2A",100, SeatType.PLATINUM);
//        TheaterSeatEntity theaterSeatEntity7 = new TheaterSeatEntity("2B",100, SeatType.PLATINUM);
//        TheaterSeatEntity theaterSeatEntity8 = new TheaterSeatEntity("2C",100, SeatType.PLATINUM);
//        TheaterSeatEntity theaterSeatEntity9 = new TheaterSeatEntity("2D",100, SeatType.PLATINUM);
//        TheaterSeatEntity theaterSeatEntity10 = new TheaterSeatEntity("2E",100, SeatType.PLATINUM);
//
//        seats.add(theaterSeatEntity1);
//        seats.add(theaterSeatEntity2);
//        seats.add(theaterSeatEntity3);
//        seats.add(theaterSeatEntity4);
//        seats.add(theaterSeatEntity5);
//        seats.add(theaterSeatEntity6);
//        seats.add(theaterSeatEntity7);
//        seats.add(theaterSeatEntity8);
//        seats.add(theaterSeatEntity9);
//        seats.add(theaterSeatEntity10);
//
        theaterSeatRepository.saveAll(seats);  //Save only savees one entity and Save all save the entire list
        return seats;
    }
    public TheaterReponseDTO getTheaterById(int theaterId) throws Exception {
        if(theaterRepository.findById(theaterId).isPresent()){
            TheaterEntity theater = theaterRepository.findById(theaterId).get();
            TheaterReponseDTO theaterReponseDTO = TheaterReponseDTO.builder().name(theater.getName()).id(theater.getId())
                    .address(theater.getAddress()).city(theater.getCity()).build();
            return theaterReponseDTO;
        }
        throw new Exception("Theater is of found");
    }
    public List<TheaterReponseDTO> getAllTheaterByMovieName(String movieName){
        MovieEntity movie = movieRepository.findByMovieName(movieName);
        List<ShowEntity> list = movie.getListOfshows();
        List<TheaterReponseDTO> theaterReponseDTOList = new ArrayList<>();
        for(ShowEntity show: list){
            TheaterEntity theater = show.getTheater();
            TheaterReponseDTO theaterReponseDTO = TheaterReponseDTO.builder().id(theater.getId()).name(theater.getName())
                    .address(theater.getAddress()).city(theater.getCity()).build();
            if(!theaterReponseDTOList.contains(theaterReponseDTO)){
                theaterReponseDTOList.add(theaterReponseDTO);
            }
        }
        return theaterReponseDTOList;
    }
    public List<TheaterReponseDTO> getAllTheater(){
        List<TheaterReponseDTO> theaterReponseDTOList = new ArrayList<>();
        List<TheaterEntity> theaterEntityList = theaterRepository.findAll();
        for(TheaterEntity theater: theaterEntityList){
            TheaterReponseDTO theaterReponseDTO = TheaterReponseDTO.builder().city(theater.getCity()).address(theater.getAddress())
                    .name(theater.getName()).id(theater.getId()).build();
            theaterReponseDTOList.add(theaterReponseDTO);
        }
        return theaterReponseDTOList;
    }
}





