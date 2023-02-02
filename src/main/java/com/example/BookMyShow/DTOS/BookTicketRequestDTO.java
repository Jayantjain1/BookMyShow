package com.example.BookMyShow.DTOS;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//The goal of dto is to send light weight object bodies
@Data
//@NoArgsConstructor
public class BookTicketRequestDTO {
    private List<String> requestedSeats;
    private int showId;
    private int userID;
}
