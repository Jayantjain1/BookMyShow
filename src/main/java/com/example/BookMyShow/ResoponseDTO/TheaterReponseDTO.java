package com.example.BookMyShow.ResoponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheaterReponseDTO {
    private int id;
    private String name;
    private String city;
    private String address;
}
