package com.elbuensabor.reservas.reservas.controllers.data;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationUI {

    // Datos de la reserva
    private String reservationCode;
    private String userName;
    private Date date;
    private String state;
    private int tableBooked;
    private int numberOfGuests;
}
