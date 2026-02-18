package com.elbuensabor.reservas.reservas.logic.usercases;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConvertes;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class ModifyReservationUserCase {

    @Autowired
    private ReservationRepository repoReservation;

    //Servicio para modificar una reserva existente
    public Result<ReservationUI> execute(String reservationId, String newUserName, 
        Date newDate, String newState, int newReservedTable, int newPeopleCount) {
        Result<ReservationUI> result = null;

        try{
        var reservation = repoReservation.findByReservationId(reservationId);
        reservation.setUserName(newUserName);
        reservation.setDateReservation(newDate);
        reservation.setEstateReservation(newState);
        reservation.setReservedTable(newReservedTable);
        reservation.setNumberOfGuests(newPeopleCount);
        repoReservation.save(reservation);
         var reservationUI = EntityConvertes.ReservationEntityToUI(reservation);
        result = Result.success(reservationUI);
        }catch(Exception e){
        result = Result.failure(e);
    }
    return result;

    }

}
