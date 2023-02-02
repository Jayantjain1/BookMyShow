package com.example.BookMyShow.Model;

import com.example.BookMyShow.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "showSeats")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShowSeatsEntity {   //It will show the seats that are booked

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;
    private boolean booked;

    private Date bookedAt;

    @ManyToOne
    @JoinColumn
    private ShowEntity show;
    @ManyToOne
    @JoinColumn
    private TicketEntity ticket;
//    @OneToMany
//    @JoinColumn
//    private TicketEntity ticket;
}
