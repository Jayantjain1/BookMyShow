package com.example.BookMyShow.Service;

import com.example.BookMyShow.DTOS.BookTicketRequestDTO;
import com.example.BookMyShow.Model.ShowEntity;
import com.example.BookMyShow.Model.ShowSeatsEntity;
import com.example.BookMyShow.Model.TicketEntity;
import com.example.BookMyShow.Model.UserEntity;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BookTicketService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TicketRepository ticketRepository;
    public String bookTicket(BookTicketRequestDTO bookTicketRequestDTO) throws Exception {
        //get Requested seats

        List<String> requestedSeats = bookTicketRequestDTO.getRequestedSeats();
        ShowEntity showEntity = showRepository.findById(bookTicketRequestDTO.getShowId()).get();

        UserEntity userEntity = userRepository.findById(bookTicketRequestDTO.getShowId()).get();

        //get the showseats Means for this particular show i want these many seats
        List<ShowSeatsEntity> showSeats = showEntity.getListOfSeats();

        //Checking wheather user can book his/her requested seats or not
        List<ShowSeatsEntity> bookedSeats = new ArrayList<>();

        for(ShowSeatsEntity showSeat : showSeats){  //This is how we are checking wheather the requested is booked or not
            String seatNo = showSeat.getSeatNo();
            if(showSeat.isBooked() == false && requestedSeats.contains(seatNo)){
                bookedSeats.add(showSeat);
            }
        }

        if(bookedSeats.size() != requestedSeats.size()){  //he didn't get all the requested seats
            throw new Exception("requested seats sre not available");
        }

        TicketEntity ticketEntity = new TicketEntity();
        //To calculate the amount
        double totalamount = 0;
        double multiplier = showEntity.getMultiplier();
        int rate = 0;
        String allotedseats = "";
        for(ShowSeatsEntity bookedSeat: bookedSeats){
            bookedSeat.setBooked(true);  //marked as status that we have booked it
            bookedSeat.setBookedAt(new Date());  //On which date i have booked it
            bookedSeat.setTicket(ticketEntity);  // ticket booked
            bookedSeat.setShow(showEntity);   //Which show we have booked it for
            //Calculating amount
            String seatNo = bookedSeat.getSeatNo();
            allotedseats += seatNo + ",";

            if(seatNo.charAt(0) == '1'){
                rate = 100;
            }
            else{
                rate = 200;
            }

            totalamount = totalamount + multiplier * rate;
        }
        ticketEntity.setBookedAt(new Date());
        ticketEntity.setAmount((int)totalamount);
        ticketEntity.setShow(showEntity);   //which show this ticket has been  booked for , setting back to ticketEntity
        ticketEntity.setUser(userEntity);   //who has booked this ticket , setting back to ticketEntity
//        ticketEntity.setBookedSeats(bookedSeats);
        ticketEntity.setAlloted_seats(allotedseats);
        ticketRepository.save(ticketEntity);
        return "Successfully created";
    }
    public int cancelTicket(int ticketId) throws Exception {
        TicketEntity ticket = ticketRepository.findById(ticketId).get();
        int amount = ticket.getAmount();
        ticketRepository.delete(ticket);
        return amount;
    }
}
