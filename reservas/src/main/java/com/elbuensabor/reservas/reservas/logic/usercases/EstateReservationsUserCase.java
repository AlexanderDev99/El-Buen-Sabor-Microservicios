package com.elbuensabor.reservas.reservas.logic.usercases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbuensabor.reservas.reservas.controllers.converters.EntityConvertes;
import com.elbuensabor.reservas.reservas.controllers.data.ReservationUI;
import com.elbuensabor.reservas.reservas.data.repository.ReservationRepository;
import com.elbuensabor.reservas.reservas.logic.validators.Result;

@Service
public class EstateReservationsUserCase {

    @Autowired
    private ReservationRepository repoReservation;

    public Result<ReservationUI> calcelReservation(String reservaId) {
        Result<ReservationUI> result = null;
        try {
            var reservationCancel = repoReservation.findByReservationId(reservaId);
            reservationCancel.setEstateReservation("CANCEL");
            repoReservation.save(reservationCancel);
            var reservationUI = EntityConvertes.ReservationEntityToUI(reservationCancel);
            result = Result.success(reservationUI);
        } catch (Exception e) {
            result = Result.failure(e);
        }
        return result;
    }

    public Result<ReservationUI> completeReservation(String reservationId) {
        Result<ReservationUI> result = null;
        try {
            var reservationComplete = repoReservation.findByReservationId(reservationId);
            reservationComplete.setEstateReservation("COMPLETE");
            reservationComplete.setReservedTable(1);
            repoReservation.save(reservationComplete);
            var reservationUI = EntityConvertes.ReservationEntityToUI(reservationComplete);
            result = Result.success(reservationUI);
        } catch (Exception e) {
            result = Result.failure(e);
        }
        return result;
    }

    

}
