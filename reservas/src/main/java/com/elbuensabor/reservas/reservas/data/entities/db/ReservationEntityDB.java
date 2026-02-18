package com.elbuensabor.reservas.reservas.data.entities.db;

import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reservations")
public class ReservationEntityDB {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    //Datos de la reserva
    private String reservationId;
    private String userName;
    private Date dateReservation;
    private String estateReservation;
    private int reservedTable;
    private int numberOfGuests;


}
