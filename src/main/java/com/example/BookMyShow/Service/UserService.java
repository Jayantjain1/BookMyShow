package com.example.BookMyShow.Service;

import com.example.BookMyShow.DTOS.UserRequestDTO;
import com.example.BookMyShow.Model.ShowEntity;
import com.example.BookMyShow.Model.TicketEntity;
import com.example.BookMyShow.Model.UserEntity;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import com.example.BookMyShow.ResoponseDTO.UserBookedTicketReponseDTO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//In hibenate all he sql properties are written and defined
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;

    public String addUsertoDB(UserRequestDTO userRequestDTO){
//        UserEntity obj = new UserEntity();   //This is one of the ways to convert DTO to entity
//        obj.setName(userRequestDTO.getName());
//        obj.setMobile(userRequestDTO.getMobile());
//        userRepository.save(obj);
//        return "success";
        UserEntity userEntity = UserEntity.builder().name(userRequestDTO.getName()).mobile(userRequestDTO.getMobile()).build();
        //If it contans  an exception i am handling it through try catch
        try {
            userRepository.save(userEntity);
        }
        catch (Exception e){
            return "user couldn't be added";
        }
        return "user added successfuly";
    }
    public List<UserBookedTicketReponseDTO> getAllTickets(int userId){
        List<TicketEntity> ticketEntityList = ticketRepository.findAll();
        List<UserBookedTicketReponseDTO> UserbookedTicketDtoList = new ArrayList<>();
        for(TicketEntity ticket: ticketEntityList){
            if(ticket.getUser().getId() == userId){
                ShowEntity show = ticket.getShow();
                UserBookedTicketReponseDTO userBookedTicketDTO = new UserBookedTicketReponseDTO();
                userBookedTicketDTO.setTicketId(ticket.getId());
                userBookedTicketDTO.setAllottedSeats(ticket.getAlloted_seats());
                userBookedTicketDTO.setShowId(show.getId());
                userBookedTicketDTO.setMovieName(show.getMovie().getMovieName());
                userBookedTicketDTO.setTheatreName(show.getTheater().getName());
                userBookedTicketDTO.setShowDate(show.getShowDate());
                userBookedTicketDTO.setShowTime(show.getShowTime());

                UserbookedTicketDtoList.add(userBookedTicketDTO);
            }
        }
        return UserbookedTicketDtoList;
    }
}
