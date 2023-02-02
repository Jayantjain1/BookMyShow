package com.example.BookMyShow.DTOS;

import lombok.Data;

import java.util.Date;

@Data
public class MovieRequestDTO {
    private String name;
    private Date releasedate;
    private int duration;
}
