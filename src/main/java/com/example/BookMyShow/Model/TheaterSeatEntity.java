package com.example.BookMyShow.Model;

import com.example.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theater_seats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterSeatEntity {   //All the  virtual seats which we can see
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    private int rate;    //It is fixed amount but it will be varied according to date, time and days
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    public TheaterSeatEntity(String seatNo,int rate,SeatType seatType){
        this.seatNo = seatNo;
        this.rate = rate;
        this.seatType = seatType;
    }

    @ManyToOne
    @JoinColumn
    private TheaterEntity theater;

}
