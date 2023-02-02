package com.example.BookMyShow.Controlers;

import com.example.BookMyShow.DTOS.BookTicketRequestDTO;
import com.example.BookMyShow.Service.BookTicketService;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    BookTicketService bookTicketService;
    @PostMapping("/book")
    public String BookTicket(@RequestBody BookTicketRequestDTO bookTicketRequestDTO){
        try {
            return bookTicketService.bookTicket(bookTicketRequestDTO);
        }catch (Exception e){
            return "Requested seats are not available";
        }
    }
    @DeleteMapping("/cancel-ticket")
    public ResponseEntity<String> cancelTicket(@RequestParam("id") int ticketId){
        try{
            int amount = bookTicketService.cancelTicket(ticketId);
            return new ResponseEntity<>(amount+"is refunded" , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null , HttpStatus.BAD_REQUEST);
        }
    }
}
