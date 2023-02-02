package com.example.BookMyShow.Controlers;

import com.example.BookMyShow.DTOS.UserRequestDTO;
import com.example.BookMyShow.Model.TicketEntity;
import com.example.BookMyShow.ResoponseDTO.UserBookedTicketReponseDTO;
import com.example.BookMyShow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<String> addUser(@RequestBody() UserRequestDTO userRequestDTO){
        String response = userService.addUsertoDB(userRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/get-user-booked-tickets")
    public ResponseEntity<List<UserBookedTicketReponseDTO>> getAllTickets(@RequestParam("id") int userId){
        try{
            List<UserBookedTicketReponseDTO> userBookedTicketReponseDTOS = userService.getAllTickets(userId);
            return new ResponseEntity<>(userBookedTicketReponseDTOS , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null ,HttpStatus.BAD_REQUEST);
        }
    }
    //findByName and findallUser
}
