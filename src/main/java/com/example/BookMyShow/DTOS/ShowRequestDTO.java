package com.example.BookMyShow.DTOS;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDTO {
    private LocalDate showDate;
    private LocalTime showTime;
    private double multiplier;
    //These below two variable are just to define that which movie is there in the show and in which theater
    private String movieName;
    private int theaterId;
}
